package things.Cells.Protein


import things.Cells.Attackable
import things.Cells.Cell
import things.Cells.Task
import things.Cells.Tasks
import things.Invaders.Invader
import things.Microorganisms

class Proteins {
    class Target(val targetX: Double, val targetY: Double) : Protein("Target") {
        constructor(target: Microorganisms) : this(target.state.x,target.state.y)


        override fun process() {
            TODO("Not yet implemented")
        }
    }

    class complements{
        class C3 : ReactableProtein("C3") {
            init{
                this addReaction Reaction<Protein>(this@C3,ReactionType.SEPARATE){
                    createProtein(C3)
                    createProtein(C3b)
                }
            }

            override fun process() {

            }
        }
        class C3b : ReactableProtein("C3b") {
            var anchored: Microorganisms? = null
            init {
                this addReaction Reaction<Invader>(this@C3b,ReactionType.ANCHORING){
                    // invader 만들어야할듯 시발
                    //anchoring logic
                    //지금은 Cell 대상으로 함
                    nearCells.forEach {
                        anchored = it
                    }
                }
                this addReaction Reaction<Protein>(this@C3b,ReactionType.COMBINE_PROTEIN){
                    
                }
            }
            override fun process() {
                Scheduler.instance.createRepeatingTask(1000L,0){

                }
            }
        }
        class C3a : ReactableProtein("C3a"){

            init{
                range = 1.0;
                this addReaction Reaction<Cell>(this@C3a,ReactionType.ACTIVATION){
                    for (cell in nearCells) {
                        interact(cell)
                    }
                }
                this addReaction Reaction<Cell>(this@C3a,ReactionType.ORDER){
                    nearCells.forEach {
                        if(it is Attackable) it.addTask(Tasks.Moving(producedFrom.x,producedFrom.y)) //수정이 필요하다는 직감
                    }
                }
            }
            override fun process() {
                TODO("Not yet implemented")
            }
        }
    }
    companion object{
        val C3 = complements.C3()
        val C3b = complements.C3b()
        val C3a = complements.C3a()
    }
}