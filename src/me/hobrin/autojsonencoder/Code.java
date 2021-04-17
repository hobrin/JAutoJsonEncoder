package me.hobrin.autojsonencoder;

public class Code {
	String code = "";
	
	public void addLine() {
		this.code = this.code + "\n";
	}
	public void addLine(String l) {
		this.code = this.code + l + "\n";
	}
	public void addLines(String l) {
		this.code = this.code + l;
	}
	
	public String getCode() {
		return this.code;
	}
}
