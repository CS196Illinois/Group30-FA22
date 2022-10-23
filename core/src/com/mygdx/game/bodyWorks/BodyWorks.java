package com.mygdx.game.bodyWorks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.MyGdxGame;

public class BodyWorks {
    public long x;
    public long y;
    BodyDef bodyDef;
    public Body body;
    CircleShape circle;
    public FixtureDef fixtureDef;
    Fixture fixture;

    public BodyWorks(BodyDef.BodyType bodyType, Vector2 bodyOrigin, long radius, float density, float friction, float restitution){
        bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(bodyOrigin);
        bodyDef.fixedRotation = true;
        body = MyGdxGame.world.createBody(bodyDef);
        circle = new CircleShape();
        circle.setRadius(radius);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixture = body.createFixture(fixtureDef);
    }

    public void bodyWorker(){
        body.applyForceToCenter(new Vector2(1000*x,980*y), false);
    }


}
