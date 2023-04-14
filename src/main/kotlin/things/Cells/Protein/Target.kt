package things.Cells.Protein

import things.Microorganisms

class Target(val targetX: Double, val targetY: Double) : Protein {
    constructor(target: Microorganisms) : this(target.state.x,target.state.y)
}