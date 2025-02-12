package com.example.longboardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.longboardapp.R

@Composable
fun MyImageLongBoards(name: String){

    if (name == "Dancing") Image(
        painterResource(R.drawable.imagedancinglongboard),
        "Mi imagen dancing",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .aspectRatio(1f)
    )
    else if (name == "Cruising") Image(
        painterResource(R.drawable.imagecruisinglongboard),
        "Mi imagen dancing",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) else if (name == "SurfTStake") Image(
        painterResource(R.drawable.imagesurfskate),
        "Mi imagen dancing",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) else if (name == "Balance") Image(
        painterResource(R.drawable.imagebalancelongboard),
        "Mi imagen dancing",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )


}