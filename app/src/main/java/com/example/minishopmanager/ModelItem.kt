package com.example.minishopmanager

data class ModelItem(
    val brand: String,
    val specs: String,
    val price: String,
    val imageResId: Int,
    val imageUrl: String = ""
)