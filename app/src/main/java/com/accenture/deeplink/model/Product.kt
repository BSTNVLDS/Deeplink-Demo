package com.accenture.deeplink.model

data class Product(
    val id: String,
    val name: String,
    val stock: Int,
    val photo: Int,
    val metaImage: String,
    val selected: Boolean
)