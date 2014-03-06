package org.adamsko.cubicforest.render.text;

import com.badlogic.gdx.math.Vector2;

public class LabelVector2 extends Label {

	private Vector2 value;

	public LabelVector2(Vector2 value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

}
