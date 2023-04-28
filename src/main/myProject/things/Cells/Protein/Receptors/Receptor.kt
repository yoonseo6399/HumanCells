package things.Cells.Protein.Receptors

import things.Cells.Activatable
import things.Cells.Cell
import things.Cells.Protein.Protein
import things.Cells.Protein.Proteins

interface Receptor { // 얘네들이 쏟아져 나온곳으로 역주행해야하는데..?
    val canInteractWith : List<Protein>
    fun interact(protein: Protein)
    infix fun canReact(protein: Protein) = canInteractWith.any {it.equals(protein) }
    companion object{

    }
    class Default{
        class Activation : Receptor{ // receptors.forEach { it.interact(protein) }
            override val canInteractWith = listOf(Proteins.C3a)
            override fun interact(protein: Protein) {
                
            }
        }
    }
}
