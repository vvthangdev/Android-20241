package vn.edu.hust.listexamples.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import vn.edu.hust.listexamples.R
import vn.edu.hust.listexamples.models.StudentModel

class StudentAdapter(val students: List<StudentModel>): BaseAdapter() {
  override fun getCount(): Int = students.size

  override fun getItem(position: Int): Any = students[position]

  override fun getItemId(position: Int): Long = position.toLong()

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val itemView: View
    val viewHolder: ViewHolder

    if (convertView == null) {
      itemView = LayoutInflater.from(parent?.context)
        .inflate(R.layout.layout_student_item, parent, false)
      viewHolder = ViewHolder(itemView)
      itemView.tag = viewHolder
    } else {
      itemView = convertView
      viewHolder = itemView.tag as ViewHolder
    }

    val student = students[position]
    viewHolder.imageAvatar.setImageResource(student.avatarResId)
    viewHolder.textHoten.text = student.hoten
    viewHolder.textMssv.text = student.mssv
    viewHolder.checkSelected.isChecked = student.selected

    viewHolder.checkSelected.setOnClickListener {
      student.selected = viewHolder.checkSelected.isChecked
      notifyDataSetChanged()
    }

    return itemView
  }

  class ViewHolder(itemView: View) {
    val imageAvatar: ImageView
    val textHoten: TextView
    val textMssv: TextView
    val checkSelected: CheckBox
    init {
      imageAvatar = itemView.findViewById(R.id.image_avatar)
      textHoten = itemView.findViewById(R.id.text_hoten)
      textMssv = itemView.findViewById(R.id.text_mssv)
      checkSelected = itemView.findViewById(R.id.check_selected)
    }
  }
}