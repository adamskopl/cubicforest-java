package org.adamsko.cubicforest.render.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

public class RenderableObject  {

	protected RenderableObjectType_e renderType;
	
	public RenderableObjectType_e getRenderType() {
		return renderType;
	}

	public void setRenderType(RenderableObjectType_e renderType) {
		this.renderType = renderType;
	}

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
	 * Temporary solution: sequence number of the texture in an atlas row
	 */
	private int texNum = 0;
	
	/**
	 * @param tr
	 */
	public RenderableObject(TextureRegion tr, int texNum) {
		this.textureRegion = tr;
		this.texNum = texNum;
		renderType = RenderableObjectType_e.TYPE_UNDEFINED;		
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
	
	/**
	 * Temporary solution. Get sequence number of a texture in an atlas row.
	 * 
	 * @return sequence number of the texture in an atlas row
	 */
	public int getTexNum() {
		return texNum;
	}
	
}