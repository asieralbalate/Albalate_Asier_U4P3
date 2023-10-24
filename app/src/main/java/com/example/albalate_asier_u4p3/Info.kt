package com.example.albalate_asier_u4p3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Info() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Info")
    }

}