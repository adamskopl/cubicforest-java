package org.adamsko.cubicforest.render.text;

public class LabelFloat extends Label {

	Float value;

	public LabelFloat(Float value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return Float.toString(value);
	}

}
