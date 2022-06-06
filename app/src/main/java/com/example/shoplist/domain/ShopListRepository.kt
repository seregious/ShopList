package com.example.shoplist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemId(shopItemId: String): ShopItem

    fun getShopList(): List<ShopItem>
}