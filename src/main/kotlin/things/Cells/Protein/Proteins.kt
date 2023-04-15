package things.Cells.Protein

import Location
import things.Cells.Cell
import things.Cells.Protein.Combinable.Companion.combineMap
import things.Microorganisms

class Proteins {
    class Target(val targetX: Double, val targetY: Double) : Protein {
        constructor(target: Microorganisms) : this(target.state.x,target.state.y)


        override fun activation() {
            Cell.getNearbyCell(location.x,location.y,)
        }
    }

    class complements{
        class C3 : Protein("C3"), Combinable {
            init{
                combineMap.put(this,listOf(C3a::class,C3b::class))
            }
            override fun process(cells: List<Protein>) {

            }
        }
        class C3a : Protein("C3a"){
            override fun process(cells: List<Protein>) {
                TODO("Not yet implemented")
            }

        }
        class C3b : Protein("C3b"){
            override fun process(cells: List<Protein>) {
                TODO("Not yet implemented")
            }
        }
    }
    companion object{
        val C3 = complements.C3() as Protein
        val C3a = complements.C3a() as Protein
    }
}