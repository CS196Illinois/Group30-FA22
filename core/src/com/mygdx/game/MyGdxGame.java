package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
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
	Texture charImage;
	SpriteBatch batch;
	Body hitbox;

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
		// add in sprites
		charImage = new Texture(Gdx.files.internal("a.png"));
		batch = new SpriteBatch();
		//create hitbox for sprite
		hitbox = createBox(100, 100, 20, 20, false);

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
		shape.setAsBox(width / 2, height / 2);

		pBody.createFixture(shape, 1.0f);
		shape.dispose();
		return pBody;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		// render sprites, make sprite follow the hitbox that has the physics added
		batch.draw(charImage, hitbox.getPosition().x, hitbox.getPosition().y);

		batch.end();
		world.step(1/10f, 6, 2);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		groundBox.dispose();
		batch.dispose();
	}
}
