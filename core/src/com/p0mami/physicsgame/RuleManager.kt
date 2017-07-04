package com.p0mami.physicsgame

/**
 * Created by Mark on 01.07.2017.
 */

class RuleManager/*: Runnable*/{
    /*var flag = true
    override fun run() {
        while(flag){
            Calculation()
        }
    }*/

    companion object {
        var Units = mutableListOf<Unit>()
        var Rules = mutableListOf<Rule>()
    }
    fun setRules(){
        Rules.add(Gravitation())
        Rules.add(Impulse())
        /*run()*/
    }

    fun Calculation(){
        for(u1 in Units)
            for(u2 in Units)
                if(!u1.equals(u2))
                    for(r in Rules)
                        r.result(u1, u2)
    }
}