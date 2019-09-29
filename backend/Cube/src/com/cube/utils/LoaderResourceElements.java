package com.cube.utils;

public class LoaderResourceElements {

	private static LoaderResourceElements loaderResourceElements;

	private LoaderResourceElements() {
	}
	
	public static LoaderResourceElements getInstance() {
		if (loaderResourceElements == null) {
			loaderResourceElements = new LoaderResourceElements();
		}
		return loaderResourceElements;
	}
}