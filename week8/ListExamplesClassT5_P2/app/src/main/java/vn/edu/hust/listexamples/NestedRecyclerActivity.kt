package vn.edu.hust.listexamples

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.listexamples.adapters.MainAdapter
import vn.edu.hust.listexamples.models.ItemModel

class NestedRecyclerActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nested_recycler)

    val itemss = mutableListOf<List<ItemModel>>()

    val items1 = mutableListOf<ItemModel>()
    items1.add(ItemModel("Item 11", R.drawable.thumb0))
    items1.add(ItemModel("Item 12", R.drawable.thumb1))
    items1.add(ItemModel("Item 13", R.drawable.thumb2))
    items1.add(ItemModel("Item 14", R.drawable.thumb3))
    items1.add(ItemModel("Item 15", R.drawable.thumb4))
    itemss.add(items1)

    val items2 = mutableListOf<ItemModel>()
    items2.add(ItemModel("Item 21", R.drawable.thumb6))
    items2.add(ItemModel("Item 22", R.drawable.thumb7))
    items2.add(ItemModel("Item 23", R.drawable.thumb8))
    items2.add(ItemModel("Item 24", R.drawable.thumb9))
    items2.add(ItemModel("Item 25", R.drawable.thumb10))
    itemss.add(items2)

    val items3 = mutableListOf<ItemModel>()
    items3.add(ItemModel("Item 31", R.drawable.thumb10))
    items3.add(ItemModel("Item 32", R.drawable.thumb11))
    items3.add(ItemModel("Item 33", R.drawable.thumb12))
    items3.add(ItemModel("Item 34", R.drawable.thumb13))
    items3.add(ItemModel("Item 35", R.drawable.thumb14))
    itemss.add(items3)

    val items4 = mutableListOf<ItemModel>()
    items4.add(ItemModel("Item 41", R.drawable.thumb16))
    items4.add(ItemModel("Item 42", R.drawable.thumb17))
    items4.add(ItemModel("Item 43", R.drawable.thumb18))
    items4.add(ItemModel("Item 44", R.drawable.thumb19))
    items4.add(ItemModel("Item 45", R.drawable.thumb20))
    itemss.add(items4)

    val adapter = MainAdapter(itemss)

    val mainRecyclerView = findViewById<RecyclerView>(R.id.main_recycler)
    mainRecyclerView.adapter = adapter
    mainRecyclerView.layoutManager = LinearLayoutManager(this)
  }
}