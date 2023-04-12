package things.Cells

import things.Microorganisms

data class Attribute(val movementSpeed: Float, val maxhealth: Float, val attackStat: AttackStat<Microorganisms>) {
    var health = 0f
    init {
        health = maxhealth
    }
}
open class AttackType{
    class Melting(val meltingSpeed: Float): AttackType()
    data class Ordering(val range: Float): AttackType()
    data class Order_SelfDying(val range: Float): AttackType()
    class UnAttackable(): AttackType()
}
abstract class  AttackStat<T: Microorganisms>(val cooldown: Float, val type:AttackType){
    abstract fun attack(victim: T)
}