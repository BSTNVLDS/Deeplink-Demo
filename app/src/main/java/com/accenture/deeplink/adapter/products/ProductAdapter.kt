package com.accenture.deeplink.adapter.products

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.accenture.deeplink.R
import com.accenture.deeplink.model.ManagerAdapter
import com.accenture.deeplink.service.ProductService

class ProductAdapter(private val manager: ManagerAdapter) : RecyclerView.Adapter<ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductHolder(view, manager)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) =
        holder.bind(ProductService.getElement(position))

    override fun getItemCount() = ProductService.getSize()

}