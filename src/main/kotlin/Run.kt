import annotations.RunAnnotations
import kotlinx.coroutines.*
import things.Cells.Types.Macrophage
import java.awt.Point
import java.lang.Math.sqrt
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sqrt

fun main(args: Array<String>) {

    RunAnnotations().run()


    val job = Job()
    Scheduler.instance = Scheduler(CoroutineScope(Dispatchers.Default + job))
//    val a = ArrayList<Macrophage>()
//
//    for(i in 0..30){
//        a.add(Macrophage.create())
//    }
//
//    a.forEach { it.isActivated = true }
//    a.forEach {
//        it.state.x=(Math.random()*10).toDouble()
//        it.state.y=(Math.random()*10).toDouble()
//    }
//    Frame()
//    for(i in a.indices){
//        if(i == 0) continue
//        a.get(0).addTask(Tasks.Attack(victim = a.get(i)))
//    }
    Macrophage.create()




    runBlocking {
        job.join()
    }
    Scheduler.instance.cancelAllTasks()
}


fun log(message: String){
    val onDebug = true
    if(onDebug) println("Thread:${Thread.currentThread()} ${
        with(Thread.currentThread()) {
            if (stackTrace.size < 3) "Main" else stackTrace[2]
        }} $message")
}

fun logWithPrefix(message: String){
    val sdf = SimpleDateFormat("[hh:mm:ss]")

    println(
        "${sdf.format(Date())} $message"
    )


}


/*
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15





 */
/**
 * 그냥 range 안에 있으면 true ㅇㅋ?
 */
fun isInRange(x1: Double, y1: Double, x2: Double, y2: Double, range: Double): Boolean{
    val distance = kotlin.math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    return distance <= range
}