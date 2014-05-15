package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolRed;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
		case TOOL_TURRET:
			newTool = new HeroToolTurret(textureRegions.get(1)[2], 2);
			break;
		case TOOL_TRAP:
			newTool = new HeroToolTrap(textureRegions.get(1)[3], 3);
			break;
		case TOOL_PORTAL:
			newTool = new HeroToolPortial(textureRegions.get(1)[4], 4);
			break;
		default:
			Gdx.app.error("createHeroTool", "unknown heroToolType");
			break;
		}

		newTool.setRenderVector(new Vector2(-textureRegions.get(0)[0]
				.getRegionWidth()/2 + 2, -30));
		
		newTool.setTilesPos(tilePos);
		newTool.setVerticalPos(0.3f);
		newTool.addLabel(newTool.getHeroToolType().toString());
		newTool.altLabelLast(Color.YELLOW, 0.8f, -40.0f, -10.0f);

		return newTool;
	}
}
