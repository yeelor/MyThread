package com.j2cms.singleton.t0;

public class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
		// …
	}

	public static Singleton getInstance() {
		return instance;
	}
}
