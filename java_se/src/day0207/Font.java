package day0207;

import java.io.Serializable;

public class Font implements Serializable{
	
	
	public String getFont() {
		return font;
	}

	public int getStyle() {
		return style;
	}

	public int getSize() {
		return size;
	}

	private   String font ; //참조형 데이터형은 직렬화가 되지 않음
	private  int style ; //기본형 데이터형은 직렬화가 지원된다.
	private  int size ;
	
	public Font(String font, int style, int size) {
		
		
		this.font = font;
		this.style = style;
		this.size = size;
	}
	

}