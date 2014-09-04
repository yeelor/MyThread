package com.j2cms.singleton.t1;

public class Singleton {
	private static Singleton instance = null;

	private Singleton() {
		// …
	}

	public static Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}
}
