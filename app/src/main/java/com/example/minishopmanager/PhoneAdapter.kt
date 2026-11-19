package com.example.minishopmanager

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class PhoneAdapter(
    context: Context,
    private val phones: List<PhoneItem>
) : ArrayAdapter<PhoneItem>(context, 0, phones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_phone,
            parent,
            false
        )

        val phone = getItem(position) ?: return view

        val imgPhone = view.findViewById<ImageView>(R.id.imgPhone)
        val tvPhoneName = view.findViewById<TextView>(R.id.tvPhoneName)
        val tvPhoneSpecs = view.findViewById<TextView>(R.id.tvPhoneSpecs)
        val tvPhonePrice = view.findViewById<TextView>(R.id.tvPhonePrice)
        val btnCommander = view.findViewById<Button>(R.id.btnCommander)

        imgPhone.setImageResource(phone.imageResId)

        if (phone.imageUrl.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val bitmap = BitmapFactory.decodeStream(URL(phone.imageUrl).openStream())
                    withContext(Dispatchers.Main) {
                        imgPhone.setImageBitmap(bitmap)
                    }
                } catch (_: Exception) { }
            }
        }

        tvPhoneName.text = phone.modelName
        tvPhoneSpecs.text = phone.specs
        tvPhonePrice.text = phone.priceDT

        btnCommander.setOnClickListener {
            Toast.makeText(
                context,
                "Commande sélectionnée : ${phone.modelName} (${phone.priceDT})",
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }
}