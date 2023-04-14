package things.Cells

import kotlinx.coroutines.runBlocking
import things.Microorganisms

/**
 * Protein 과 연결고리 만들어야함... but how....?
 */
class Tasks {

    data class Moving(val moveToX: Double,val moveToY: Double): Task("MovingTask") {
        override suspend fun execute(cell: Cell) {
            if(cell is Movable) {
                cell.move(moveToX,moveToY)
            }
        }
    }
    data class Attack(val victim: Microorganisms):Task("AttackTask") {
        override suspend fun execute(cell: Cell) {
            if(cell is Attackable){
                runBlocking {
                    cell.move(victim.state.x, victim.state.y)
                }
                cell.attack(victim)
            }
        }
    }
    /**
     * 뭐로 바뀔껀지 어케 정하지
     */
    data class TransformTo(val cell: Cell): Task("TransformTask") {
            override suspend fun execute(cell: Cell) {
                if(cell is Transformable) cell.transform(cell)
            }
        }

}