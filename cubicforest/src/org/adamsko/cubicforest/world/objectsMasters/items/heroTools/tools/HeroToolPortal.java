package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeroToolPortal extends HeroTool {

	private HeroesMaster heroesMaster;
	
	public HeroToolPortal(TextureRegion tr, int texNum, HeroesMaster heroesMaster) {
		super(tr, texNum, HeroToolType.TOOL_PORTAL);
		this.heroesMaster = heroesMaster;
	}

	@Override
	public void onEntityTileEvent(InteractionResult interactionResult,
			EntityObject entityObject, TileEvent eventType) {
		
		if(eventType != TileEvent.OCCUPANT_STOPS &&
				eventType != TileEvent.OCCUPANT_STAYS) {
			return;
		}
		
		switch (entityObject.getEntityType()) {
		case ENTITY_HERO:
			interactionResult.setOrderOperation(OrderOperation.ORDER_FINISH);
			
			// remove hero
			interactionResult.remove((Hero)entityObject);
			
			// remove portal
			interactionResult.remove(this);
			
			// if removed hero is last, the game is won
			if(heroesMaster.getOrderableObjects().size() == 1) {
				interactionResult.setGameResult(GameResult.GAME_WON);
			}
			
			break;

		default:
			break;
		}
		
	}
}
