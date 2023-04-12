package things

import State
import things.Cells.Attackable
import things.Cells.Attribute

interface Microorganisms {
    fun work()
    val attribute : Attribute
    val state : State

    fun attacked(attacker: Attackable): Boolean
    fun die(reason: String)
}