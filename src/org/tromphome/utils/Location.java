package org.tromphome.utils;

import com.leapmotion.leap.Vector;

public class Location {

	private int x;
	private int y;
	private int z;
	
	public Location(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	
	

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public static Location fromVector(Vector v){
		return new Location((int) v.getX(), (int) v.getY(), (int) v.getZ());
	}
	
	
}
