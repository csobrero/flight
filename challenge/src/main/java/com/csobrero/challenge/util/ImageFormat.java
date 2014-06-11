package com.csobrero.challenge.util;

public enum ImageFormat {
	BMP("bmp"), GIF("gif"), JPG("jpg"), PNG("png");

	private String type;
	
	private ImageFormat(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	
}
