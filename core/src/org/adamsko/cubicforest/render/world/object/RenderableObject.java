package org.adamsko.cubicforest.render.world.object;

import org.adamsko.cubicforest.render.text.LabelsMaster;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChanger;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChanger;
import org.adamsko.cubicforest.world.object.WorldObjectType;

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

	CTextureRegion getTextureRegionCubic();

	/**
	 * Set texture manager and ensure, that rendered textures are of given
	 * worldObjecType.
	 */
	void setTexturesManager(TexturesManager texturesManager,
			WorldObjectType worldObjectType);

	void setTextureRegionCubic(final CTextureRegion textureRegionCubic);

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

	VisualStateChanger visualState();

	TileDirectionChanger tileDirection();

}
