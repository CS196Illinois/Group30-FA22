package com.mygdx.game.bodyWorks.bodyPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.bodyWorks.BodyWorks;

public class BodyPlayer extends BodyWorks {


    public BodyPlayer() {
        super(BodyDef.BodyType.DynamicBody,new Vector2(100, 200), 1/2, 0.15f,0.1f, 0.6f);
    }

    public void inputWorks(){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                super.x = -1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                super.x = 1;
            } else {
                super.x = 0;
            }
            super.y = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            super.y = 1;
        }


    }

}
