package com.lowbot;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class Command {

	String name;
	String prefix;
	String desc;
	Permissions perm;
	
	public Command(String cmdName, String prefix, Permissions perm){
		this.name = cmdName;
		this.prefix = prefix;
		this.perm = perm;
	}
	
	public String getName(){
		return name;
	}
	public String getPrefix(){
		return prefix;
	}
	
	public Permissions getPermission(){
		return perm;
	}
	
	public void execute(){
		Main.getDebug().info("Command " + getName() +"  has been executed.");
	}
	
	public void setDescription(String str){
		this.desc = str;
	}
}
