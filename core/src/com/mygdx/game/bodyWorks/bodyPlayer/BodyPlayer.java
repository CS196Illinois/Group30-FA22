package com.mygdx.game.bodyWorks.bodyPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.bodyWorks.BodyWorks;

public class BodyPlayer extends BodyWorks {
    private boolean upMovementLock;
    private boolean upMovementUnlock;
    int waitInt;

    public BodyPlayer() {
        super(BodyDef.BodyType.DynamicBody,new Vector2(100, 200), 1/2, 0.05f,0.1f, 0.6f);
        upMovementLock = true;
        waitInt = 100;
    }

    public void inputWorks(){
        System.out.println(waitInt);
        if (waitInt != 0 && waitInt != 100){
            waitInt--;}
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            super.x = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            super.x = 1;
        } else {
            super.x = 0;
        }
        super.y = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println(upMovementLock);
            if(super.body.getLinearVelocity().y > 15 && !upMovementUnlock){
                waitInt--;
                upMovementLock = true;
            }
            if (super.body.getLinearVelocity().y < 0) {
                upMovementUnlock = true;

            }
            if(upMovementUnlock && Math.abs(super.body.getLinearVelocity().y) < 1 && waitInt == 100) {
                waitInt--;
                upMovementLock = false;
                upMovementUnlock = false;
            }
            if(!upMovementLock) {
                super.y = 1;
            }
            if (waitInt == 0) {
                waitInt = 100;
            }
        }
    }
}
