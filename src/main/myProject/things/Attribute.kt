package things.Cells

import things.Microorganisms

/**
 * memoized cell's Attributes, example:
 * maxhealth
 */
data class Attribute(val movementSpeed: Float, val maxhealth: Float) {
    var health = 0f
    init {
        health = maxhealth
    }
}
open class AttackType{
    class Melting(val meltingSpeed: Float): AttackType(){
        override fun toString(): String {
            return "Melting"
        }
    }
    data class Ordering(val range: Float): AttackType(){
        override fun toString(): String {
            return "Ordering"
        }
    }
    data class Order_SelfDying(val range: Float): AttackType(){
        override fun toString(): String {
            return "Self_Dying"
        }
    }
    class UnAttackable(): AttackType()
}
abstract class  AttackStat<T: Microorganisms>(val cooldown: Float, val type:AttackType){
    abstract fun attack(victim: T)
}