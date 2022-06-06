package com.example.shoplist.domain

data class ShopItem(
    val id: String,
    val name: String,
    val count: Int,
    var isActive: Boolean
)
