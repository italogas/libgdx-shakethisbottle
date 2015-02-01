package com.restinhosoft.game.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Balloon extends Actor {
	
	Texture texture = new Texture(Gdx.files.internal("circle1.png"));
	float balloon_x = 0;
	float balloon_y = Gdx.graphics.getHeight() + texture.getHeight();
	public static boolean started = false;
	
	Vector2 position;
	Vector2 velocity;
	Vector2 acceleration;
	
	public Balloon() {
		this.setBounds(balloon_x, balloon_y, texture.getWidth(), texture.getHeight());
		
		//		initial position
		position = new Vector2();
		position.x = balloon_x;
		position.y = balloon_y;
		
		//		gravity
		acceleration = new Vector2();
		acceleration.x = 0;
		acceleration.y = -0.1f;
		
		//		motion rate
		velocity = new Vector2();
		velocity.x = 0;
		velocity.y = 2;
		
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position.x, position.y);
	}
	
	@Override
	public void act(float delta) {
		update();
		return;
	}

	private void update() {
		velocity.add(acceleration);
		position.add(velocity);
		setPosition(position.x, position.y);
		
	}

}
