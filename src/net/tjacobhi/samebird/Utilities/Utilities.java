package net.tjacobhi.samebird.Utilities;

import java.util.Random;

/**
 * Created by Sean on 12/30/2016.
 *
 * contains utility constants
 */
public class Utilities
{
	public static final int PORT = 25560;
	public static final String HOSTNAME = "samebird.tjacobhi.net";
	public static final Random rand = new Random();
	
	// server codes
	public static final int GAME_START = 0x00000001;
	public static final int GAME_END = 0x00000002;
	
}
