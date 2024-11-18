package com.example.example05

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtMsg: TextView = findViewById(R.id.txtMsg)
        val viewGroup: LinearLayout = findViewById(R.id.viewgroup)
        val imageSelected: ImageView = findViewById(R.id.imageSelected)

        val captionList: ArrayList<String> = ArrayList()
        val thumbList: ArrayList<Int> = ArrayList()
        val imageList: ArrayList<Int> = ArrayList()

        for(i in 1..27) {
            captionList.add("Data-$i")
            thumbList.add(resources.getIdentifier("thumb$i", "drawable", packageName))
            imageList.add(resources.getIdentifier("wall$i", "drawable", packageName))
        }

        for (i in 0..captionList.size) {
            val view: View = layoutInflater.inflate(R.layout.xxx, viewGroup, false)
            view.id = i

            view.findViewById<TextView>(R.id.caption).text = captionList[i]
            view.findViewById<ImageView>(R.id.icon).setImageResource(thumbList[i])

            viewGroup.addView(view)

            view.setOnClickListener{
                view.setOnClickListener{
                    txtMsg.text = "Selected position: ${view.id}"
                    imageSelected.setImageResource(imageList[view.id])
                }
            }
        }
    }
}