package things.Cells

import State
import isInRange
import things.Microorganisms

abstract class Cell : Microorganisms {
    abstract override val state : State
    companion object {
        val cellList = mutableListOf<Cell>()

        fun getNearbyCells(x: Double, y: Double, range: Double) =
            cellList.filter { isInRange(x,y,it.state.x,it.state.y,range) }
    }

    init {
        cellList.add(this)
    }

    /**
     * 평범하게 구현좀..
     */
    abstract val taskToDo : ArrayList<Task>

    /**
     * Task 추가 로직
     */
    fun addTask(task: Task){
        taskToDo.add(task)
    }
}