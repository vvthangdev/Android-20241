package vn.edu.hust.listexamples.models

data class StudentModel(val hoten: String, val mssv: String, val avatarResId: Int) {
  var selected: Boolean = false
}
