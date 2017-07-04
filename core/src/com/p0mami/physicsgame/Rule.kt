package com.p0mami.physicsgame

import com.badlogic.gdx.Gdx
import sun.rmi.runtime.Log

/**
 * Created by Mark on 01.07.2017.
 */


fun Double.Pico():  Double = (this * Math.pow(10.toDouble(), -12.toDouble()))
fun Double.Nano():  Double = (this * Math.pow(10.toDouble(), -9.toDouble()))
fun Double.Micro(): Double = (this * Math.pow(10.toDouble(), -6.toDouble()))
fun Double.Milli(): Double = (this * Math.pow(10.toDouble(), -3.toDouble()))
fun Double.Kilo():  Double = (this * Math.pow(10.toDouble(), 3.toDouble()))
fun Double.Mega():  Double = (this * Math.pow(10.toDouble(), 6.toDouble()))
fun Double.Giga():  Double = (this * Math.pow(10.toDouble(), 9.toDouble()))
fun Double.Tera():  Double = (this * Math.pow(10.toDouble(), 12.toDouble()))

interface Rule{

    fun result(u1: Unit, u2: Unit)
    fun check(u1: Unit, u2: Unit): Boolean
}

class Gravitation: Rule{
    val gamma = 667
    override fun result(u1: Unit, u2: Unit) {
        if(check(u1, u2)){

            val acceleration = u2.pos.minus(u1.pos).normalize().multi2(
                    (gamma * u2.mass /
                            ((u2.pos.getDist(u1.pos)+100) *
                            (u2.pos.getDist(u1.pos)+100))
                    ).toFloat()
            )
            if(acceleration.x.isNaN()){
                print("Ops");
            }
            u1.acceleration.add(acceleration)
            //Gdx.app.log("Gravity", "${u1.name} + ${u2.name} acceleration = ${acceleration}")
        }
    }

    override fun check(u1: Unit, u2: Unit): Boolean {
        /*if(u1.pos.getDist(u2.pos)){
            return false
        }*/
        return true
    }
}

class Impulse: Rule{
    val TAG = "Rule"

    fun CCollision(s1: Vec2, s2: Vec2, u1: Unit, u2: Unit){
        var buf: Vec2 = Vec2(0f, 0f);
        buf.set(s1);

        s1.set(s1.multi2(u1.mass - u2.mass)
                .plus(s2.multi2( 2f *  u2.mass ))
                .multi2( 1/(u1.mass + u2.mass)))

        s2.set(s2.multi2(u2.mass - u1.mass)
                .plus(buf.multi2( 2f *  u1.mass ))
                .multi2( 1/(u2.mass + u1.mass) ))
    }

    fun init(u1: Unit, u2: Unit){
        Gdx.app.log(TAG, "Collision ${u1.name} + ${u2.name}: ${u1.collision} + ${u2.collision}")

        val center: Vec2 = u2.pos.minus(u1.pos);
        val ortho: Vec2 = center.normal()

        var sc1: Vec2 = u1.speed.prjc_v(center)
        var sc2: Vec2 = u2.speed.prjc_v(center.multi2(-1f))
        CCollision(sc1, sc2, u1, u2)

        var so1: Vec2 = u1.speed.prjc_v(ortho)
        var so2: Vec2 = u2.speed.prjc_v(ortho)

        u1.speed.set(sc1.plus(so1))
        u2.speed.set(sc2.plus(so2))

        u1.collision = true
        u2.collision = true
    }

    override fun result(u1: Unit, u2: Unit) {
        if(check(u1, u2)){
            init(u1, u2)
        }
    }

    override fun check(u1: Unit, u2: Unit): Boolean {
        if((u1.pos.getDist(u2.pos) > (u1.radius + u2.radius)) || u1.collision || u2.collision)
                return  false
        return true
    }
}