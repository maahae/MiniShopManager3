package com.example.minishopmanager

data class PhoneItem(
    val modelName: String,
    val specs: String,
    val priceDT: String,
    val imageResId: Int,
    val imageUrl: String = ""
)