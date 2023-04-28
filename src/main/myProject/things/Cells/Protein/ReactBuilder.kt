package things.Cells.Protein

import things.Cells.Cell
import things.Cells.Protein.Receptors.Receptor

/**
 * Reaction 에서 쓰이는 람다전용
 * @param T 반응할 대상
 * @param protein 자기자신
 */
class ReactBuilder<T>(val protein: Protein) {
    val proteins= ArrayList<Protein>()
    val nearCells = Cell.getNearbyCells(protein.location.x, protein.location.y, protein.range)
    val nearProteins = Protein.getNearbyProtein(protein.location.x, protein.location.y, protein.range)
    fun createProtein(protein: Protein) {
        val temp = protein.createInstance()
        proteins.add(temp)
    }
    // 이 수용체가 무슨수용체랑 반응하는지 알려줌
    fun interact(receptor: Receptor){
        receptor.interact(protein)
    }
    fun interact(cell: Cell){
        for (receptor in cell.receptors) interact(receptor)
    }
}