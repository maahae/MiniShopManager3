package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ModelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_models)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Téléphone"
        val tvCategoryTitle = findViewById<TextView>(R.id.tvCategoryTitle)
        tvCategoryTitle.text = "${getString(R.string.title_models)} ($categoryName)"

        val modelList = listOf(
            ModelItem("Oppo", "Find X, Reno & A Series", "À partir de 580 DT", R.drawable.ic_oppo),
            ModelItem("Samsung", "Galaxy S, Note, A & Z Fold", "À partir de 1250 DT", R.drawable.ic_samsung),
            ModelItem("iPhone", "iPhone 15, 14, 13 Pro & SE", "À partir de 1930 DT", R.drawable.ic_apple),
            ModelItem("Redmi", "Redmi Note 13, 12 Pro & K Series", "À partir de 420 DT", R.drawable.ic_redmi),
        )

        val lvModels = findViewById<ListView>(R.id.lvModels)
        val adapter = ModelAdapter(this, modelList)
        lvModels.adapter = adapter

        // Ouverture de la liste détaillée des modèles de la marque sélectionnée
        lvModels.setOnItemClickListener { _, _, position, _ ->
            val selectedBrand = modelList[position]
            val intent = Intent(this, PhoneListActivity::class.java)
            intent.putExtra("BRAND_NAME", selectedBrand.brand)
            startActivity(intent)
        }

        val btnRetourModels = findViewById<Button>(R.id.btnRetourModels)
        btnRetourModels.setOnClickListener {
            finish()
        }
    }
}