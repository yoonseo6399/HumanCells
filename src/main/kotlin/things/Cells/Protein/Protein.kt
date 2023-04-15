package things.Cells.Protein

import Location
import isInRange

abstract class Protein(var proteinName: String) {
    constructor() : this("NoneProtein")

    var location: Location = Location.DEFAULT

    companion object{
        val list = ArrayList<Protein>()
        fun getNearbyProtein(x: Double,y: Double,range: Double) =
            list.filter { isInRange(x,y,it.location.x,it.location.y,range) }

        fun process(){
            list.forEach{
                if(it is Combinable){

                    it.with()
                }
            }
        }
    }
    // 프로틴 만들어질떄마다 list 에 저장!
    init{
        list.add(this)
    }
    // 호출할떄 정보 이용해서 Cell 에 nearby 한 거 넣어서 실행좀.
    abstract fun process(cells: List<Protein>)
}