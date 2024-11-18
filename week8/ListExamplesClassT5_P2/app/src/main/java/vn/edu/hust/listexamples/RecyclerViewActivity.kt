package vn.edu.hust.listexamples

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.listexamples.adapters.ItemAdapter
import vn.edu.hust.listexamples.models.ItemModel

class RecyclerViewActivity : AppCompatActivity(), ItemClickListener {
  lateinit var items: MutableList<ItemModel>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recycler_view)

    items = mutableListOf<ItemModel>()
    for (i in 0..27)
      items.add(
        ItemModel("Item $i",
          resources.getIdentifier("thumb$i", "drawable", packageName))
      )

    val adapter = ItemAdapter(items, this)

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)

    findViewById<Button>(R.id.button_add).setOnClickListener {
      items.add(1, ItemModel("NEW ITEM", R.drawable.thumb10))
      adapter.notifyItemInserted(1)
    }

    findViewById<Button>(R.id.button_remove).setOnClickListener {
      items.removeAt(1)
      adapter.notifyItemRemoved(1)
    }

    findViewById<Button>(R.id.button_update).setOnClickListener {
      items[1].title = "UPDATED"
      adapter.notifyItemChanged(1)
    }
  }

  override fun ItemClicked(position: Int) {
    Log.v("TAG", "${items[position].title}")
  }
}