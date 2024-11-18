package vn.edu.hust.listexamples

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import vn.edu.hust.listexamples.adapters.MessageAdapter
import vn.edu.hust.listexamples.models.MessageModel

class ChatActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_chat)

    val messages = mutableListOf<MessageModel>()
    messages.add(MessageModel("me", "Hello", R.drawable.thumb1))
    messages.add(MessageModel("friend", "Hi", R.drawable.thumb2))
    messages.add(MessageModel("me", "How are you?", R.drawable.thumb1))
    messages.add(MessageModel("friend", "I'm fine. Thanks.", R.drawable.thumb2))

    val adapter = MessageAdapter(messages)

    val listMessages = findViewById<ListView>(R.id.list_messages)
    listMessages.adapter = adapter
  }
}