package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Platformer;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by antoi on 2017-02-14.
 */

public class Mario extends Sprite {
    public World world;
    public Body b2Body;
    private TextureRegion marioStand;

    public Mario(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0, 0, 16/Platformer.PPM, 16/Platformer.PPM);
        setRegion(marioStand);


    }

    public void update(float delta){
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);

    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/Platformer.PPM,32/Platformer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/Platformer.PPM);

        fdef.shape = shape;
        b2Body.createFixture(fdef);

    }
}
