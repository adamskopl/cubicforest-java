package org.adamsko.cubicforest.render.world;

import org.adamsko.cubicforest.render.text.LabelsMaster;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public interface RenderableObject extends LabelsMaster {

	RenderableObjectType getRenderType();

	void setRenderType(final RenderableObjectType renderType);

	/**
	 * Returns vector for which object's image is translated by
	 * 
	 * @return
	 */
	Vector2 getRenderVector();

	/**
	 * Sets the vector for which object's image is translated by
	 * 
	 * @param vec
	 */
	void setRenderVector(final Vector2 vec);

	TextureRegion getTextureRegion();

	void setTextureRegion(final TextureRegion tr);

	/**
	 * Get sequence number of a texture in an atlas row.
	 * 
	 * @return sequence number of the texture in an atlas row
	 */
	int getTexNum();

	/**
	 * Get object's texture width
	 * 
	 * @return
	 */
	int getTexWidth();

	/**
	 * Get object's texture height
	 * 
	 * @return
	 */
	int getTexHeight();

}
