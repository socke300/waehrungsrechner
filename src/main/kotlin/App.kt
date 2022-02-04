import io.javalin.Javalin
import io.javalin.core.JavalinConfig
import java.math.RoundingMode
import java.text.DecimalFormat

class App {
    init {
        var currency = mutableMapOf<String, Float>(
            "US-Dollar" to 1.12F,
            "Yen" to 120.72F,
            "Zloty" to 4.46F
        )
        var app: Javalin = Javalin.create() { t: JavalinConfig -> t.addStaticFiles("/public") }.start(7070)

        app.get("/sendValue") { ctx ->
            var exchangeRate = currency[ctx.queryParam("select")]
            var euro2: Float = ctx.queryParam("1")!!.toFloat()
            var result = euro2 * exchangeRate!!
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            println("test")
            ctx.result("" + df.format(result))
        }
    }
}

fun main() {
    App()
}