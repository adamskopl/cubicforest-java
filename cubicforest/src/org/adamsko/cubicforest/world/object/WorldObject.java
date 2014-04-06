package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public class WorldObject extends RenderableObject {

	protected WorldObjectType_e worldType;
	
	public WorldObjectType_e getWorldType() {
		return worldType;
	}

	/*
	 * Position indicated by tiles. (0.0,0.0): uppper corner of the first tile.
	 * (2.5, 1.5): center of the field with (2, 1) coordinates
	 */
	protected Vector2 tilesPos;

	/*
	 * Vertical position of the object. Indicates order of the rendering.
	 */
	protected Float verticalPos;

	/**
	 * Long story short: WorldObject's name.
	 */
	protected String name;
	
	/**
	 * How many tiles object can move. 
	 */
	private int speed;
	
	/**
	 * Is this object occupying tile or leaving it free?
	 */
	private Boolean occupiesTile;

	public WorldObject(TextureRegion tr, int texNum, WorldObjectType_e worldType) {
		super(tr, texNum);
		tilesPos = new Vector2(0.0f, 0.0f);
		verticalPos = new Float(0.0f);
		name = new String("WorldObject");
		speed = 0;
		occupiesTile = true;
		this.worldType = worldType;
		this.renderType = RenderableObjectType_e.TYPE_WORLD;
	}

	public void setTilesPos(Vector2 pos) {
		this.tilesPos.set(pos);
	}

	public void setTilesPosX(float x) {
		this.tilesPos.set(x, this.tilesPos.y);
	}

	public void setTilesPosY(float y) {
		this.tilesPos.set(this.tilesPos.x, y);
	}

	public Vector2 getTilesPos() {
		return new Vector2(this.tilesPos);
	}

	public float getTilesPosX() {
		return this.tilesPos.x;
	}

	public float getTilesPosY() {
		return this.tilesPos.y;
	}

	public void setVerticalPos(Float height) {
		this.verticalPos = height;
	}

	public float getVerticalPos() {
		return verticalPos;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setOccupiesTile(Boolean occupiesTile) {
		this.occupiesTile = occupiesTile;
	}
	
	public Boolean isOccupyingTile() {
		return this.occupiesTile;
	}

	public String typeToString() {
		try {
			return WorldObjectHelper.typeToString(worldType);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "TYPE_ERROR";
	}
	
	public void addLabel(ROLabel_e type) throws Exception {
		switch(type) {
		case LABEL_TILEPOS: {
			labels.addLabel(tilesPos);
			break;
		}
		case LABEL_HEIGHT: {
			labels.addLabel(verticalPos);
			break;
		}
		case LABEL_NAME: {
			labels.addLabel(name);
			break;
		}
		default: {
			throw new Exception("unsupported Label type");
		}
		}
	}
	

}