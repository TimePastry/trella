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
	public static final int ACCEPT_GAME_START = GAME_START << 16; //0x00010000;
	public static final int GAME_END = 0x00000002;
	public static final int ACCEPT_GAME_END = GAME_END << 16;
	public static final int SEND_WORLD = 0x00000004;
	public static final int ACCEPT_WORLD = SEND_WORLD << 16;
	public static final int SEND_MESSAGE = 0x00000008;
	public static final int ACCEPT_MESSAGE = SEND_MESSAGE << 16;
	public static final int SEND_USER_INFO = 0x00000010;
	public static final int ACCEPT_USER_INFO = SEND_USER_INFO << 16;
}
