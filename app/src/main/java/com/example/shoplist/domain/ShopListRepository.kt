package com.example.shoplist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemId(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}