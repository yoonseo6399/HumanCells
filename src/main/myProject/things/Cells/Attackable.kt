package things.Cells

import things.Microorganisms

interface Attackable : Movable, Microorganisms {
    val attackStat: AttackStat<Microorganisms>

    /**
     * Attack 메커니즘 구현해라.
     * @param victim 희생자
     */
    suspend fun attack(victim: Microorganisms)
}