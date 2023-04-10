package things.Cells

import things.Microorganisms

interface Attackable {
    fun attack(thing: Microorganisms)
}