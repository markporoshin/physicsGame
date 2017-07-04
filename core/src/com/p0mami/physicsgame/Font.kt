package com.p0mami.physicsgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

/**
 * Created by Mark on 29.06.2017.
 */
class Font {
    val FONT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>"
    var font: BitmapFont;
    constructor(name: String, size: Int = 15){
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/$name"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.characters = FONT_CHARS
        parameter.size = size;
        parameter.color = Color.BLACK
        font = generator.generateFont(parameter)
        generator.dispose()
    }

    fun draw(batch: Batch, text: String, x: Float, y: Float){
        font.draw(batch, text, x, y);
    }

}