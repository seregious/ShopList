package com.example.shoplist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getItemId(shopItemId: String): ShopItem {
        return shopListRepository.getItemId(shopItemId.toInt())
    }
}