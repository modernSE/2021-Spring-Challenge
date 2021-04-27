package de.cas.challenges.utilities;

public class Stopwatch {

	private long startTime;

	public Stopwatch() {
		reset();
	}

	public boolean exceededSeconds(int seconds) {
		return elapsedMillis() > seconds * 1000;
	}

	public long elapsedMillis() {
		long now = System.currentTimeMillis();
		return now - startTime;
	}
	
	public float elapsedSeconds() {
		return elapsedMillis() / 1000f;
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}

	public void printElapsed() {
		System.out.println(String.format("elapsed %.3f", elapsedMillis() / 1000f));
	}

}
