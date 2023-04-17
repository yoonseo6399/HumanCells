import kotlinx.coroutines.*
import things.Cells.Task

class Scheduler(val scope: CoroutineScope) {

    companion object{
        var TaskMap = HashMap<Long,Job>()
        @OptIn(DelicateCoroutinesApi::class)
        var instance = Scheduler(GlobalScope)
    }
    var lastTaskId:Long = 0L

    /**
     * 지연된 연산 수행 - 비동기
     */
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

    /**
     * 단순 반복하는 Task 를 만듬.
     * @param repeatDelay 반복속도
     * @param startDelay 시작할떄 딜레이
     * @return Job 을 리턴해서 cancel 가능.
     */
    fun createRepeatingTask(repeatDelay: Long, startDelay: Long = 0L, action: (Int) -> Unit):Job {
        log("created RepeatingTask $action")
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