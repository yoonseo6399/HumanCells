package things.Cells.Protein

abstract class ReactableProtein(proteinName : String) : Protein(proteinName), Reactable {
    val reactions = ArrayList<Reaction<*>>()




    class IllegalUsageException(reason: String): RuntimeException(reason)
}
infix fun ReactableProtein.addReaction(reaction : Reaction<*>){ reactions.add(reaction) }