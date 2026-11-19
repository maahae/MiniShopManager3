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

class ModelAdapter(
    context: Context,
    private val models: List<ModelItem>
) : ArrayAdapter<ModelItem>(context, 0, models) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_model,
            parent,
            false
        )

        val model = getItem(position) ?: return view

        val imgModel = view.findViewById<ImageView>(R.id.imgModel)
        val tvModelBrand = view.findViewById<TextView>(R.id.tvModelBrand)
        val tvModelSpecs = view.findViewById<TextView>(R.id.tvModelSpecs)
        val tvModelPrice = view.findViewById<TextView>(R.id.tvModelPrice)

        imgModel.setImageResource(model.imageResId)

        if (model.imageUrl.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val bitmap = BitmapFactory.decodeStream(URL(model.imageUrl).openStream())
                    withContext(Dispatchers.Main) {
                        imgModel.setImageBitmap(bitmap)
                    }
                } catch (_: Exception) { }
            }
        }

        tvModelBrand.text = model.brand
        tvModelSpecs.text = model.specs
        tvModelPrice.text = model.price

        return view
    }
}