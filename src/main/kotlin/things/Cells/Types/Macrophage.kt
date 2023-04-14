package things.Cells.Types

import State
import Scheduler
import annotations.NeedToSee
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import log
import logWithPrefix
import things.Cells.*
import things.Microorganisms
import java.lang.Math.sqrt
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Macrophage(val name: String, override val state: State ) : Cell(), Attackable, Reproducible, Activatable, Movable {


    val a = object : AttackStat<Microorganisms>(1000F, AttackType.Melting(100F)) {
        override fun attack(victim: Microorganisms) {
            victim.attacked(this@Macrophage)
        }
    }

    override val attribute: Attribute = Attribute(1f,200f)
    override val attackStat: AttackStat<Microorganisms> = a

    companion object{
        @NeedToSee("Cell 로 옮길까요?")
        var count by Delegates.observable(1){ kProperty: KProperty<*>, i: Int, i1: Int ->
            logWithPrefix(i1.toString())
        }
        val list = ArrayList<Macrophage>()

        fun create(cellState: State): Macrophage {
            val temp = Macrophage("Macrophage$count",cellState)
            count++
            list.add(temp)
            log("created $temp")
            return temp
        }
        fun create(): Macrophage {
            val temp = create(State(0.0,0.0,null))
            temp.work()

            return temp
        }
    }
    val eattenCells = ArrayList<Microorganisms>()
    override var isActivated: Boolean = false




    override suspend fun move(x: Double, y: Double) {
        val dx = x - state.x
        val dy = y - state.y
        val distance = sqrt(dx * dx + dy * dy)
        val time = distance / 1.0f // 1m/s
        // 1초마다 위치 업데이트
        val interval = 1000L // 1초
        val steps = (time * 1000 / interval).toInt() // time을 ms로 변환하고 interval로 나누어 step 수 계산
        val xStep = dx / steps
        val yStep = dy / steps
        for (i in 1..steps) {
            log("moved to ${state.x}, ${state.y}")
            state.x += xStep
            state.y += yStep
            delay(interval)
        }


    }


    override fun toString(): String {
        return "Macrophage:$name"
    }

    override fun produce(): Cell {
        return create(State(state.x,state.y, null))
    }

    override val taskToDo: ArrayList<Task> = ArrayList()
    var workJob: Job? = null
    override fun work() {
        workJob = Scheduler.instance.createRepeatingTask(1000L){
            if(isActivated){
                if(!eattenCells.isEmpty()){
                    eattenCells.get(0).die("$this kill you AttackType: ${attackStat.type}")
                    eattenCells.removeAt(0)
                }
                //log("working ${this@Macrophage}")
                runBlocking {
                    if(!taskToDo.isEmpty()) {
                        taskToDo.get(0).execute(this@Macrophage)
                        taskToDo.removeAt(0)
                    }
                }
            }
        }
    }



    override fun attacked(attacker: Attackable): Boolean {
        state.focused = attacker
        return true
    }

    override fun die(reason: String) {
        list.remove(this)
        cellList.remove(this)
        workJob?.cancel()
        log("$this died by $reason")
    }



    override suspend fun attack(victim: Microorganisms) {
        delay(1000L)
        state.focused = victim
        victim.attacked(this@Macrophage)
        if(victim.attacked(this)) eattenCells.add(victim)
    }
}