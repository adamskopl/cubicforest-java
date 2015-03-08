package org.adamsko.cubicforest.render.world;

import org.adamsko.cubicforest.render.text.LabelsMaster;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Interface for classes that have to be rendered on the screen.
 * 
 * @author adamsko
 * 
 */
public interface RenderableObject extends LabelsMaster {

	RenderableObjectType getRenderType();

	void setRenderType(final RenderableObjectType renderType);

	/**
	 * Returns vector for which object's image is translated by
	 */
	Vector2 getRenderVector();

	/**
	 * Sets the vector for which object's image is translated by
	 */
	void setRenderVector(final Vector2 vec);

	TextureRegion getTextureRegion();

	Vector2 getRenderVectorCubic();

	void setRenderVectorCubic(final Vector2 vec);

	TextureRegion getTextureRegionCubic();

	void setTextureRegionCubic(final TextureRegion textureRegionCubic);

	void setTextureRegion(final TextureRegion tr);

	/**
	 * Get sequence number of a texture in an atlas row.
	 * 
	 * @return sequence number of the texture in an atlas row
	 */
	int getTexNum();

	/**
	 * Get object's texture width
	 */
	int getTexWidth();

	/**
	 * Get object's texture height
	 */
	int getTexHeight();

}
