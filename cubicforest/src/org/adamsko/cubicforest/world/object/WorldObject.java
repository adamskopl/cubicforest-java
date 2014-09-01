package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.render.world.RenderableObjectDefault;
import org.adamsko.cubicforest.render.world.RenderableObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.NullCollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicatorDefault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public abstract class WorldObject extends RenderableObjectDefault implements
		Nullable {

	private final WorldObjectType type;
	private WorldObjectState state;

	private TilePropertiesIndicator tilePropertiesIndicator = null;

	/**
	 * Container holding this WorldObject.
	 */
	private final WorldObjectsMasterDefault parentContainer;

	/**
	 * Return container holding this WorldObject. <br>
	 * FIXME: design flaw. Every class having contact with WorldObject can
	 * remove it.
	 */
	public WorldObjectsMasterDefault getParentContainer() {
		return parentContainer;
	}

	public WorldObjectType getType() {
		return type;
	}

	private CollisionVisitorsManager collisionVisitorsManager;

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

	/**
	 * Needed for NullWorldObject constructor. Otherwise
	 * {@link CollisionVisitorsManagerDefault} is invoked recursively.
	 */
	protected WorldObject() {
		super(null, 0);
		this.parentContainer = null;
		this.collisionVisitorsManager = null;
		this.type = WorldObjectType.DEFAULT;
		this.renderType = RenderableObjectType.TYPE_WORLD;
		this.state = WorldObjectState.ALIVE;
	}

	public WorldObject(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container,
			final WorldObjectType type) {
		super(tr, texNum);

		this.parentContainer = container;

		// collisionVisitorsManager = CollisionVisitorsManagerFactory.instance()
		// .create(type);

		collisionVisitorsManager = NullCollisionVisitorsManagerDefault
				.instance();

		initTilePropertiesIndicator();

		// initCollisionVisitorsManager();
		tilesPos = new Vector2(0.0f, 0.0f);
		verticalPos = new Float(0.0f);
		name = new String("WorldObject");
		speed = 0;
		occupiesTile = true;
		this.type = type;
		this.renderType = RenderableObjectType.TYPE_WORLD;
		this.state = WorldObjectState.ALIVE;
	}

	public TilePropertiesIndicator getTilePropertiesIndicator() {
		return tilePropertiesIndicator;
	}

	protected void initTilePropertiesIndicator() {
		tilePropertiesIndicator = new TilePropertiesIndicatorDefault();
	}

	/**
	 * Should be invoked after initializing CollisionVisitorsManager
	 */
	public void initCollisionVisitorsManager(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {
		if (collisionVisitorsManagerFactory.isNull()) {
			Gdx.app.error("WorldObject::initCollisionVisitorsManager",
					"collisionVisitorsManagerFactory.isNull()");
		}
		collisionVisitorsManager = collisionVisitorsManagerFactory.create(type);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	public void setState(final WorldObjectState newState) {
		this.state = newState;
	}

	public WorldObjectState getState() {
		return state;
	}

	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitWorldObject(this);
	}

	public CollisionVisitorsManager collision() {
		collisionVisitorsManager.setVisitingObject(this);
		return collisionVisitorsManager;
	}

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