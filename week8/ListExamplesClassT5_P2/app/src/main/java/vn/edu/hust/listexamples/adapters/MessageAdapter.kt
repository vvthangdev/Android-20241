package vn.edu.hust.listexamples.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import vn.edu.hust.listexamples.models.MessageModel
import vn.edu.hust.listexamples.R

class MessageAdapter(val messages: List<MessageModel>): BaseAdapter() {
  override fun getCount(): Int = messages.size

  override fun getItem(position: Int): Any = messages[position]

  override fun getItemId(position: Int): Long = position.toLong()

  override fun getViewTypeCount(): Int = 2

  override fun getItemViewType(position: Int): Int {
    if (messages[position].username.equals("me"))
      return 1
    else
      return 0
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val itemView: View
    val viewHolder: ViewHolder

    if (convertView == null) {
      if (getItemViewType(position) == 0)
        itemView = LayoutInflater.from(parent?.context)
          .inflate(R.layout.layout_item_left, parent, false)
      else
        itemView = LayoutInflater.from(parent?.context)
          .inflate(R.layout.layout_item_right, parent, false)
      viewHolder = ViewHolder(itemView)
      itemView.tag = viewHolder
    } else {
      itemView = convertView
      viewHolder = itemView.tag as ViewHolder
    }

    val message = messages[position]
    viewHolder.imageAvatar.setImageResource(message.avatarResId)
    viewHolder.textMessage.text = message.message

    return itemView
  }

  class ViewHolder(itemView: View) {
    val imageAvatar: ImageView
    val textMessage: TextView
    init {
      imageAvatar = itemView.findViewById(R.id.image_avatar)
      textMessage = itemView.findViewById(R.id.text_message)
    }
  }
}