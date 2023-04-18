package things.Cells.Protein

import things.Cells.Protein.Reactable.Companion.combineMap
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
                addReaction Reaction<Protein>(ReactionType.SEPARATE){
                    create(C3)
                    create(C3b)
                }
            }

            override fun process() {

            }
        }
        class C3b : Protein("C3b"),Reactable{

            override fun process() {
                TODO("Not yet implemented")
            }


        }
    }
    companion object{
        val C3 = complements.C3()
        val C3b = complements.C3b()
        //val C3a = complements.C3a() as Protein
    }
}