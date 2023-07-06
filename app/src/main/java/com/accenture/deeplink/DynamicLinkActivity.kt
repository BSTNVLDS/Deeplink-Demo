package com.accenture.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.accenture.deeplink.adapter.products.ProductAdapter
import com.accenture.deeplink.databinding.ActivityDeepLinkBinding
import com.accenture.deeplink.model.ManagerAdapter
import com.accenture.deeplink.model.Product
import com.accenture.deeplink.service.ProductService
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class DynamicLinkActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDeepLinkBinding.inflate(layoutInflater) }
    private val adapter = ProductAdapter(ProductManager())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initDefaultList()
        initRecyclerView()
        initDynamicLink(intent)
    }

    private fun initDefaultList() {
        ProductService.list.clear()
        ProductService.initList()
    }


    private fun initRecyclerView() {
        binding.rvProductList.adapter = adapter
    }

    private fun initDynamicLink(intent: Intent) {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener { dynamicLinkData ->
                dynamicLinkData?.let {
                    getParameters(it.link)
                }
            }
            .addOnFailureListener(this) { e ->
                Log.d("DynamicLinkError", e.message ?: "ERROR")
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
            sendLink(createLink(product))
        }
    }

    private fun createLink(product: Product): String {
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link =
                Uri.parse("https://www.deeplinkdemodemo.page.link/deeplink?product=${product.id}")
            domainUriPrefix = "https://deeplinkdemodemo.page.link/"

            androidParameters("com.accenture.deeplink") {
                minimumVersion = 1
            }
            socialMetaTagParameters {
                this.title = product.name
                this.imageUrl =product.metaImage.toUri()
                this.description = "Stock${product.stock}"
            }
        }
        return dynamicLink.uri.toString()
    }

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




