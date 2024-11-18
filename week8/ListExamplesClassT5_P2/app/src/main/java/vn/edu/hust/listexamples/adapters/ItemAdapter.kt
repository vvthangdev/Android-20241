package vn.edu.hust.listexamples.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.listexamples.ItemClickListener
import vn.edu.hust.listexamples.models.ItemModel
import vn.edu.hust.listexamples.R

class ItemAdapter(val items: List<ItemModel>, val listener: ItemClickListener? = null): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
//    Log.v("TAG", "onCreateViewHolder")
    return ItemViewHolder(itemView, listener)
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = items[position]
    holder.imageView.setImageResource(item.imageId)
    holder.textView.text = item.title
//    Log.v("TAG", "onBindViewHolder")
  }

  class ItemViewHolder(itemView: View, listener: ItemClickListener?): RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView
    val textView: TextView
    init {
      imageView = itemView.findViewById(R.id.image_view)
      textView = itemView.findViewById(R.id.text_view)

      itemView.setOnClickListener {
        listener?.ItemClicked(adapterPosition)
      }
    }
  }
}