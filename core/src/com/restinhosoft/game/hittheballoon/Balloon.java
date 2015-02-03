package com.restinhosoft.game.hittheballoon;

import java.security.InvalidParameterException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Balloon extends Actor {
	
	enum BallonColor {
		
		RED ("red", 100),
		BLUE ("blue", 200),
		GREEN ("green", 300),
		ORANGE ("orange", 400), 
		PURPLE ("purple", 500), 
		YELLOW ("yellow", 600), 
		PINK ("pink", 700);
		
		private final String color;
		private final int value;
		
		BallonColor(String color, int value){
			this.color  = color;
			this.value = value;
		}
		
		public  String color() { return color; }
		public int value() { return value; }
		
	}
	
	//	balloon texture
	Texture texture;
	
	//	balloon initial position
	float balloon_x;
	float balloon_y;

	//	state variables
	private boolean touched = false;
	private boolean out = false;
	
	//	balloon's motion vectors
	Vector2 position;
	Vector2 velocity;
	Vector2 acceleration;
	
	BallonColor color;

	public Balloon(BallonColor color) {
		this.color = color;
		
		setTexture();
		
		Random random = new Random();
		int radom_x = random.nextInt(Gdx.graphics.getWidth() - 64);
		
		balloon_x = radom_x;
		balloon_y = Gdx.graphics.getHeight() + 64;
		
		this.setBounds(balloon_x, balloon_y, texture.getWidth(), texture.getHeight());
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				((Balloon) event.getTarget()).setVisible(false);
				((Balloon) event.getTarget()).setTouched(true);;
				return true;
			}
		});
		
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
	
	public void setTexture(){
		if(color.equals(Balloon.BallonColor.BLUE)){
			texture = new Texture(Gdx.files.internal("hittheballoon/blue_balloon.png"));
		} else if(color.equals(Balloon.BallonColor.RED)){
			texture = new Texture(Gdx.files.internal("hittheballoon/red_balloon.png"));
		} else if(color.equals(Balloon.BallonColor.GREEN)){
			texture = new Texture(Gdx.files.internal("hittheballoon/green_balloon.png"));
		} else if(color.equals(Balloon.BallonColor.ORANGE)){
			texture = new Texture(Gdx.files.internal("hittheballoon/orange_balloon.png"));
		}else if(color.equals(Balloon.BallonColor.PURPLE)){
			texture = new Texture(Gdx.files.internal("hittheballoon/purple_balloon.png"));
		}else if(color.equals(Balloon.BallonColor.YELLOW)){
			texture = new Texture(Gdx.files.internal("hittheballoon/yellow_balloon.png"));
		} else if(color.equals(Balloon.BallonColor.PINK)){
			texture = new Texture(Gdx.files.internal("hittheballoon/pink_balloon.png"));
		}else {
			throw new InvalidParameterException("Invalid Balloon Color. ");
		}
	}

	private void update() {
		velocity.add(acceleration);
		position.add(velocity);
		setPosition(position.x, position.y);
		
		if(position.y < 0 && !isTouched()){
			setOut(true);
		}
		
	}
	
	public int getBalloonPoints(){
		return color.value;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public boolean isOut() {
		return out;
	}

	public void setOut(boolean out) {
		this.out = out;
	}

}
