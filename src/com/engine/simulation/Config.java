package com.engine.simulation;

public interface Config {
	public static int DIMENSION = 2;					//2D
	public static int CONSIDERATION = 3;				//3:가속도까지 생각
	public static float GRAVITY = 9.81f;				//중력가속도
	public static float RESTITUTION_COEFF_THING = 0.9f;	//반발계수
	public static float RESTITUTION_COEFF_WALL = 0.6f;	//반발계수
	public static float FRICTION_COEFF = 0.999f;		//바닥면마찰계수
//	public static float delT = 0.1f;					//시간간격(delta t)
//	public static float STOP_POSITION = 0.1f;			//최소 정지 조건(position)
//	public static float STOP_VELOCITY = 0.1f;			//최소 정지 조건(velocity)
	
	public static final int DP_WIDTH  = 800; // 물리엔진 크기(수정 필요)
	public static final int DP_HEIGHT = 550;
	
	public static final int DP_RIGHT = DP_WIDTH-16;	//환경에 따라 다를 수 있음
	public static final int DP_BOTTOM = DP_HEIGHT-40;
	
	public static final String TITLE = "Engine  v.0.2";
}