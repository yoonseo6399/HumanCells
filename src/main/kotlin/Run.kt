import annotations.RunAnnotations
import display.img.Frame
import kotlinx.coroutines.*
import things.Cells.Types.Macrophage
import things.Cells.Tasks
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import kotlin.collections.ArrayList

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