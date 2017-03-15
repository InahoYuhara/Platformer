package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.mygdx.game.Platformer;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by antoi on 2017-02-14.
 */

public class Mario extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD};
    public State currentState;
    public State previousState;
    public Boolean marioIsDead;

    public World world;
    public Body b2Body;

    private TextureRegion marioStand;

    private Animation marioRun;
    private Animation marioJump;
    private float stateTimer;
    private boolean runningRight;


    public Mario(World world, PlayScreen screen){

        super(screen.getAtlas().findRegion("hoodleidle"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        marioIsDead = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++)
        {
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        }
        marioRun = new Animation(0.1f, frames);
        frames.clear();
        for(int i = 4; i < 6; i++)
        {
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        }
        marioJump = new Animation(0.1f, frames);

        marioStand = new TextureRegion(getTexture(), 0, 0, 32, 32);

        defineMario();
        setBounds(0, 0, 16/Platformer.PPM, 16/Platformer.PPM);
        setRegion(marioStand);


    }

    public void update(float delta){
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));

    }

    public TextureRegion getFrame(float delta){
        currentState = getState();

        TextureRegion region;
        switch (currentState)
        {
            case JUMPING:
                region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }
        if((b2Body.getLinearVelocity().x < 0 || !runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }
        else if((b2Body.getLinearVelocity().x > 0 || runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;

    }
    public State getState(){
        if(b2Body.getPosition().y < 0)
            return State.DEAD;
        else if(b2Body.getLinearVelocity().y > 0 || (b2Body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2Body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2Body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else return State.STANDING;
    }

    public Boolean isDead(){
        return marioIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/Platformer.PPM,32/Platformer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/Platformer.PPM);

        fdef.shape = shape;
        b2Body.createFixture(fdef);

    }
}
