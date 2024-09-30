import kotlin.math.sqrt

fun main() {
//giai phuong trinh bac 2
    var a = 1.0
    var b = -2.0
    var c = -3.0

    if(a == 0.0) {
        if (b == 0.0) {
            if (c == 0.0)
                println("PT co vo so nghiem")
            else
                println("Phuong trinh vo nghiem")
        } else
            println("PT co nghiem don x = ${-c / b}")
    }
    else {
        var delta = b * b - 4 * a * c
        if (delta < 0.0)
            println("PT vo nghiem")
        else if ((delta == 0.0))
                println("PT co nghiem kep x = ${-b / (2 * a)}")
            else
                println("PT co hai nghiem x1 = ${(-b - sqrt(delta)) / (2 * a)} va x2 = ${(-b + sqrt(delta)) / (2 * a)}")

    }
}