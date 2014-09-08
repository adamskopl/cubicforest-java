package org.adamsko.cubicforest.render.text;

import com.badlogic.gdx.graphics.Color;

/**
 * Base class for labels containing references to objects of specific types.
 * 
 * @author adamsko
 * 
 */
public abstract class Label {

	private Color color;
	private float scale;
	private float vecX;
	private float vecY;

	Label() {
		color = new Color(Color.ORANGE);
		scale = 1.0f;
		vecX = 0.0f;
		vecY = 0.0f;
	}

	public void alt(Color color, float scale, float vecX, float vecY) {
		this.color = color;
		this.scale = scale;
		this.vecX = vecX;
		this.vecY = vecY;
	}

	abstract public String getValue();

	public Color getColor() {
		return color;
	}

	public float getScale() {
		return scale;
	}

	public float getVecX() {
		return vecX;
	}

	public float getVecY() {
		return vecY;
	}

}
