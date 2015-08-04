package org.adamsko.cubicforest.render.world.object;

import org.adamsko.cubicforest.render.text.LabelsMaster;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChanger;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChanger;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for classes that have to be rendered on the screen.
 *
 * @author adamsko
 *
 */
public interface RenderableObject extends LabelsMaster {

	RenderableObjectType getRenderType();

	Vector2 getRenderVectorCubic();

	void setRenderVector(final Vector2 vec);

	CTextureRegion getTextureRegion();

	/**
	 * Set texture manager and ensure, that rendered textures are of given
	 * worldObjecType.
	 */
	void setTexturesManager(TexturesManager texturesManager,
			WorldObjectType worldObjectType);

	void setTextureRegion(final CTextureRegion textureRegionCubic);

	VisualStateChanger visualState();

	TileDirectionChanger tileDirection();

}
