package org.adamsko.cubicforest.render.text;

import com.badlogic.gdx.Gdx;

public class LabelFloat extends Label {

	Float value;

	public LabelFloat(Float value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

}
