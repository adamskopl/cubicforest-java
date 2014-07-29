package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public class WorldObject extends RenderableObject {

	protected WorldObjectType type;

	public WorldObjectType getType() {
		return type;
	}

	/**
	 * Position indicated by tiles. (0.0,0.0): uppper corner of the first tile.
	 * (2.5, 1.5): center of the field with (2, 1) coordinates
	 */
	protected Vector2 tilesPos;

	/**
	 * Vertical position of the object. Indicates order of the rendering.
	 */
	protected Float verticalPos;

	/**
	 * Long story short: WorldObject's name.
	 */
	protected String name;

	/**
	 * How many tiles object can move in it's turn.
	 */
	private int speed;

	/**
	 * Indicates how many movement points left. Every tile decreases this
	 * variable by one. Restored before every new movement.
	 */
	private int movePointsLeft;

	public int getMovePointsLeft() {
		return movePointsLeft;
	}

	public void restoreMovementPoints() {
		movePointsLeft = speed;
	}

	public void addMovePoints(final int movePoints) {
		movePointsLeft += movePoints;
		if (movePointsLeft < 0) {
			Gdx.app.error(getName(), "movePointsLeft < 0");
		}
	}

	/**
	 * Is this object occupying tile or leaving it free?
	 */
	private Boolean occupiesTile;

	public WorldObject(final TextureRegion tr, final int texNum,
			final WorldObjectType worldType) {
		super(tr, texNum);
		tilesPos = new Vector2(0.0f, 0.0f);
		verticalPos = new Float(0.0f);
		name = new String("WorldObject");
		speed = 0;
		occupiesTile = true;
		this.type = worldType;
		this.renderType = RenderableObjectType.TYPE_WORLD;
	}

	public void setTilesPos(final Vector2 pos) {
		this.tilesPos.set(pos);
	}

	public void setTilesPosX(final float x) {
		this.tilesPos.set(x, this.tilesPos.y);
	}

	public void setTilesPosY(final float y) {
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

	public void setVerticalPos(final Float height) {
		this.verticalPos = height;
	}

	public float getVerticalPos() {
		return verticalPos;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSpeed(final int speed) {
		this.speed = speed;
		this.movePointsLeft = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setOccupiesTile(final Boolean occupiesTile) {
		this.occupiesTile = occupiesTile;
	}

	public Boolean isOccupyingTile() {
		return this.occupiesTile;
	}

	public void addLabel(final ROLabel type) throws Exception {
		switch (type) {
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