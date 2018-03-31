package me.wei.broaditem.io;

import java.io.IOException;
import java.sql.SQLException;

public interface IOInterface{
	public abstract void loadBroadItem() throws IOException ,SQLException;
	
	public abstract void loadBroadFormat() throws IOException ,SQLException;
	
	public abstract void saveBroadItem() throws IOException ,SQLException;
	
	public abstract void saveBroadFormat() throws IOException ,SQLException;
}
