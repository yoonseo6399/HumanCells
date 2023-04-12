import kotlinx.coroutines.*
import things.Cells.Activatable
import things.Cells.Cell
import things.Cells.Macrophage
import things.Cells.Tasks

fun main(args: Array<String>) {
    val job = Job()
    Scheduler.instance = Scheduler(CoroutineScope(Dispatchers.Default + job))
    val a = listOf(Macrophage.create(),Macrophage.create(),Macrophage.create(),Macrophage.create())
    a.forEach { it.isActivated = true }
    a.forEach {
        it.state.x=(Math.random()*10).toInt()
        it.state.y=(Math.random()*10).toInt()
    }

    for(i in a.indices){
        a.get(0).addTask(Tasks.Attack(victim = a.get(i)))
    }




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