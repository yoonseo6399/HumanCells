package things.Cells.Protein

class Reaction<T : Any>(result: List<Protein>) {
    constructor(result: Protein): this(listOf(result))
}