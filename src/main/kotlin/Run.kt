import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    print("a")
    runBlocking {
        val job1 = Scheduler.getInstance().createDelayedTask(1000L){
            println("aaaaaaaaa!")
        }
        val job = Scheduler.getInstance().createDelayedTask(1000L){
            println("aaaaaaaaaaaaaa!")
        }
        job1.cancel()
        job.join()
    }

}