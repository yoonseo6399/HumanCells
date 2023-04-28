package things.Cells.Protein

import things.Microorganisms

open class Reaction<T : Any>(val protein:Protein,val reactionType: ReactionType,val action: ReactBuilder<T>.() -> Unit) {

    fun react(): List<Protein>{
        val builder = ReactBuilder<T>(protein)
        action(builder)
        return builder.proteins
    }
}
data class ReactionType(val reactionName: String){
    companion object{
        val ACTIVATION = ReactionType("activation")
        val SEPARATE = ReactionType("separate")
        val COMBINE_PROTEIN = ReactionType("combine")
        val ORDER = ReactionType("combine")
        val ANCHORING = ReactionType("anchoring")


    }
}