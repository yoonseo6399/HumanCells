package things.Cells

interface Movable {
    suspend fun move(x: Int, y: Int)
}