import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import things.Cells.Cell
import things.Cells.Macrophage

fun main(args: Array<String>) {
    print("a")
    log("a!")
    Macrophage.create()
    Macrophage.create()
    Macrophage.create()
    Macrophage.create()
    println(Cell.cellList.size)



}


fun log(message: String){
    val onDebug = true
    if(onDebug) println("Thread:${Thread.currentThread()} ${
        with(Thread.currentThread()) {
            if (stackTrace.size < 3) "Main" else stackTrace[2]
        }} $message")
}