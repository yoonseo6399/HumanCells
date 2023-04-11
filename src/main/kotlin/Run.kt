import kotlinx.coroutines.*
import things.Cells.Activatable
import things.Cells.Cell
import things.Cells.Macrophage
import things.Cells.Tasks

fun main(args: Array<String>) {
    val job = Job()
    Scheduler.instance = Scheduler(CoroutineScope(Dispatchers.Default + job))
    Macrophage.create().addTask(Tasks.Moving(10,10))
    val cell = Cell.cellList[0]
    if(cell is Activatable) cell.isActivated = true

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