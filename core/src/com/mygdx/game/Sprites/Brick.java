package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by inaho on 2017-02-15.
 */

public class Brick extends InteractiveTileObject {

    public Brick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

    }
}
