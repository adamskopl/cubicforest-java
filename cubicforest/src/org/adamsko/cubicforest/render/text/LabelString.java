package org.adamsko.cubicforest.render.text;

public class LabelString extends Label {

	String value;

	public LabelString(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return new String(value);
	}

}
