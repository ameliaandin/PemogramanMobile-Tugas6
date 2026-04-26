package com.utama.aplikasiloginsederhana

data class InventoryItem(
    val id: Int,
    val name: String,
    val category: String,
    val stock: Int,
    val unit: String
)
