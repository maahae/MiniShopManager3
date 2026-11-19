package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSuivant = findViewById<Button>(R.id.btnSuivant)
        btnSuivant.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        val productList = listOf(
            Product("Téléphone", "Smartphones iOS & Android", "À partir de 500DT", R.drawable.ic_phone),
            Product("Casque Bluetooth", "Réduction de bruit active & Basses", "45DT", R.drawable.ic_headphone),
            Product("Montre connectée", "Suivi santé, sport & notifications", "365DT", R.drawable.ic_smartwatch),
            Product("Chargeur USB", "Charge rapide Fast Charge 65W", "25DT", R.drawable.ic_charger)
        )

        // Configuration du ListView avec le Custom Adapter
        val lvProduits = findViewById<ListView>(R.id.lvProduits)
        val adapter = ProductAdapter(this, productList)
        lvProduits.adapter = adapter

        // Gestion du clic sur un produit
        lvProduits.setOnItemClickListener { _, _, position, _ ->
            val produit = productList[position]
            if (produit.name == "Téléphone") {
                val intent = Intent(this, ModelsActivity::class.java)
                intent.putExtra("CATEGORY_NAME", produit.name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Produit sélectionné : ${produit.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}