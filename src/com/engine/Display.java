package com.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import java.util.ArrayList;

import com.engine.simulation.*;


public class Display extends Canvas implements Runnable{
	// Display
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Engine  v.0.1";
	
	// Threads
	private Thread mainThread;
	private Thread freeFallEngine;
	public static boolean running = false;
	
	// Graphics 
	private static AffineTransform at;
	private static BufferStrategy bs;
	private static BufferedImage buffer;
	private static Graphics g;
	private static Graphics2D g2d;
	private static GraphicsConfiguration gc;
	private static GraphicsDevice gd;
	private static GraphicsEnvironment ge;
		
	// Things
	public static ArrayList<Thing> thing = new ArrayList<Thing>();
	
	// Thread 시작
	private void start() {
		if (running) 
			return;
		running = true;
		mainThread = new Thread(this);
		mainThread.start();
		
		// 엔진 구동 thread
		freeFallEngine = new FreeFallEngine();
		freeFallEngine.start();
		
		System.out.println("Engine Working");
	}
	
	// Thread 종료시
	private void stop() {
		if (!running) 
			return;
		running = false;
		try {
			mainThread.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("Engine Terminated");
	}
	

	// 프로그램 초기화
	private static void initialize() {
		Display display = new Display();
		JFrame frame = new JFrame();
		frame.add(display);
		frame.pack();
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		
		System.out.println("Running...");
		
		// Get graphics configuration...
	    ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    gd = ge.getDefaultScreenDevice();
	    gc = gd.getDefaultConfiguration();
	    // Create off-screen drawing surface
	   buffer = gc.createCompatibleImage(WIDTH, HEIGHT);
	    // Objects needed for rendering...
	    g = null;
	    g2d = null;
		
		System.out.println("Initialized");
		
		// 원 생성 _ 추후 삭제
		thing.add(new Circle("test",
												new Vec2d(400, 0),
												new Vec2d(20, 0),
												new Vec2d(0, 0),
												100, 20));
		thing.add(new Circle("test",
				new Vec2d(300, 0),
				new Vec2d(-20, 0),
				new Vec2d(0, 0),
				100, 20));
		thing.add(new Circle("test",
				new Vec2d(600, 0),
				new Vec2d(0, 0),
				new Vec2d(0, 0),
				100, 20));

		
		display.start();
	}
	
	public void run() {
		createBufferStrategy(2);
		bs = this.getBufferStrategy();
		
		int fps = 0;
		int frames = 0;
		long totalTime = 0;
		long curTime = System.currentTimeMillis();
		long lastTime = curTime;
		
		// 엔진 구동 중 에니메이션 디스플레이
		while(running) {
			try {
				// 시간 계산
				lastTime = curTime;
				curTime = System.currentTimeMillis();
				totalTime += curTime - lastTime;
				
				if(totalTime > 1000) {
					totalTime -= 1000;
					fps = frames;
					frames = 0;
				}

				frames++;

				g2d = buffer.createGraphics();
		        g2d.setColor(Color.WHITE);
		        g2d.fillRect(0, 0, WIDTH, HEIGHT);
		        
		        // Draw things
		        for (int i = 0; i < thing.size(); i++) {
		          at = new AffineTransform();
		          at.translate(thing.get(i).posX(), thing.get(i).posY());
		          g2d.setColor(Color.BLACK);
		          Thing t = thing.get(i);
		          g2d.fill(new Ellipse2D.Double(t.posX(), t.posY(), t.getDim(), t.getDim()));
		        }
		        

				g = bs.getDrawGraphics();
		        g.drawImage(buffer, 0, 0, null);
		        if (!bs.contentsLost()) bs.show();
		        // Let the OS have a little time...
		        Thread.sleep(15);
			} catch (InterruptedException e) {
			} finally {
				// release resources
				if (g != null) g.dispose();
				if (g2d != null) g2d.dispose();
			}
			
		}
	}
	
	public static void main (String[] args) {
		initialize();
	}
}
