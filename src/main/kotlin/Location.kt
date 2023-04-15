import things.Cells.Cell
//조만간 state.x 이거 다 대체될 예정ㅋ
data class Location(var x: Double, var y: Double) {
    companion object{
        val DEFAULT = Location(0.0,0.0)
    }


    fun add(location: Location){
        x += location.x
        y += location.y
    }
    fun subtract(location: Location){
        x -= location.x
        y -= location.y
    }

    fun getNearbyCells(range: Double) = Cell.getNearbyCell(x,y, range)

}