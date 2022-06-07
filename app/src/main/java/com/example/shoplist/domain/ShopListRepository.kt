package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemId(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}