package things

import things.Cells.Attackable

interface Microorganisms {
    fun work()
    fun attacked(attacker: Attackable): Boolean
}