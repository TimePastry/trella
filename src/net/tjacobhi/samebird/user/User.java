package net.tjacobhi.samebird.user;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by tjacobhi on 30-Dec-16.
 */
public class User implements Serializable
{
	private UUID mUuid;
	private String mUserName;

	public User()
	{
		mUuid = UUID.randomUUID();
	}

	public void setUuid(UUID uuid)
	{
		mUuid = uuid;
	}

	public UUID getmUuid()
	{
		return mUuid;
	}

	public void setUserName(String userName)
	{
		mUserName = userName;
	}

	public String getUserName()
	{
		return mUserName;
	}

	private static final long serialVersionUID = 0x55736572L;
}
