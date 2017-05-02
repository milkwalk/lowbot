package com.lowbot;

import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class LowbotAPI {

	
	public boolean hasPermission(Permissions perm, IUser user){
		
		for(IRole r : user.getClient().getRoles()){
			if(r.getPermissions().contains(perm))
				return true;
		}
		
		return false;		
	}
}
