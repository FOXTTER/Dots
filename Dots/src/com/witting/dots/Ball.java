package com.witting.dots;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
	public float x;
	public float y;
	private float xSpeed;
	private float ySpeed;
	public float r;
	private static float width = 900;
	private static float height = 1600;
	private Paint color = new Paint();
	public Ball(float x, float y, float r, int col){
		this.x = x;
		this.y = y;
		this.r = r;
		this.color.setColor(col);
		
		xSpeed = 0;
		ySpeed = 0;
	}
	public Ball(float playerX, float playerY){
		this(0,0, 40f,Color.RED);
		//Din kode er blevet sat ind her.
		this.x = (float)Math.random()*800;
		this.y = (float)Math.random()*1600;
		while(this.x<playerX+100 & this.x>playerX-100) {
			this.x = (float)Math.random()*width;
		}
		while(this.y<playerY+100 & this.y>playerY-100) {
			this.y = (float)Math.random()*height;
		}
		Boolean selector = 0.5 < Math.random();
		xSpeed = (selector ? 0 : 10);
		ySpeed = (!selector ? 0 : 10);
	}
	public void draw(Canvas c){
		if (CollisionBorder()) {
			reverse();
		}
		x+=xSpeed;
		y+=ySpeed;
		c.drawCircle(x, y, r, color);
	}
	private float getDistance(float x1, float y1, float x2, float y2){
		return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	public boolean checkCollision(Ball bold){
		return getDistance(this.x,this.y,bold.x,bold.y)< (this.r+bold.r);
	}
	public boolean CollisionBorder(){
		if (x <= r && xSpeed < 0 || x >= width-r && xSpeed > 0) {
			return true;
		}
		if (y <= r && ySpeed < 0 || y >= height-r && ySpeed > 0) {
			return true;
		}
		return false;
	}
	private void reverse(){
		xSpeed = -xSpeed;
		ySpeed = -ySpeed;
	}
}
