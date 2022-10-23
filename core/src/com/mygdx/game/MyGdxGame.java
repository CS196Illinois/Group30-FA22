package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
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
	BodyDef bodyDef;
	Body body;
	Texture charImage;
	SpriteBatch batch;
	Body hitbox;
	BodyEditorLoader bodyEditorLoader;


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
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(300, 400);
		body = world.createBody(bodyDef);
		// add in sprites
		charImage = new Texture(Gdx.files.internal("a.png"));
		batch = new SpriteBatch();
		//create hitbox for sprite
		hitbox = createBox(40, 40, 20, 20, false);
		bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal("aHitbox"));
		bodyEditorLoader.attachFixture(player.body, "aHitboxHitbox", player.fixtureDef, 30);
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic) {
		Body pBody;
		BodyDef def = new BodyDef();

		if(isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;

		def.position.set(x, y);
		def.fixedRotation = true;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2.0f, height / 2.0f);

		pBody.createFixture(shape, 0.1f);
		shape.dispose();
		return pBody;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.5f, 0.5f, 1);
		camera.translate(player.x, 0);
		camera.update();
		player.inputWorks();
		player.bodyWorker();
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		// render sprites, make sprite follow the hitbox that has the physics added
		batch.draw(charImage, player.body.getPosition().x, player.body.getPosition().y);

		batch.end();
		debugRenderer.render(world, camera.combined);
		world.step(1/10f, 6, 2);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		groundBox.dispose();
		circle.dispose();
		boxyShape.dispose();
		batch.dispose();
	}
}
