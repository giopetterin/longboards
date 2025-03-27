import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.longboardapp.data.LongBoardsDataBase
import com.example.longboardapp.data.dao.UserDao
import com.example.longboardapp.data.entities.UserEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class UserDaoTest {

    private lateinit var database: LongBoardsDataBase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LongBoardsDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()
        userDao = database.getUserDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `getUserByNameAndPassword returns UserEntity`() = runTest {
        val userEntity = UserEntity(1, "testUser", "testPassword")
        userDao.insert(userEntity)

        val result = userDao.getUserByNameAndPassword("testUser", "testPassword")

        assertEquals(userEntity, result)
    }

    @Test
    fun `insert inserts UserEntity`() = runTest {
        val userEntity = UserEntity(user = "testUser", password = "testPassword")
        userDao.insert(userEntity)

        val result = userDao.getUserByNameAndPassword("testUser", "testPassword")

        assertEquals(userEntity.user, result.user)
        assertEquals(userEntity.password, result.password)
    }

    @Test
    fun `insert replaces existing user`() = runTest {
        val userEntity1 = UserEntity(1, "testUser", "testPassword")
        userDao.insert(userEntity1)

        val userEntity2 = UserEntity(1, "testUser", "newPassword")
        userDao.insert(userEntity2)

        val result = userDao.getUserByNameAndPassword("testUser", "newPassword")

        assertEquals(userEntity2, result)
    }
}