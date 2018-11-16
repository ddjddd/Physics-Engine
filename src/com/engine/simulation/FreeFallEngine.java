package com.engine.simulation;

import com.engine.Display;
import com.engine.simulation.Config;
import com.engine.simulation.Vec2d;


public class FreeFallEngine extends Thread {
	private long tLapse = 0;
	private long curT = 0;
	private long lastT = 0;
	private double unitT = 0.0;

	
	public void run() {
		curT = System.currentTimeMillis();
		
		while(Display.running) {
			updateTime();
			accelerate();
			moveThing();
		}
	}
	
	private void updateTime() {
		lastT = curT;
		curT = System.currentTimeMillis();
		tLapse = (curT - lastT);
		unitT = (tLapse / 1000.0);
	}
	
	private synchronized void accelerate() {
		for(int i  = 0; i < Display.thing.size(); i++) {
			Thing t = Display.thing.get(i);
			Vec2d oldVel = t.vel();			
			Vec2d newVel = new Vec2d(oldVel.x, 
																  oldVel.y + Config.GRAVITY * unitT);
			t.setVel(newVel);
		}
	}	
	
	private synchronized void moveThing() {
		for(int i  = 0; i < Display.thing.size(); i++) {
			Thing t = Display.thing.get(i);
			Vec2d oldPos = t.pos();			
			Vec2d newPos = new Vec2d(oldPos.x + t.velX() * unitT, 
																  oldPos.y + t.velY() * unitT);
			t.setPos(newPos);
			newPos.print();
			// Need Collision Detection
		}
	}
}
