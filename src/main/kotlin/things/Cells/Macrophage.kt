package things.Cells

import State
import Scheduler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import log
import things.Microorganisms

class Macrophage(val name: String, override val state: State) : Cell(), Attackable , Reproducible,Activatable,Movable{


    val a = object : AttackStat<Microorganisms>(1000F,AttackType.Melting(100F)) {
        override fun attack(victim: Microorganisms) {
            victim.attacked(this@Macrophage)
        }
    }
    override val attribute: Attribute = Attribute(1f,200f,a)


    companion object{
        var count = 0
        val list = ArrayList<Macrophage>()
        fun create(cellState: State): Macrophage {
            val temp = Macrophage("Macrophage$count",cellState)
            count++
            list.add(temp)
            log("created $temp")
            return temp
        }
        fun create(): Macrophage {
            val temp = create(State(0,0,null))
            temp.work()

            return temp
        }
    }
    val eattenCells = ArrayList<Microorganisms>()
    override var isActivated: Boolean = false

    override suspend fun move(x: Int, y: Int) {
        delay(500L)
        log("moved to $x, $y")
        state.x = x
        state.y = y
    }


    override fun toString(): String {
        return "Macrophage:$name"
    }

    override fun produce(): Cell {
        return create(State(state.x,state.y, null))
    }

    override val taskToDo: ArrayList<Task> = ArrayList()
    override fun work() {
        Scheduler.instance.createRepeatingTask(1000L){
            if(isActivated){
                if(!eattenCells.isEmpty()){
                    eattenCells.get(0).die("$this kill you AttackType: ${attribute.attackStat.type}")
                    eattenCells.removeAt(0)
                }
                log("working")
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
        log("$this died by $reason")
    }

    override suspend fun attack(victim: Microorganisms) {
        state.focused = victim
        victim.attacked(this@Macrophage)
        if(victim.attacked(this)) eattenCells.add(victim)
    }
}