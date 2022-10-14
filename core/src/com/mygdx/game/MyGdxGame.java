package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.*;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	World world;
	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;
	BodyDef groundBodyDef;
	Body groundBody;
	PolygonShape groundBox;
	BodyDef bodyDef;
	Body body;
	CircleShape circle;
	FixtureDef fixtureDef;
	Fixture fixture;

	@Override
	public void create () {
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		groundBody = world.createBody(groundBodyDef);
		groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth, 10.0f);
		groundBody.createFixture(groundBox, 0.0f);
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(300, 400);
		body = world.createBody(bodyDef);
		circle = new CircleShape();
		circle.setRadius(6f);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		fixture = body.createFixture(fixtureDef);
	}

	//sdfsdfsdfsd
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		debugRenderer.render(world, camera.combined);
		world.step(1/10f, 6, 2);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		groundBox.dispose();
		circle.dispose();
	}
}
