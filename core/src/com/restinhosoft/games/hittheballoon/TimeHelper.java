package com.restinhosoft.games.hittheballoon;

/**
 * Simple class that implements time counting.
 * @author Ã?talo
 *
 */
public class TimeHelper {
	
	private long initialTime;

	/**
	 * Starts counting when called.
	 */
	public TimeHelper() 
	{
		initialTime = System.currentTimeMillis();
	}
	
	public double getElapsedTimeInSeconds()
	{
		long currentTime = System.currentTimeMillis();
		long delta = currentTime - initialTime;
		double elapsedSeconds = delta / 1000.0;
		return elapsedSeconds;
	}

	public long getInitialTime() 
	{
		return initialTime;
	}

}
