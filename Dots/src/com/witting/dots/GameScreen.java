package com.witting.dots;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.widget.ImageView;

public class GameScreen extends ImageView {
	private Ball goal = new Ball(0,0,50,Color.BLUE);
	private Ball player = new Ball(0,0,50,Color.GREEN);
	List<Ball> list = new ArrayList();
	public GameScreen(Context context, AttributeSet attrs){
		super(context, attrs);
		
		
		this.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    int eventaction = event.getActionMasked();
				PointerCoords pos = new PointerCoords();
			    switch (eventaction) {
			    case MotionEvent.ACTION_DOWN:
			    	invalidate();
			    	break;
			    case MotionEvent.ACTION_POINTER_DOWN: 
			    	break;
		        case MotionEvent.ACTION_POINTER_UP:		        	
		            break;
		        case MotionEvent.ACTION_UP:   
		            break;
				case MotionEvent.ACTION_MOVE:
					for (int i = 0; i < event.getHistorySize(); i++) {
						event.getHistoricalPointerCoords(0, i, pos);
						player.x= pos.x;
						player.y = pos.y;
					}
					
					break;
			    }
			    return true; 
			}
		});
	}
	public Ball spawnEnemy(){
		//Her spawner en ny fjende ud fra spillerens koordinater
		return new Ball(player.x,player.y);
	}
	Paint p = new Paint();
	protected void onDraw(Canvas c) {
		if (player.checkCollision(goal)) {
			goal.x = (float)Math.random()*900;
			goal.y = (float)Math.random()*1600;
			list.add(spawnEnemy());
		}
		for (Ball enemy : list) {
			enemy.draw(c);
		}
		goal.draw(c);
		player.draw(c);
		for (int i = 0; i < list.size(); i++) {
			if (player.checkCollision(list.get(i))) {
				
				p.setColor(Color.RED);
                p.setTextSize(100);
                p.setTextAlign(Align.CENTER);
                c.drawText("GAME OVER",400, 1000, p);
                c.drawText("TOUCH TO START", 400, 500, p);
                p.setTextAlign(Align.LEFT);
                p.setTextSize(40);
                list = new ArrayList<Ball>();
                return;
			}
		}

		invalidate();
	}
}
