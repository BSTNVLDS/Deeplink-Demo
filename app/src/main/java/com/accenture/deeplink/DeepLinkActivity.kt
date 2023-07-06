package com.accenture.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.deeplink.adapter.products.ProductAdapter
import com.accenture.deeplink.databinding.ActivityDeepLinkBinding
import com.accenture.deeplink.model.ManagerAdapter
import com.accenture.deeplink.model.Product
import com.accenture.deeplink.service.ProductService
import java.lang.Exception

class DeepLinkActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDeepLinkBinding.inflate(layoutInflater) }
    private val adapter = ProductAdapter(ProductManager())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initDefaultList()
        initRecyclerView()
        initDeepLink(intent)
    }

    private fun initDefaultList() {
        ProductService.list.clear()
        ProductService.initList()
    }

    private fun initRecyclerView() {
        binding.rvProductList.adapter = adapter
    }

    private fun initDeepLink(intent: Intent) {
        val uri = intent.data
        if (uri != null) {
            getParameters(uri)
        }
    }

    private fun getParameters(uri: Uri?) {
        val idProduct = uri?.getQueryParameter("product")
        val newList = ProductService.list.map {
            if (it.id == idProduct) {
                it.copy(selected = true)
            } else {
                it
            }
        }
        ProductService.list.clear()
        ProductService.list.addAll(newList)
        adapter.notifyDataSetChanged()
    }

    inner class ProductManager : ManagerAdapter {
        override fun actionShare(product: Product) {
            sendLink(generateDeepLinks(product))
        }
    }

    private fun generateDeepLinks(product: Product) =
        "https://www.accenture.com/deeplink?product=${product.id}"


    fun sendLink(url: String): String? {
        return try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Deeplink App")
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            this.startActivity(Intent.createChooser(shareIntent, "compartir"))
            null
        } catch (e: Exception) {
            e.toString()
        }
    }
}