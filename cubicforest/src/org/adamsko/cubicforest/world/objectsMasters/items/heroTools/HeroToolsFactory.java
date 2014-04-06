package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HeroToolsFactory {

	private List<TextureRegion[]> textureRegions;

	public HeroToolsFactory(List<TextureRegion[]> textureRegions) {
		this.textureRegions = textureRegions;
	}

	public HeroTool createHeroTool(HeroToolType_e heroToolType, Vector2 tilePos) {
		HeroTool newTool = null;

		switch (heroToolType) {
		case TOOL_ORANGE:
			newTool = new HeroToolOrange(textureRegions.get(1)[0], 0);
			break;
		case TOOL_RED:
			newTool = new HeroToolRed(textureRegions.get(1)[1], 1);
			break;
		case TOOL_BLUE:
			newTool = new HeroToolBlue(textureRegions.get(1)[2], 2);
			break;
		case TOOL_BLACK:
			newTool = new HeroToolBlack(textureRegions.get(1)[3], 3);
			break;
		case TOOL_WHITE:
			newTool = new HeroToolWhite(textureRegions.get(1)[4], 4);
			break;
		default:
			Gdx.app.error("createHeroTool", "unknown heroToolType");
			break;
		}

		newTool.setRenderVector(new Vector2(-textureRegions.get(0)[0]
				.getRegionWidth() / 2 + 2, -30));
		
		newTool.setTilesPos(tilePos);
		newTool.setVerticalPos(0.4f);

		return newTool;
	}

}
