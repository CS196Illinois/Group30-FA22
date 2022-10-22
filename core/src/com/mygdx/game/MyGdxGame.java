package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.bodyWorks.bodyPlayer.BodyPlayer;

public class MyGdxGame extends ApplicationAdapter {
	public static World world;
	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;
	BodyDef groundBodyDef;
	Body groundBody;
	PolygonShape groundBox;
	CircleShape circle;
	BodyPlayer player;
	BodyDef boxy;
	Body groundBoxy;
	PolygonShape boxyShape;


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
		player = new BodyPlayer();
		boxy = new BodyDef();
		boxy.position.set(new Vector2(200, 200));
		groundBoxy = world.createBody(boxy);
		boxyShape = new PolygonShape();
		boxyShape.setAsBox(10.0f, 10.0f);
		groundBoxy.createFixture(boxyShape, 0.0f);
		camera.zoom = 0.5f;
		camera.translate(-200, -120);
	}


	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.5f, 0.5f, 1);
		camera.translate(player.x, 0);
		camera.update();
		debugRenderer.render(world, camera.combined);
		player.inputWorks();
		player.bodyWorker();
		world.step(1/10f, 6, 2);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		groundBox.dispose();
		circle.dispose();
		boxyShape.dispose();
	}
}
