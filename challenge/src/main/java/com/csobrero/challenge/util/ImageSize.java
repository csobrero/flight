package com.csobrero.challenge.util;

public enum ImageSize {
	SMALL(100, 100), MEDIUM(150,150), LARGE(250,250), XLARGE(350,350);

	private int height;
	private int width;
	
	private ImageSize(int height, int width){
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	
}
