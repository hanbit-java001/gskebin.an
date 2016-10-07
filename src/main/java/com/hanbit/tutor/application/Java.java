package com.hanbit.tutor.application;

public class Java {

	private static Java singleton_name;

	private Java() {

	}
	
	
	public static Java getInstance() {
		if (singleton_name == null) {
			synchronized (Java.class) {
				if (singleton_name == null) {

					singleton_name = new Java();
				}
			}
		}
		return singleton_name;
	}

	
}
