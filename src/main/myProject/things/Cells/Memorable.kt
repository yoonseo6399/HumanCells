package things.Cells

import kotlinx.coroutines.delay
import things.Invaders.Invader
import things.Microorganisms

abstract class Memorable<T: Invader> {
    abstract val memory: ArrayList<Microorganisms>
    suspend fun memorize(victim: Microorganisms){
        delay(100)
        memory.add(victim)
    }
}