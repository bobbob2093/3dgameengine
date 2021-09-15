package common;

import org.lwjgl.glfw.*;

import engine.io.*;

public class maincomponent 
{
	
	
	public static final int WIDTH=800;
	public static final int HEIGHT=600;
	public static final String TITLE = "3D Engine";
	public static final double FRAME_CAP = 5000.0;

	private boolean isRunning;
	private Game game;
	
	public maincomponent()
	{
		isRunning = false;
		game = new Game();
	}
	
	public void start(Window window)
	{
		if(isRunning)
			return;
		run(window);
	}
	
	public void stop()
	{
		if(!isRunning)
			return;
		
		isRunning = false;
	}
	
	private void run(Window window)
	{
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning)
		{
			boolean render = false; 
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime)
			{
				render = true;
						
				unprocessedTime -= frameTime;
				
				if(window.closed())
					stop();
				
				Time.setDelta(frameTime);
				//Input.update();
				
				game.input();
				game.update();
				
				if(frameCounter >= Time.SECOND)
				{
					System.out.println(frames);
					frameCounter = 0;
					frames = 0;
				}
			}
			if(render)
			{
				render(window);
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			if(window.closed())
				stop();
			render(window);
			
			frame(window);
		}
		cleanup(window);
	}
	
	private void render(Window window)
	{
		window.update();
		game.render();
	}
	
	private void frame(Window window)
	{
		window.swapBuffers();
	}
	
	private void cleanup(Window window)
	{
		window.dispose();
	}
	public static void main(String[] args)
	{
		Window window = new Window(WIDTH, HEIGHT, TITLE);
		window.create();
		maincomponent game = new maincomponent();
		game.start(window);
		
		
		
	}
	
	
}
