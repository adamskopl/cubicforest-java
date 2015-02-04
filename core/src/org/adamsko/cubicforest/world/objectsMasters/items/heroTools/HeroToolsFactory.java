package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolRed;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit.HeroToolExit;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap.HeroToolTrap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HeroToolsFactory {

	private final List<TextureRegion[]> textureRegions;
	private final HeroesMaster heroesMaster;

	public HeroToolsFactory(final List<TextureRegion[]> textureRegions,
			final HeroesMaster heroesMaster) {
		this.textureRegions = textureRegions;
		this.heroesMaster = heroesMaster;
	}

	public HeroTool createHeroTool(final WorldObjectType heroToolType,
			final Vector2 tilePos, final WorldObjectsMaster container) {
		HeroTool newTool = NullHeroTool.instance();

		switch (heroToolType) {
		case TOOLORANGE:
			newTool = new HeroToolOrange(textureRegions.get(1)[0], 0, container);
			break;
		case TOOLRED:
			newTool = new HeroToolRed(textureRegions.get(1)[1], 1, container);
			break;
		case TOOLTURRET:
			newTool = new HeroToolTurret(textureRegions.get(1)[2], 2, container);
			break;
		case TOOLTRAP:
			newTool = new HeroToolTrap(textureRegions.get(1)[3], 3, container);
			break;
		case TOOLEXIT:
			newTool = new HeroToolExit(textureRegions.get(1)[4], 4, container);
			break;
		default:
			Gdx.app.error("createHeroTool", "unknown heroToolType");
			break;
		}

		newTool.setRenderVector(new Vector2(-textureRegions.get(0)[0]
				.getRegionWidth() / 2 + 2, -30));

		newTool.setTilesPos(tilePos);
		newTool.setVerticalPos(0.1f);
		newTool.addLabel(newTool.getType().toString());
		newTool.altLabelLast(Color.YELLOW, 0.8f, -40.0f, -10.0f);

		return newTool;
	}
}
