package com.example.minishopmanager

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ProductAdapter(
    context: Context,
    private val products: List<Product>
) : ArrayAdapter<Product>(context, 0, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_product,
            parent,
            false
        )

        val product = getItem(position) ?: return view

        val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)
        val tvProductName = view.findViewById<TextView>(R.id.tvProductName)
        val tvProductDesc = view.findViewById<TextView>(R.id.tvProductDesc)
        val tvProductPrice = view.findViewById<TextView>(R.id.tvProductPrice)

        imgProduct.setImageResource(product.imageResId)

        if (product.imageUrl.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val bitmap = BitmapFactory.decodeStream(URL(product.imageUrl).openStream())
                    withContext(Dispatchers.Main) {
                        imgProduct.setImageBitmap(bitmap)
                    }
                } catch (_: Exception) { }
            }
        }

        tvProductName.text = product.name
        tvProductDesc.text = product.description
        tvProductPrice.text = product.price

        return view
    }
}