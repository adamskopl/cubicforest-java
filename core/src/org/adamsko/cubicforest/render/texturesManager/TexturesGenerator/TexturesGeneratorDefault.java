package org.adamsko.cubicforest.render.texturesManager.TexturesGenerator;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.CubicTextureController.CubicTextureController;
import org.adamsko.cubicforest.render.texturesManager.CubicTextureController.CubicTextureControllerDefault;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TexturesGeneratorDefault implements TexturesGenerator {

	private final CubicTextureController cubicTextureController;

	private final int textureSize;
	// parameters for calculating positions of the cubes in a textures. value
	// dependent from a single cube texture's size
	private final float calcA, calcB, calcC;
	private final int offsetX, offsetY;

	public TexturesGeneratorDefault() {
		// 'atomic cube' are now available
		this.cubicTextureController = new CubicTextureControllerDefault();

		this.textureSize = 75;
		offsetX = textureSize / 2 - 2;
		offsetY = textureSize * 1 / 2 + 10;
		this.calcA = 3; // 3,
		this.calcB = 1.5f; // 2,
		this.calcC = 4; // 4,
	}

	@Override
	public CTextureRegion generate(final List<CubicJsonCube> modelCubes,
			final RenderableObjectVisualState renderableObjectVisualState) {
		final Pixmap modelPixmap = new Pixmap(textureSize, textureSize,
				cubicTextureController.getPixelFormat());

		float x, y, z;
		float dstX, dstY;

		for (final CubicJsonCube cube : modelCubes) {
			x = cube.getPos().getX();
			y = cube.getPos().getY();
			z = cube.getPos().getZ();

			dstX = offsetX;
			dstY = offsetY;
			dstX += (y + x) * calcA;
			dstY += (x - y) * calcB - z * calcC;

			final String colorName;
			if (renderableObjectVisualState == RenderableObjectVisualState.NORMAL) {
				colorName = cube.getColName();
			} else {
				colorName = visualStateToTextureColor(renderableObjectVisualState);
			}
			final Pixmap singleCubePixmap = prepareSingleCubePixmap(colorName);
			modelPixmap.drawPixmap(singleCubePixmap, (int) dstX, (int) dstY, 0,
					0, cubicTextureController.getCubeTexW(),
					cubicTextureController.getCubeTexH());
			singleCubePixmap.dispose();
		}

		final Texture modelTexture = new Texture(modelPixmap);
		modelPixmap.dispose();
		return new CTextureRegion(modelTexture);
	}

	/**
	 * Get pixel map of a single cube with desired color. Pixel map should be
	 * disposed right after using!
	 */
	private Pixmap prepareSingleCubePixmap(final String color) {
		final TextureRegion[] cubesRow = cubicTextureController
				.getAtlasRowsColor(color).get(0);
		final Texture singleCube = cubesRow[0].getTexture();
		singleCube.getTextureData().prepare();
		final Pixmap singleCubePixmap = singleCube.getTextureData()
				.consumePixmap();
		return singleCubePixmap;
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
