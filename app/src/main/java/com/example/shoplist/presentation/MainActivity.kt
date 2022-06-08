package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem

class MainActivity :
    AppCompatActivity(),
    ShopListAdapter.OnShopItemLongClickListener,
    ShopListAdapter.OnShopItemClickListener
{

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = ShopListAdapter(this,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this) [MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }

        setupRecycleView()
    }

    private fun setupRecycleView() = with(binding){
        rvShopList.adapter = adapter
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.shopList
            }

        }
    }

    override fun onShopItemClick(shopItem: ShopItem) {
        viewModel.changeEnabledState(shopItem)
    }

    override fun onShopItemLongClick(shopItem: ShopItem) {
        TODO("Not yet implemented")
    }
}