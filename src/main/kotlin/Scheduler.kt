import kotlinx.coroutines.*
class Scheduler {
    companion object{
        private var instance: Scheduler? = null

        fun create(): Scheduler {
            instance = Scheduler()
            return instance as Scheduler
        }

        fun getInstance(): Scheduler {
            if(instance == null) create()
            return instance as Scheduler
        }
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
    fun createRepeatingTask(action: () -> Unit, repeatDelay: Long, startDelay: Long = 0L):Job =
        GlobalScope.launch {
            delay(startDelay)
            while (true){
                action()
                delay(repeatDelay)
            }
        }
}