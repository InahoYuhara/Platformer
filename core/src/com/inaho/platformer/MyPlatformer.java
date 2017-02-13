package com.inaho.platformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.inaho.platformer.Screens.PlayScreen;

public class MyPlatformer extends Game {
	public SpriteBatch batch;

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(new PlayScreen(this));
	}

	@Override
	public void create () {

	}

	@Override
	public void render () {

		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
