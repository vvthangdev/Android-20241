package vn.edu.hust.listexamples.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.listexamples.R
import vn.edu.hust.listexamples.models.ItemModel

class MainAdapter(val itemss: List<List<ItemModel>>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
  class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val subRecyclerView: RecyclerView
    init {
      subRecyclerView = itemView.findViewById(R.id.sub_recycler)
      subRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.layout_sub_recycler, parent, false)
    return MainViewHolder(itemView)
  }

  override fun getItemCount(): Int = itemss.size

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    val items = itemss[position]
    val adapter = ItemAdapter(items)
    holder.subRecyclerView.adapter = adapter
  }
}