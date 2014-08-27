package org.adamsko.cubicforest.render.text;

public class LabelFloat extends Label {

	Float value;

	public LabelFloat(final Float value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

}
