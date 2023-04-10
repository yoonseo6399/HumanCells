package things.Cells

import Scheduler
import things.Microorganisms

class Macrophage : Cell, Attackable {
    val eattenCells = ArrayList<Microorganisms>()









    override fun work() {
        val job = Scheduler.getInstance().createDelayedTask(1000L){
            println("aaaaaaaaaaaaaa!")
        }
    }


    override fun attacked(attacker: Attackable): Boolean {
        TODO("Not yet implemented")
    }

    override fun attack(thing: Microorganisms) {

        if(thing.attacked(this)) eattenCells.add(thing)

    }
}