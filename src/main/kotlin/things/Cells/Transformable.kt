package things.Cells

interface Transformable {
    fun <T: Cell> transform(cell: T)
}