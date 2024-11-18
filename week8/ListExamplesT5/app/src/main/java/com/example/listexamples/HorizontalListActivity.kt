package com.example.listexamples

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HorizontalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_list)

        val items = mutableListOf<ImageModel>()
        repeat(28) {
            items.add(ImageModel(
                "Image ${it + 1}",
                resources.getIdentifier("thumb${it + 1}", "drawable", packageName),
                resources.getIdentifier("wall${it + 1}", "drawable", packageName)))
        }

        val imageLarge = findViewById<ImageView>(R.id.image_large)
        val layoutItems = findViewById<LinearLayout>(R.id.layout_items)
        for (item in items) {
            val itemView = layoutInflater.inflate(R.layout.layout_image_item, layoutItems, false)

            itemView.findViewById<ImageView>(R.id.image_thumb).setImageResource(item.thumbId)
            itemView.findViewById<TextView>(R.id.text_title).text = item.title

            layoutItems.addView(itemView)

            itemView.setOnClickListener {
                imageLarge.setImageResource(item.imageId)
            }
        }
    }
}