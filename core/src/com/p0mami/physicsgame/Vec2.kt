package com.p0mami.physicsgame

import com.sun.org.apache.xpath.internal.operations.Plus
import java.io.IOException

/**
 * Created by Mark on 29.06.2017.
 */
operator fun Vec2.unaryMinus(): Vec2 {
    return Vec2(-this.x, -this.y)
}



class Vec2 {
    var x: Float = 0.toFloat()
    var y: Float = 0.toFloat()

    fun getDist(b: Vec2): Float{
        return Math.sqrt((this.x - b.x) * (this.x - b.x) + ((this.y - b.y) * (this.y - b.y).toDouble())).toFloat()
    }

    fun setNull(){
        this.x = 0f
        this.y = 0f
    }

    constructor() {
    }

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor(v: Vec2) {
        this.x = v.x
        this.y = v.y
    }


    fun plus(v: Vec2):  Vec2{
        return Vec2(this.x + v.x, this.y + v.y)
    }

    fun minus(v: Vec2):  Vec2{
        return Vec2(this.x - v.x, this.y - v.y)
    }

    fun multi2(v: Float):  Vec2{
        return Vec2(this.x * v, this.y * v)
    }

    fun add(v: Vec2): Vec2 {
        this.x = this.x + v.x
        this.y = this.y + v.y
        return this
    }


    fun multi(k: Float): Vec2 {
        this.x = this.x * k
        this.y = this.y * k
        return this
    }

    override fun toString(): String {
        return "(" +
                x +
                "," + y +
                ')'
    }

    fun subtract(v: Vec2): Vec2 {
        this.x = this.x - v.x
        this.y = this.y - v.y
        return this
    }

    fun add(x: Float, y: Float): Vec2 {
        this.x = this.x + x
        this.y = this.y + y
        return this
    }


    operator fun set(x1: Float, y1: Float): Vec2 {
        this.x = x1
        this.y = y1
        return this
    }

    fun rotate(degree: Float): Vec2 {
        val radians = degreesToRadians * degree
        val cos = Math.cos(radians.toDouble()).toFloat()
        val sin = Math.sin(radians.toDouble()).toFloat()

        val newX = this.x * cos - this.y * sin//* Render.ratio;
        val newY = this.x * sin + this.y * cos

        this.x = newX
        this.y = newY
        return this
    }

    fun set(vec2: Vec2) {
        this.x = vec2.x
        this.y = vec2.y
        //   return this;
    }

    fun multi(vec2: Vec2): Float{
        return this.x * vec2.x + this.y * vec2.y
    }

    fun angle(vec2: Vec2): Float{
        return Math.acos(this.multi(vec2) / (this.length() * vec2.length()).toDouble()).toFloat()
    }

    fun normalize(): Vec2{
        if(this.length() != 0f)
            return Vec2(this.x / this.length(), this.y / this.length())
        else
            return Vec2(0f, 0f)
    }

    fun length(): Float{
        return Math.sqrt((this.x * this.x + this.y * this.y).toDouble()).toFloat();
    }

    fun prjc_f(vec2: Vec2): Float{
        return this.multi(vec2) / vec2.length()
    }

    fun prjc_v(vec2: Vec2): Vec2{
        return vec2.normalize().multi(this.prjc_f(vec2))
    }

    fun normal(): Vec2{
        if(this.x != 0f && this.y != 0f)
            return Vec2(this.y, this.x * -1f)
        else if(this.x ==0f && this.y == 0f)
            throw IOException()
        else if(this.x == 0f)
            return Vec2(1f, 0f)
        else
            return Vec2(0f, 1f)
    }

    companion object {
        val PI = 3.14159265358979323846f
        val degreesToRadians = PI / 180
    }
}