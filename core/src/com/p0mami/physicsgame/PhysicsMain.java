package com.p0mami.physicsgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;


public class PhysicsMain extends ApplicationAdapter {

    public static int Width;
    public static int Height;

	SpriteBatch batch;
    Font font;
    ArrayList<Unit> dot;
    Unit sdot;
    RuleManager rm;
    @Override
	public void create () {
        Width = Gdx.graphics.getWidth();
        Height = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
        font = new Font("gothic.ttf", 15);
        rm = new RuleManager();
        {
            dot = new ArrayList<Unit>();
            dot.add(new Unit(new Vec2(Width / 2 + 50, Height / 2 + 60), new Vec2(-50, -50), new Vec2(0, 0), 250).setParam("dot1", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 - 50, Height / 2 - 50), new Vec2(50, 50), new Vec2(0, 0), 300).setParam("dot2", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 + 75, Height / 2 - 170), new Vec2(+10, -30), new Vec2(0, 0), 350).setParam("dot5", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 + 75, Height / 2 + 180), new Vec2(+40, 30), new Vec2(0, 0), 350).setParam("big dot1", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 + 75, Height / 2 - 180), new Vec2(-40, -30), new Vec2(0, 0), 350).setParam("dot6", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 + 90, Height / 2 - 180), new Vec2(-10, -30), new Vec2(0, 0), 350).setParam("dot7", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 - 75, Height / 2 + 180), new Vec2(40, 30), new Vec2(0, 0), 350).setParam("dot8", "dot.png"));
           // dot.add(new SPoint(new Vec2(Width / 2, Height / 2), new Vec2(40, -30), new Vec2(0, 0), 5000).setParam("dot9", "sdot.png"));
            dot.add(new Unit(new Vec2(Width / 2 - 95, Height / 2 - 180), new Vec2(100, -30), new Vec2(0, 0), 1000).setParam("dot10", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 -5, Height / 2 - 180), new Vec2(40, -30), new Vec2(0, 0), 1000).setParam("dot11", "dot.png"));
            dot.add(new Unit(new Vec2(Width / 2 - 20, Height / 2 - 180), new Vec2(40, 30), new Vec2(0, 0), 1000).setParam("dot12", "dot.png"));

            //sdot = new SPoint(new Vec2(Width / 2, Height / 2), new Vec2(0, 0), new Vec2(0, 0), 1000);
            //sdot.setParam("sdot1", "sdot.png");
        }
        rm.setRules();
    }

	@Override
	public void render () {
        rm.Calculation();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float dtime = Gdx.graphics.getDeltaTime();

		batch.begin();
        for (Unit u: dot) {
            u.render(dtime, batch);
        }
        //sdot.render(dtime, batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
