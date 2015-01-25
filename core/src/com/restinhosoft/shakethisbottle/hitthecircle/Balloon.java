package com.restinhosoft.shakethisbottle.hitthecircle;

import java.security.InvalidParameterException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.sun.glass.events.TouchEvent;

public class Balloon extends Actor {
	public enum BallonVelocity {
		SLOW, NORMAL, FAST, VERY_FAST, INSANE

	}
	public enum BalloonColor {
		RED, BLUE, GREEN, YELLOW
	}

	private Vector2 velocity;
	private Vector2 position;
	private BalloonColor color;
	
	public Balloon(float x, float y, float width, float height, BalloonColor color) {
		super.setBounds(x, y, width, height);
		this.position = new Vector2(x, y);
		setVelocity(BallonVelocity.SLOW);
		this.color = color;
		
		super.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				if(event.getClass().equals(TouchEvent.class)){
					if(Balloon.this.color.equals(BalloonColor.RED)){
//						Balloon2.this.act(delta);
					}
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * This simple method updates velocity vector. 
	 * @param vel
	 */
	public void setVelocity(BallonVelocity vel) {
		if(vel.equals(BallonVelocity.SLOW)){
			this.velocity = new Vector2(position.x * 10, position.y * 10);
		} else if (vel.equals(BallonVelocity.NORMAL)){
			this.velocity = new Vector2(position.x * 20, position.y * 20);
		} else if (vel.equals(BallonVelocity.FAST)){
			this.velocity = new Vector2(position.x * 30, position.y * 30);
		} else if (vel.equals(BallonVelocity.VERY_FAST)){
			this.velocity = new Vector2(position.x * 40, position.y * 40);
		} else if (vel.equals(BallonVelocity.INSANE)){
			this.velocity = new Vector2(position.x * 50, position.y * 50);
		} else {
			throw new InvalidParameterException();
		}
		return;
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Texture texture = null;
		if(this.color == BalloonColor.RED){
			texture = new Texture(Gdx.files.internal("red_ballon.png"));
		} else if (this.color == BalloonColor.BLUE){
			texture = new Texture(Gdx.files.internal("blue_ballon.png"));
		} else if (this.color == BalloonColor.GREEN){
			texture = new Texture(Gdx.files.internal("green_ballon.png"));
		}
		batch.begin();
		batch.draw(texture, this.getX(), this.getY());
		batch.end();
	}
	
}
