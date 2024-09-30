fun main() {
    // Lambda function to convert a string to lowercase
    val enc1 = { s: String -> s.lowercase() }

    // Call encodeMsg with the lambda and the regular function
    println(encodeMsg(msg = "Hello World", enc1))
    println(encodeMsg(msg = "Hello World", ::enc2))
}

// Function that takes a string and a function to encode it
fun encodeMsg(msg: String, encoder: (String) -> String): String {
    return encoder(msg)
}

// Regular function to convert a string to uppercase
fun enc2(s: String): String = s.uppercase()
