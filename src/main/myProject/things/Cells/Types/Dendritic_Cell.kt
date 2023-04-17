package things.Cells.Types

import State
import things.Cells.*

class Dendritic_Cell : Cell(), Movable {
    override val taskToDo: ArrayList<Task>
        get() = TODO("Not yet implemented")

    override fun work() {
        TODO("Not yet implemented")
    }

    override val attribute: Attribute
        get() = TODO("Not yet implemented")
    override val state: State
        get() = TODO("Not yet implemented")

    override fun attacked(attacker: Attackable): Boolean {
        TODO("Not yet implemented")
    }

    override fun die(reason: String) {
        TODO("Not yet implemented")
    }

    override suspend fun move(x: Double, y: Double) {
        TODO("Not yet implemented")
    }

}