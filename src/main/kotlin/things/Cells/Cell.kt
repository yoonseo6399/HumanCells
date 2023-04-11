package things.Cells

import things.Microorganisms

abstract class Cell : Microorganisms {
    companion object {
        val cellList = mutableListOf<Cell>()
    }

    init {
        cellList.add(this)
    }
    abstract val taskToDo : ArrayList<Task>
    fun addTask(task: Task){
        taskToDo.add(task)
    }
}