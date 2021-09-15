package engine.io;

import org.lwjgl.glfw.GLFW.*;

public class Input
{
	private GLFWKeyCallback keyboard;
	
	
	private GLFWCursorPosCallback mouseMove;
	
	
	private GLFWMouseButtonCallback mouseButton;
	
	public static void update()
	{
		
	}
	
	public static boolean getkey(int keyCode)
	{
		return .isKeyDown(keyCode);
	}
	
	public static boolean getKeyDown(int keyCode)
	{
		return false;
	}
}
