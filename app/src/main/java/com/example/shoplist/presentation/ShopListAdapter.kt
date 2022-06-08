package com.example.shoplist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.databinding.ShopItemDisabledBinding
import com.example.shoplist.databinding.ShopItemEnabledBinding
import com.example.shoplist.domain.ShopItem

class ShopListAdapter(
    private val longListener: OnShopItemLongClickListener,
    private val clickListener: OnShopItemClickListener
    ): RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var bindingEnabled = ShopItemEnabledBinding.bind(view)
        private var bindingDisabled = ShopItemDisabledBinding.bind(view)



        fun bindEnabled(shopItem: ShopItem) = with(bindingEnabled){
            itemName.text = shopItem.name
            itemCount.text = shopItem.count.toString()
        }

        fun bindDisabled(shopItem: ShopItem) = with(bindingDisabled){
            itemName.text = shopItem.name
            itemCount.text = shopItem.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLED -> R.layout.shop_item_enabled
            else -> R.layout.shop_item_disabled
    }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bindEnabled(shopList[position])
        holder.bindDisabled(shopList[position])

        holder.itemView.setOnClickListener {
            clickListener.onShopItemClick(shopList[position])
        }

        holder.itemView.setOnLongClickListener {
            longListener.onShopItemLongClick(shopList[position])
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (shopList[position].isActive) {
            true -> ENABLED
            else -> DISABLED
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    interface OnShopItemClickListener {
        fun onShopItemClick(shopItem: ShopItem)
    }

    companion object {
        const val ENABLED = 1
        const val DISABLED = 0
        const val MAX_POOL_SIZE = 8
    }
}