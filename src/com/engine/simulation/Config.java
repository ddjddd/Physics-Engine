package com.engine.simulation;

public final class Config {
	public static int DIMENTION = 2;				//2D
	public static int CONSIDERATION = 3;			//3:���ӵ����� ����
	public static double GRAVITY = 9.81;			//�߷°��ӵ�
	public static double RESTITUTION_COEFF = 0.5;	//�ݹ߰��(e)
	public static double FRICTION_COEFF = 0.9;		//�ٴڸ鸶�����
	public static double delT = 0.1;				//�ð�����(delta t)
	public static double STOP_POSITION = 1d;		//�ּ� ���� ����(position)
	public static double STOP_VELOCITY = 1d;		//�ּ� ���� ����(velocity)
	
	public static double DP_WIDTH  = 800; // �������� ũ��(���� �ʿ�)
	public static double DP_HEIGHT = 550;
}