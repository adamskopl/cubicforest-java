package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class CubicModelTextureBuilderDefault implements CubicModelTextureBuilder {

	private final CubicTextureController cubicTextureController;

	private final int textureSize;
	// parameters for calculating positions of the cubes in a textures. value
	// dependent from a single cube texture's size
	private final int calcA, calcB, calcC;

	public CubicModelTextureBuilderDefault(
			final CubicTextureController cubicTextureController) {
		this.cubicTextureController = cubicTextureController;
		this.textureSize = 60;
		this.calcA = 3; // 3,
		this.calcB = 2; // 2,
		this.calcC = 4; // 4,
		ConditionalLog.addObject(this, "CubicModelTextureBuilderDefault");
		ConditionalLog.setUsage(this, false);
	}

	@Override
	public Texture createTexture(final List<CubicJsonCube> modelCubes) {

		final Pixmap modelPixmap = new Pixmap(textureSize, textureSize,
				cubicTextureController.getPixelFormat());
		modelPixmap.setColor(Color.RED);
		// modelPixmap.drawRectangle(1, 1, textureSize - 2, textureSize - 2);

		int x, y, z;
		int dstX, dstY;
		final int offsetX = textureSize / 3;
		final int offsetY = textureSize * 2 / 3;
		for (final CubicJsonCube cube : modelCubes) {
			x = cube.getPos().getX();
			y = cube.getPos().getY();
			z = cube.getPos().getZ();

			dstX = offsetX;
			dstY = offsetY;
			dstX += (y + x) * calcA;
			dstY += (x - y) * calcB - z * calcC;

			final Pixmap singleCubePixmap = prepareSingleCubePixmap(cube
					.getColName());
			modelPixmap.drawPixmap(singleCubePixmap, dstX, dstY, 0, 0,
					cubicTextureController.getCubeTexW(),
					cubicTextureController.getCubeTexH());
			singleCubePixmap.dispose();

			ConditionalLog.debug(
					this,
					Integer.toString(x) + " " + Integer.toString(y) + " "
							+ Integer.toString(z) + " -> "
							+ Integer.toString(dstX - offsetX) + " "
							+ Integer.toString(dstY - offsetY));
		}

		final Texture modelTexture = new Texture(modelPixmap);
		return modelTexture;
	}

	@Override
	public int getTextureSize() {
		return textureSize;
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

}
