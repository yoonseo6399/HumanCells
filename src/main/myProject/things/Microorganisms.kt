package things

import State
import things.Cells.Attackable
import things.Cells.Attribute
import things.Cells.Protein.Receptors.Receptor

interface Microorganisms {
    /**
     * 비동기적으로 스레드를 만들던 해서 1초당 한번 연산으로 수행좀.
     * 개선필요함.
     */
    fun work()
    val attribute : Attribute
    val state : State
    val receptors: List<Receptor>

    /**
     * 이 객체가 attack 받으면 일어나는 로직
     */
    fun attacked(attacker: Attackable): Boolean


    /** 저장한거든 작업이던 다 취소해서 갈비지 컬렉터가 가져가게 해주셈.
     *
     */
    fun die(reason: String)
}