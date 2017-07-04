package com.p0mami.physicsgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite

/**
 * Created by Mark on 01.07.2017.
 */



val mpa = 1//koef proportionality mass and area
open class Unit{

    var pos: Vec2
    var speed: Vec2
    var acceleration: Vec2
    var mass: Float
    var radius: Float = 0f
    var collision = false;
    lateinit var sprite: Sprite
    lateinit var name: String

    constructor(pos: Vec2, speed: Vec2, acceleration: Vec2, mass: Float){
        RuleManager.Units.add(this)

        this.pos = pos
        this.speed = speed
        this.acceleration = acceleration
        this.mass = mass
    }
    open fun setParam(name: String, tex_name: String): Unit{
        this.sprite = Sprite(Texture(Gdx.files.internal("units/$tex_name")))
        /*
         area value ~ mass^(1/2)
        */
        this.sprite.setSize(Math.pow(mass.toDouble(), 1/(2.toDouble())).toFloat() * mpa,
                            Math.pow(mass.toDouble(), 1/(2.toDouble())).toFloat() * mpa)
        this.radius = sprite.width / 2f
        this.name = name
        return this;
    }

    /*
     * function fon recalculation unit param
     */
    open fun responce(dtime: Float){
        speed.add(acceleration.multi2(dtime)) //recalculation speed whit acceleration
        pos.add(speed.multi2(dtime).plus(acceleration.multi2(dtime * dtime / 2f))) //... with speed & acceleration

        sprite.setPosition(pos.x - sprite.width / 2, pos.y - sprite.height / 2)
        acceleration.setNull()
    }

    /*
    * function for draw unit
    */
    open fun render(dtime: Float, batch: Batch){
        collision = false
        responce(dtime)
        sprite.draw(batch)
        check()
    }

    fun check(){
        if(pos.x <= 0 || pos.x >= PhysicsMain.Width)
            speed.x = -speed.x

        if(pos.y <= 0 || pos.y >= PhysicsMain.Height)
            speed.y = -speed.y
    }
}

class SPoint: Unit{
    constructor(pos: Vec2, speed: Vec2, acceleration: Vec2, mass: Float):
      super(pos, speed, acceleration, mass){

    }

    override fun setParam(name: String, tex_name: String): Unit{
        super.setParam(name, tex_name)

        return this
    }

    override fun responce(dtime: Float) {
        speed.setNull()       //static unit have not speed
        acceleration.setNull()//and acceleration
        super.responce(dtime)
    }

    override fun render(dtime: Float, batch: Batch) {
        super.render(dtime, batch)
    }
}
