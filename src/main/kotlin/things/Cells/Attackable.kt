package things.Cells

import things.Microorganisms

interface Attackable : Movable, Microorganisms {
    suspend fun attack(victim: Microorganisms)
}