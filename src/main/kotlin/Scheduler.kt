import kotlinx.coroutines.*
import things.Cells.Task

class Scheduler(val scope: CoroutineScope) {

    companion object{
        var TaskMap = HashMap<Long,Job>()
        @OptIn(DelicateCoroutinesApi::class)
        var instance = Scheduler(GlobalScope)
    }
    var lastTaskId:Long = 0L

    fun createDelayedTask(delay: Long, action: () -> Unit): Job {
        val job = scope.launch {
            try{
                println("DelayedTask added")
                delay(delay)
                action.invoke()
            }catch (_: Exception){

            }
        }
        TaskMap.put(lastTaskId,job)
        lastTaskId++
        return job
    }

    fun createRepeatingTask(repeatDelay: Long, startDelay: Long = 0L, action: (Int) -> Unit):Job {
        val job = scope.launch {
            delay(startDelay)
            var count = 1
            while (true){
                action(count)
                count++
                delay(repeatDelay)
            }
        }
        TaskMap.put(lastTaskId,job)
        lastTaskId++
        return job
    }
    fun cancelAllTasks(){
        TaskMap.forEach { (_, j) -> j.cancel() }
    }
}