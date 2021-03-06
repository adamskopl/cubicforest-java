package org.adamsko.cubicforest.render.texturesManager.texturesGenerator;

import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.cubicTextureController.CubicTextureController;
import org.adamsko.cubicforest.render.texturesManager.cubicTextureController.CubicTextureControllerDefault;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TexturesGeneratorDefault implements TexturesGenerator {

	private final CubicTextureController cubicTextureController;

	private int textureSize;
	// parameters for calculating positions of the cubes in a textures. value
	// dependent from a single cube texture's size
	private final float calcA, calcB, calcC;
	private final int offsetX, offsetY;

	public TexturesGeneratorDefault() {
		// 'atomic cube' are now available
		this.cubicTextureController = new CubicTextureControllerDefault();
		this.textureSize = 90;
		this.offsetX = textureSize / 2 - 2;
		this.offsetY = textureSize * 1 / 2 + 10;
		this.calcA = 3; // 3,
		this.calcB = 1.5f; // 2,
		this.calcC = 4; // 4,
	}

	@Override
	public CTextureRegion generate(final List<CubicJsonCube> modelCubes,
			final RenderableObjectVisualState renderableObjectVisualState,
			final boolean isometric) {

		if (isometric) {
			textureSize = 90;
		} else {
			textureSize = (int) Math.sqrt(modelCubes.size()) * 4 + 8;
		}

		final Pixmap modelPixmap = new Pixmap(textureSize, textureSize,
				cubicTextureController.getPixelFormat());

		float x, y, z;
		float dstX, dstY;
		int rowIndex;

		for (final CubicJsonCube cube : modelCubes) {
			x = cube.getPos().getX();
			y = cube.getPos().getY();
			z = cube.getPos().getZ();

			if (isometric) {
				rowIndex = 0;
				dstX = offsetX;
				dstY = offsetY;
				dstX += (y + x) * calcA;
				dstY += (x - y) * calcB - z * calcC;
			} else {
				rowIndex = 5;
				dstX = textureSize / 2 - cubicTextureController.getCubeTexW()
						/ 2;
				dstY = 2;
				dstX += x * 4;
				dstY += z * 4;
			}

			final String colorName;
			if (renderableObjectVisualState == RenderableObjectVisualState.NORMAL) {
				colorName = cube.getColName();
			} else {
				colorName = visualStateToTextureColor(renderableObjectVisualState);
			}
			final Pixmap singleCubePixmap = prepareSingleCubePixmap(colorName,
					rowIndex);
			modelPixmap.drawPixmap(singleCubePixmap, (int) dstX, (int) dstY, 0,
					0, cubicTextureController.getCubeTexW(),
					cubicTextureController.getCubeTexH());

			modelPixmap.setColor(Color.RED);
			// uncomment to draw texture's border
			// modelPixmap.drawRectangle(0, 0, textureSize, textureSize);

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
	private Pixmap prepareSingleCubePixmap(final String color,
			final int rowIndex) {

		final TextureRegion cubeTexR = cubicTextureController.getTextureRegion(
				color, rowIndex);
		final Texture cubeTex = cubeTexR.getTexture();

		if (!cubeTex.getTextureData().isPrepared()) {
			cubeTex.getTextureData().prepare();
		}

		final Pixmap texPixmap = cubeTex.getTextureData().consumePixmap();

		final Pixmap newPixmap = new Pixmap(cubeTexR.getRegionWidth(),
				cubeTexR.getRegionHeight(),
				cubicTextureController.getPixelFormat());

		for (int x = 0; x < cubeTexR.getRegionWidth(); x++) {
			for (int y = 0; y < cubeTexR.getRegionHeight(); y++) {
				final int colorInt = texPixmap.getPixel(cubeTexR.getRegionX()
						+ x, cubeTexR.getRegionY() + y);
				newPixmap.drawPixel(x, y, colorInt);
			}
		}
		texPixmap.dispose();
		cubeTex.getTextureData().disposePixmap();

		return newPixmap;
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
