import things.Microorganisms

data class State(var x: Double, var y: Double, var focused: Microorganisms?) : Cloneable{
    companion object{
        val DEFAULT = State(0.0,0.0,null)
    }

    public override fun clone(): State {
        return State(x,y,focused)
    }
}
