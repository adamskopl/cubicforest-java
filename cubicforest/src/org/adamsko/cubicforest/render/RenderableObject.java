package org.adamsko.cubicforest.render;

import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderableObject extends WorldObject {

	/**
	 * Vector translating rendered image.
	 * Used to render images more intuitively.
	 */
	private Vector2 renderVector;
	/**
	 * 
	 */
	private TextureRegion textureRegion;
	
	/**
	 * @param tr
	 */
	public RenderableObject(TextureRegion tr) {
		this.textureRegion = tr;
	}
	
	public void setRenderVector(Vector2 vec) {
		this.renderVector = vec;
	}
	
	public Vector2 getRenderVector() {
		return renderVector;
	}
	
	/**
	 * @param tr
	 */
	public void setTextureRegion(TextureRegion tr) {
		this.textureRegion = tr;
	}
	
	/**
	 * @return
	 */
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
	}
	
}