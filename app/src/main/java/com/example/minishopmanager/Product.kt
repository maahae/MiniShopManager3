package com.example.minishopmanager

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val imageResId: Int,
    val imageUrl: String = ""
)