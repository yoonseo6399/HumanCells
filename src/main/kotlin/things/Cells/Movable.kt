package things.Cells

interface Movable {
    suspend fun move(x: Double, y: Double)
}