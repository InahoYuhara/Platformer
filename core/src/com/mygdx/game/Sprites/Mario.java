package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Platformer;

/**
 * Created by antoi on 2017-02-14.
 */

public class Mario extends Sprite {
    public World world;
    public Body b2Body;

    public Mario(World world){
        this.world = world;
        defineMario();

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
