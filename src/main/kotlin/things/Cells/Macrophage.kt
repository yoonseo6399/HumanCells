package things.Cells

import CellState
import Scheduler
import kotlinx.coroutines.runBlocking
import log
import things.Microorganisms

class Macrophage(val name: String,val state: CellState) : Cell(), Attackable , Reproducible,Activatable,Movable{
    companion object{
        var count = 0
        val list = ArrayList<Macrophage>()
        fun create(cellState: CellState): Macrophage {
            val temp = Macrophage("Macrophage$count",cellState)
            count++
            list.add(temp)
            log("created $temp")
            return temp
        }
        fun create(): Macrophage {
            return create(CellState(0,0,null))
        }
    }
    val eattenCells = ArrayList<Microorganisms>()
    override var isActivated: Boolean = false
    override suspend fun move(x: Int, y: Int) {
        state.x = x
        state.y = y
    }


    override fun toString(): String {
        return "Macrophage:$name"
    }

    override fun produce(): Cell {
        return create(CellState(state.x,state.y, null))
    }

    override val taskToDo: ArrayList<Task> = ArrayList()
    override fun work() {
        Scheduler.instance.createRepeatingTask(1000L){
            if(isActivated){
                eattenCells.removeAt(1)
                runBlocking {
                    taskToDo.get(0).execute(this@Macrophage)
                }
                taskToDo.removeAt(0)
            }
        }
    }



    override fun attacked(attacker: Attackable): Boolean {
        TODO("Not yet implemented")
    }

    override fun attack(thing: Microorganisms) {

        if(thing.attacked(this)) eattenCells.add(thing)

    }
}