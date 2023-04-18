package things.Cells.Protein

import Location
import isInRange
import things.Cells.Cell

abstract class Protein(var proteinName: String) {
    constructor() : this("NoneProtein")

    var location: Location = Location.DEFAULT
    var range = 0.0

    companion object{
        val list = ArrayList<Protein>()
        fun getNearbyProtein(x: Double,y: Double,range: Double) =
            list.filter { isInRange(x,y,it.location.x,it.location.y,range) }



        // DNA 도 프로틴이라 굳이 Protein class 와 합칠필요가 없음 : Reactable
        fun process(){
            for (protein in list) {
                if(protein is ReactableProtein){ // 반응가능한
                    for (reaction in protein.reactions) {
                        reaction.react().let { list.addAll(it) }
                    }
                }
            }
        }
    }
    // 프로틴 만들어질떄마다 list 에 저장!
    init{
        list.add(this)
    }
    fun createInstance(): Protein {
        return this::class.constructors.first().call()
    }
    // 호출할떄 정보 이용해서 Cell 에 nearby 한 거 넣어서 실행좀.
    abstract fun process()
}