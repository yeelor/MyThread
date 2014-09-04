package com.j2cms.singleton.t4;

public class Singleton {
	private Singleton() {
		// …
	}

	private static class SingletonContainer {
		private static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonContainer.instance;
	}
}
