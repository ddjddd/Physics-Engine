package com.engine.simulation;

import com.engine.Display;
import com.engine.simulation.Config;
import com.engine.simulation.Vec2d;


public class FreeFallEngine extends Thread {
	private long tLapse = 0;
	private long curT = 0;
	private long lastT = 0;
	private double unitT = 0.0;

	// 쓰레드 main
	public void run() {
		curT = System.currentTimeMillis();
		while(Display.running) {
			updateTime();
			accelerate();
			moveThing();
		}
	}
	
	// 흐른 시간 계산
	private void updateTime() {
		lastT = curT;
		curT = System.currentTimeMillis();
		tLapse = (curT - lastT);
		unitT = (tLapse / 300.0);
	}
	
	// 물체 속도 가속하기
	private synchronized void accelerate() {
		for(int i  = 0; i < Display.thing.size(); i++) {
			Thing t = Display.thing.get(i);
			Vec2d oldVel = t.vel();			
			Vec2d newVel = new Vec2d(oldVel.x, 
																  oldVel.y + Config.GRAVITY * unitT);
			t.setVel(newVel);
		}
	}	
	
	// 속도에 맞춰 물체 이동
	private synchronized void moveThing() {
		for(int i  = 0; i < Display.thing.size(); i++) {
			Thing t = Display.thing.get(i);
			Vec2d oldPos = t.pos();			
			Vec2d newPos = new Vec2d(oldPos.x + t.velX() * unitT, 
																  oldPos.y + t.velY() * unitT);
			t.setPos(newPos);
			isWallCollision(t);
			// Need Collision Detection
		}
	}
	
	// 벽과 충돌검사
	private synchronized void isWallCollision(Thing t) {
		double maxX = (double)Config.DP_WIDTH- t.getDim();
		double maxY = (double)Config.DP_HEIGHT- t.getDim()-30; // 30 size of bar
		if(t.posX() > maxX) {
			t.setPosX(maxX);
			t.setVelX(t.velX() * - Config.RESTITUTION_COEFF);
		}
		if(t.posY() > maxY) {
			t.setPosY(maxY);
			t.setVelY(t.velY() * - Config.RESTITUTION_COEFF);
		}
		if(t.posX() < 1) {
			t.setPosX(1);
			t.setVelX(t.velX() * Config.RESTITUTION_COEFF);
			
		}
	}
}
