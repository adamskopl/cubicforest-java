package org.adamsko.cubicforest.render.texturesManager.cubicTextureController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 */
public class CubicTextureControllerDefault implements CubicTextureController {

	private int cubeTexW;
	private int cubeTexH;

	/**
	 * Available colors: hex values.
	 */
	private List<String> availableColors;
	/**
	 * Hash map: KEY: hex color, VALUE: list of texture regions for a color
	 */
	private HashMap<String, List<TextureRegion[]>> atlasColorsMap;

	/**
	 * For {@link NullCubicTextureController}
	 */
	public CubicTextureControllerDefault(final boolean nullContructor) {
	}

	public CubicTextureControllerDefault() {
		this.cubeTexW = 6;
		this.cubeTexH = 7;
		atlasColorsMap = new HashMap<String, List<TextureRegion[]>>();
		availableColors = new ArrayList<String>();

		prepareAvailableColors();
		createColorsMap();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public List<TextureRegion[]> getAtlasRowsColor(final String color) {
		if (!atlasColorsMap.containsKey(color)) {
			Gdx.app.error("CubicTextureControllerDefault::getAtlasRowsColor()",
					"no " + color.toString() + " in a map");
			return atlasColorsMap.get(atlasColorsMap.get(0));
		}
		return atlasColorsMap.get(color);
	}

	@Override
	public Format getPixelFormat() {
		return atlasColorsMap.get(availableColors.get(0)).get(0)[0]
				.getTexture().getTextureData().getFormat();
	}

	@Override
	public int getCubeTexW() {
		return cubeTexW;
	}

	@Override
	public int getCubeTexH() {
		return cubeTexH;
	}

	private void prepareAvailableColors() {
		availableColors.add("gray");
		availableColors.add("darkgray");
		availableColors.add("green");
		availableColors.add("darkgreen");
		availableColors.add("brown");
		availableColors.add("pink");
		availableColors.add("darkpink");
		availableColors.add("black");
		availableColors.add("red");
		availableColors.add("yellow");
		availableColors.add("darkyellow");
	}

	private void createColorsMap() {
		String fileName = new String();
		for (final String color : availableColors) {
			fileName = "data/cubes-small2/cubes-atlas-" + color + ".png";
			final FileHandle textureFileHandle = Gdx.files.internal(fileName);
			if (!textureFileHandle.exists()) {
				Gdx.app.error(
						"CubicTextureControllerDefault::createColorsMap()",
						fileName + " not found");
				return;
			}
			final Texture cubesTexture = new Texture(textureFileHandle);
			final List<TextureRegion[]> atlasRows = new ArrayList<TextureRegion[]>();
			atlasRows.add(new TextureRegion(cubesTexture).split(this.cubeTexW,
					this.cubeTexH)[0]);
			this.atlasColorsMap.put(color, atlasRows);
		}
	}

}
