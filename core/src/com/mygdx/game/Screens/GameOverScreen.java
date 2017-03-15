package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Platformer;

import javax.swing.text.View;

/**
 * Created by antoi on 2017-02-15.
 */

public class GameOverScreen implements Screen{

    private Viewport viewport;
    private Stage stage;
    private Game game;

    public GameOverScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(Platformer.V_WIDTH,Platformer.V_HEIGHT, new OrthographicCamera());
        stage =  new Stage(viewport,((Platformer)game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.PURPLE);
        Label.LabelStyle font2 = new Label.LabelStyle(new BitmapFont(), Color.GRAY);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgainLabel = new Label("PLAY AGAIN", font2);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(30);
        stage.addActor(table);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.justTouched()){
            game.setScreen(new PlayScreen((Platformer)game));
            dispose();
        }


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
