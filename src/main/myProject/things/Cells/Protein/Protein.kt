package things.Cells.Protein

import Location
import isInRange
import things.Cells.Cell

abstract class Protein(var proteinName: String/*vararg val proteinTypes: ProteinType*/) {

    var location: Location = Location.DEFAULT
    var range = 0.0
    val producedFrom = Location.DEFAULT
    companion object{
        val list = ArrayList<Protein>()
        fun getNearbyProtein(x: Double,y: Double,range: Double) =
            list.filter { isInRange(x,y,it.location.x,it.location.y,range) }



        // DNA 도 프로틴이라 굳이 Protein class 와 합칠필요가 없음 : Reactable
        fun process(){
            for (protein in list) {
                if(protein is ReactableProtein){
                    for (reaction in protein.reactions) {
                        list.addAll(reaction.react())
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


    override fun equals(other: Any?): Boolean {
        return if(other == null) false else if(other is Protein && this.proteinName == other.proteinName) true else false
    }
}
data class ProteinType(val name: String){
    companion object{
        val ACTIVATION = ProteinType("activation")
    }
}