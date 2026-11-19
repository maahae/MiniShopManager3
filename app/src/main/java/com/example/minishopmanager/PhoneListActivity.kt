package com.example.minishopmanager

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhoneListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_list)

        val brandName = intent.getStringExtra("BRAND_NAME") ?: "Samsung"
        val tvBrandTitle = findViewById<TextView>(R.id.tvBrandTitle)
        tvBrandTitle.text = brandName

        // Chargement des téléphones réels avec leurs caractéristiques et prix en DT selon la marque
        val phoneList = when (brandName) {
            "Samsung" -> listOf(
                PhoneItem("Samsung Galaxy S24 Ultra", "Snapdragon 8 Gen 3, 256GB", "3800 DT", R.drawable.ic_phone_samsung_s24),
                PhoneItem("Samsung Galaxy A55 5G", "Super AMOLED 120Hz, 8GB/256GB", "1250 DT", R.drawable.ic_phone_samsung_a55),
                PhoneItem("Samsung Galaxy Z Flip 5", "Écran Pliable, 8GB/256GB", "3200 DT", R.drawable.ic_phone_samsung_zflip),
            )
            "iPhone", "Apple" -> listOf(
                PhoneItem("iPhone 15 Pro Max", "Puce A17 Pro, 256GB", "4200 DT", R.drawable.ic_phone_iphone_15pro),
                PhoneItem("iPhone 15", "Puce A16 Bionic, 128GB", "3100 DT", R.drawable.ic_phone_iphone_15),
                PhoneItem("iPhone 13", "Puce A15 Bionic, 128GB", "2200 DT", R.drawable.ic_phone_iphone_13),
            )
            "Oppo" -> listOf(
                PhoneItem("Oppo Reno 11 Pro 5G", "Caméra Portrait 50MP, 12GB/512GB", "1450 DT", R.drawable.ic_phone_oppo_reno11),
                PhoneItem("Oppo A98 5G", "Batterie 5000mAh, 67W, 8GB/256GB", "890 DT", R.drawable.ic_phone_oppo_a98),
                PhoneItem("Oppo A58", "FHD+ Sunlight Display, 6GB/128GB", "580 DT", R.drawable.ic_phone_oppo_a58),
            )
            "Redmi", "Xiaomi" -> listOf(
                PhoneItem("Redmi Note 13 Pro+ 5G", "Caméra 200MP, 120W, 12GB/512GB", "1350 DT", R.drawable.ic_phone_redmi_note13pro),
                PhoneItem("Redmi Note 13", "Écran AMOLED 120Hz, 8GB/256GB", "690 DT", R.drawable.ic_phone_redmi_note13),
                PhoneItem("Redmi 13C", "Batterie 5000mAh, 4GB/128GB", "420 DT", R.drawable.ic_phone_redmi_13c),
            )
            else -> listOf(
                PhoneItem("$brandName Standard", "Écran HD+, 64GB", "450 DT", R.drawable.ic_phone),
            )
        }

        val lvPhoneList = findViewById<ListView>(R.id.lvPhoneList)
        val adapter = PhoneAdapter(this, phoneList)
        lvPhoneList.adapter = adapter

        // Action au clic sur un item
        lvPhoneList.setOnItemClickListener { _, _, position, _ ->
            val phone = phoneList[position]
            Toast.makeText(this, "Sélection : ${phone.modelName} - ${phone.priceDT}", Toast.LENGTH_SHORT).show()
        }

        val btnRetourBrands = findViewById<Button>(R.id.btnRetourBrands)
        btnRetourBrands.setOnClickListener {
            finish()
        }
    }
}