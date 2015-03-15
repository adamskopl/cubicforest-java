package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilder;
import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilderDefault;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectsTextureChangerDefault implements ObjectsTextureChanger {

	private final String modelName;
	private final CubicTextureController cubicTextureController;
	private final CubicModelBuilder cubicModelBuilder;

	private TextureRegion tempCubicTextureRegion;

	private final TexturesContainer texturesContainer;

	public ObjectsTextureChangerDefault(final String modelName,
			final CubicTextureController cubicTextureController) {
		this.modelName = modelName;
		this.cubicTextureController = cubicTextureController;
		this.cubicModelBuilder = new CubicModelBuilderDefault(
				this.cubicTextureController);
		this.texturesContainer = new TexturesContainerDefault();

		// load model's cubes
		this.cubicModelBuilder.loadModel(this.modelName);
		this.tempCubicTextureRegion = new TextureRegion();
		fillTexturesContainer();
	}

	@Override
	public TextureRegion tempGetCubicTextureRegion() {
		return this.tempCubicTextureRegion;
	}

	@Override
	public TextureRegion getTextureRegion(final TileDirection desiredDirection,
			final RenderableObjectVisualState objectVisualState) {
		if (!texturesContainer.containsTextures(objectVisualState)) {
			for (final TileDirection tDirection : TileDirection.values()) {
				final TextureRegion texture = cubicModelBuilder
						.createTextureRegion(tDirection,
								visualStateToTextureColor(objectVisualState));
				this.texturesContainer.addTexture(texture, objectVisualState,
						tDirection);
			}
		}
		return texturesContainer.getTextureRegion(objectVisualState,
				desiredDirection);
	}

	// for every visual state there are texture regions in a number of tile
	// directions
	private void fillTexturesContainer() {
		for (final TileDirection tileDirection : TileDirection.values()) {
			final TextureRegion texture = cubicModelBuilder
					.createTextureRegion(
							tileDirection,
							visualStateToTextureColor(RenderableObjectVisualState.NORMAL));
			this.texturesContainer.addTexture(texture,
					RenderableObjectVisualState.NORMAL, tileDirection);
		}
		// for (final RenderableObjectVisualState visualState :
		// RenderableObjectVisualState
		// .values()) {
		// for (final TileDirection tileDirection : TileDirection.values()) {
		// final TextureRegion texture = cubicModelBuilder
		// .createTextureRegion(tileDirection,
		// visualStateToTextureColor(visualState));
		// this.texturesContainer.addTexture(texture, visualState,
		// tileDirection);
		// }
		// }
		this.tempCubicTextureRegion = this.texturesContainer.getTextureRegion(
				RenderableObjectVisualState.NORMAL,
				TileDirection.randomTileDirection());
	}

	/**
	 * Get color's name for given visual state.
	 */
	private String visualStateToTextureColor(
			final RenderableObjectVisualState objectVisualState) {
		switch (objectVisualState) {
		// normal state: return empty name, so colors will be taken from model's
		// data
		case NORMAL:
			return "";
		case SELECTED:
			return "yellow";
		case SELECTED2:
			return "darkyellow";
		case SELECTED_FAIL:
			return "red";
		case BLOCKED:
			return "darkgray";
		case ALLOWED:
			return "green";
		case ALLOWED2:
			return "darkgreen";
		default:
			return "";
		}
	}
}
