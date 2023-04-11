package things.Cells

abstract class Task(val name: String) {
    abstract suspend fun execute(cell: Cell)
}