package things.Cells.Protein

import things.Cells.Cell
import things.Cells.Protein.Reactable.Companion.combineMap
import things.Microorganisms

class Proteins {
    class Target(val targetX: Double, val targetY: Double) : Protein("Target") {
        constructor(target: Microorganisms) : this(target.state.x,target.state.y)



        override fun create(): Protein {
            TODO("Not yet implemented")
        }

        override fun process() {
            TODO("Not yet implemented")
        }
    }

    class complements{
        class C3 : Protein("C3"), Reactable {
            init{
                combineMap.put(this,Reaction<Protein>(listOf(C3)))
            }

            override fun create(): Protein =
                C3()
            override fun process() {

            }
        }

//        class C3a : Protein("C3a"){
//
//
//        }
//        class C3b : Protein("C3b"){
//
//        }
        class C3b : Protein("C3b"),Reactable{
          override fun create(): Protein {
              TODO("Not yet implemented")
          }

          override fun process() {
              TODO("Not yet implemented")
          }
        }
    }
    companion object{
        val C3 = complements.C3() as Protein
        //val C3a = complements.C3a() as Protein
    }
}