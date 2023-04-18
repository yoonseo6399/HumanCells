package things.Cells.Protein

class ProteinBuilder {
    val proteins= ArrayList<Protein>()
    fun create(protein: Protein) {
        val temp = protein.createInstance()
        proteins.add(temp)
    }
}