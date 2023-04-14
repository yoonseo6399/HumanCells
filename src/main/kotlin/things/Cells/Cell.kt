package things.Cells

import State
import things.Microorganisms

abstract class Cell : Microorganisms {
    companion object {
        val cellList = mutableListOf<Cell>()
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