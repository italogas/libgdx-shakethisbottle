package com.restinhosoft.game.hitthecolor;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Colours extends Actor{
	enum Colour {
		
		RED   ("red"   , 100),
		BLUE  ("blue"  , 110),
		GREEN ("green" , 120),
		ORANGE("orange", 150), 
		PURPLE("purple", 120), 
		YELLOW("yellow", 110), 
		PINK  ("pink"  , 100);
		
		private final String color;
		private final int value;
		
		Colour(String color, int value){
			this.color  = color;
			this.value = value;
		}
		
		public  String color() { return color; }
		public int value() { return value; }
	}
	
	private Texture texture;
	
	private float position_x;
	private float position_y;

	private boolean touched = false;
	private boolean out = false;
	
	private boolean pause;
	Colour color;

	public Colours(float position_x,float position_y) {
		setColorRan();
		
		setTexture();
				
		this.position_x = position_x;
		this.position_y = position_y;
		
		this.pause = false;
		
		//bounds();
		
		timer();
		
	}
	
	private void bounds(){
		this.setBounds(position_x, position_y, texture.getWidth(), texture.getHeight());
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				((Colours) event.getTarget()).setVisible(false);
				if(touched)((Colours) event.getTarget()).setTouched(false);
				else ((Colours) event.getTarget()).setTouched(true);
				//((Colours) event.getTarget()).setTouched(true);
				//System.out.println("epa colour.java");
				return true;
			}
		});
		
		/*addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				System.out.println("clucou");
			}
		});*/
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position_x, position_y);
	}
	
	@Override
	public void act(float delta) {
		update();
		return;
	}
	
	public void setTexture(){
		if(color.equals(Colours.Colour.BLUE)){
			texture = new Texture(Gdx.files.internal("hitthecolour/blue.png"));
		} else if(color.equals(Colours.Colour.RED)){
			texture = new Texture(Gdx.files.internal("hitthecolour/red.png"));
		} else if(color.equals(Colours.Colour.GREEN)){
			texture = new Texture(Gdx.files.internal("hitthecolour/green.png"));
		} else if(color.equals(Colours.Colour.ORANGE)){
			texture = new Texture(Gdx.files.internal("hitthecolour/orange.png"));
		}else if(color.equals(Colours.Colour.PURPLE)){
			texture = new Texture(Gdx.files.internal("hitthecolour/purple.png"));
		}else if(color.equals(Colours.Colour.YELLOW)){
			texture = new Texture(Gdx.files.internal("hitthecolour/yellow.png"));
		} else if(color.equals(Colours.Colour.PINK)){
			texture = new Texture(Gdx.files.internal("hitthecolour/pink.png"));
		}else {
			throw new InvalidParameterException("Invalid Balloon Color. ");
		}
	}

	private void update() {
		if(timer%2==0 && this.isVisible()){
			//this.setVisible(false);
			setColorRan();
		}
		//else if(timer%2==0 && !this.isVisible()) this.setVisible(true);	
		else {
			//this.setVisible(true);
			setColorRan();
		}
	}
	
	public void setColorRan(){
		Random rn = new Random();
		int cor = rn.nextInt(6);
		
		     if(cor == 0) this.color = Colour.RED;
		else if(cor == 1) this.color = Colour.BLUE;
		else if(cor == 2) this.color = Colour.GREEN;
		else if(cor == 3) this.color = Colour.ORANGE;
		else if(cor == 4) this.color = Colour.PURPLE;
		else if(cor == 5) this.color = Colour.YELLOW;
		else if(cor == 6) this.color = Colour.PINK;
	}
	
	public int getPoints(){
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
	
	private Timer counterTimer;
	private TimerTask task;
	private final long twoSeconds = 2000;
	
	private int timer = 0; 
	
	private void timer(){
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try { 
                	if(timer>100){	timer = 0; 	}
                	timer++;
                } catch (Exception e) {e.printStackTrace();}  
           }  
		};
		if(!pause)	counterTimer.scheduleAtFixedRate(task, twoSeconds, twoSeconds);
	}
	
	public void interTimer(){if(!pause)	counterTimer.scheduleAtFixedRate(task, twoSeconds, twoSeconds);}
	
	public void pause(Boolean pause){this.pause = pause;}
	
	public void setPosition(float y,float x){
		position_x = x;
		position_y = y;
		bounds();
	}
	
	public float getPositionX(){return position_x;}
	public float getPositionY(){return position_y;}
}
