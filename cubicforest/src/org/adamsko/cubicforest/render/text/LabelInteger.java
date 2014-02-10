package org.adamsko.cubicforest.render.text;

public class LabelInteger extends Label {

	Integer value;

	public LabelInteger(Integer value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return Integer.toString(value);
	}

}
