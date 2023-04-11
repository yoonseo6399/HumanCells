package things.Cells

import things.Microorganisms

class Tasks {
    companion object{
        data class Moving(val moveToX: Int,val moveToY: Int): Task("MovingTask") {
            override suspend fun execute(cell: Cell) {
                if(cell is Movable) cell.move(moveToX,moveToY)
            }
        }
        data class Attack(val victim: Microorganisms):Task("AttackTask") {
            override suspend fun execute(cell: Cell) {
                if(cell is Attackable) cell.attack(victim)
            }
        }
        data class TransformTo(val cell: Cell): Task("TransformTask") {
            override suspend fun execute(cell: Cell) {
                if(cell is Transformable) cell.transform(cell)
            }
        }
    }
}