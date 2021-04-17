package me.hobrin.autojsonencoder;

public class ElementCode extends Code {
	public final String varName;
	public ElementCode(String varName) {
		this.varName = varName;
	}
	public String getVarName() {
		return this.varName;
	}
}
