import kotlinx.coroutines.*
class Scheduler {
    companion object{
        val instance = Scheduler()
    }
    var lastTaskId = 0L
    @OptIn(DelicateCoroutinesApi::class)
    fun createDelayedTask(delay: Long, action: () -> Unit): Job =
        GlobalScope.launch {
            try{
                println("DelayedTask added")
                delay(delay)
                action.invoke()
            }catch (_: Exception){

            }
        }
    @OptIn(DelicateCoroutinesApi::class)
    fun createRepeatingTask(repeatDelay: Long, startDelay: Long = 0L, action: (Int) -> Unit):Job =
        GlobalScope.launch {
            delay(startDelay)
            var count = 1
            while (true){
                action(count)
                count++
                delay(repeatDelay)
            }
        }
}