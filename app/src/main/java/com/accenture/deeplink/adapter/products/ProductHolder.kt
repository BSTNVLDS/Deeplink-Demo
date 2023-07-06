package com.accenture.deeplink.adapter.products

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.deeplink.databinding.ItemProductBinding
import com.accenture.deeplink.model.ManagerAdapter
import com.accenture.deeplink.model.Product

class ProductHolder(itemView: View, private val manager: ManagerAdapter) :
    RecyclerView.ViewHolder(itemView) {
    private val binding = ItemProductBinding.bind(itemView)
    fun bind(product: Product) {
        binding.txtTitleProduct.text = product.name
        binding.txtIdProduct.text = product.id
        binding.txtStockProduct.text = product.stock.toString()
        binding.txtImageProduct.setImageResource(product.photo)
        binding.btnShareActionProduct.setOnClickListener {
            manager.actionShare(product)
        }
        if (product.selected) {
            binding.cardView.strokeColor = Color.WHITE
        }
    }
}