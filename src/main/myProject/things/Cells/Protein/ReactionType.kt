package things.Cells.Protein

import things.Cells.Cell

open class Reaction<T : Any>(val reactionType: ReactionType,val action: ProteinBuilder.() -> Unit) {

//    constructor(reactionType: ReactionType,action: Protein.() -> Unit): this(reactionType,action.invoke(ProteinBuilder<*>()))
    fun react(): List<Protein>{
        val builder = ProteinBuilder()
        action.invoke(builder)
        return builder.proteins
    }


}
data class ReactionType(val reactionName: String){
    companion object{
        val ACTIVATIaON = ReactionType("activation")
        val SEPARATE = ReactionType("separate")
        val COMBINE = ReactionType("combine")

//        class ACTIVATION<T: Protein> : Reaction<T>()
    }
}