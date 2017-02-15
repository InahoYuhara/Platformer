package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Platformer;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Mario;
import com.mygdx.game.Tools.B2WorldCreator;

/**
 * Created by antoine on 2017-02-13.
 */

public class PlayScreen implements Screen {

    private Platformer game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map Variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2D variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private Mario player;



    public PlayScreen(Platformer game){
        this.game = game;

        //Camera
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Platformer.V_WIDTH / Platformer.PPM,Platformer.V_HEIGHT / Platformer.PPM,gameCam);
        hud = new Hud(game.batch);

        mapLoader =  new TmxMapLoader();
        map = mapLoader.load("testMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f /Platformer.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2,gamePort.getWorldHeight() / 2, 0);

        //world creation
        world = new World(new Vector2(0, -10), true);
        //allow debug lines
        b2dr = new Box2DDebugRenderer();
        //player creation
        player = new Mario(world);
        new B2WorldCreator(world,map);



    }


    @Override
    public void show() {

    }

    public void HandleInput(float delta){

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2Body.applyLinearImpulse(new Vector2(0,4f),player.b2Body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2Body.getLinearVelocity().x <= 2){
            player.b2Body.applyLinearImpulse(new Vector2(0.1f,0),player.b2Body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&& player.b2Body.getLinearVelocity().x <= 2){
            player.b2Body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2Body.getWorldCenter(),true);
        }

    }
    public void update(float delta){
        HandleInput(delta);

        world.step(1/60f, 6, 2);

        gameCam.position.x = player.b2Body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);

    }

    @Override
    public void render(float delta) {
        //separate update logic from render
        update(delta);

        //clear the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render game map
        renderer.render();

        //render Box2DDebugLines
        b2dr.render(world,gameCam.combined);

        //Set the batch to draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
