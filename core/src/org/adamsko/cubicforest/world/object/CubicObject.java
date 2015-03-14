package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.render.world.object.RenderableObjectDefault;
import org.adamsko.cubicforest.render.world.object.RenderableObjectType;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.NullCollisionVisitorsManagerDefault;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicatorDefault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * FIXME: object is too big, method implementations should be passed to special
 * classes.
 * 
 * @author adamsko
 * 
 */
public abstract class CubicObject extends RenderableObjectDefault implements
		WorldObject {

	private final WorldObjectType type;
	private WorldObjectState state;

	private TilePropertiesIndicator tilePropertiesIndicator = null;

	/**
	 * Container holding this WorldObject.
	 */
	private final WorldObjectsMaster parentContainer;

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
	 * Needed for NullWorldObject constructor. Otherwise
	 * {@link CollisionVisitorsManagerDefault} is invoked recursively.
	 */
	protected CubicObject() {
		super(null, 0);
		this.parentContainer = null;
		this.collisionVisitorsManager = null;
		this.type = WorldObjectType.DEFAULT;
		this.renderType = RenderableObjectType.TYPE_WORLD;
		this.state = WorldObjectState.ALIVE;
	}

	public CubicObject(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container, final WorldObjectType type) {
		super(tr, texNum);

		this.parentContainer = container;

		collisionVisitorsManager = NullCollisionVisitorsManagerDefault
				.instance();

		initTilePropertiesIndicator();

		tilesPos = new Vector2(0.0f, 0.0f);
		verticalPos = new Float(0.0f);
		name = new String("WorldObject");
		speed = 0;
		this.type = type;
		this.renderType = RenderableObjectType.TYPE_WORLD;
		this.state = WorldObjectState.ALIVE;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public WorldObjectType getType() {
		return type;
	}

	@Override
	public WorldObjectsMaster getParentContainer() {
		return parentContainer;
	}

	@Override
	public TilePropertiesIndicator getTilePropertiesIndicator() {
		return tilePropertiesIndicator;
	}

	@Override
	public void initTilePropertiesIndicator() {
		tilePropertiesIndicator = new TilePropertiesIndicatorDefault();
	}

	/**
	 * Should be invoked after initializing CollisionVisitorsManager
	 */
	@Override
	public void initCollisionVisitorsManager(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {
		if (collisionVisitorsManagerFactory.isNull()) {
			Gdx.app.error("WorldObject::initCollisionVisitorsManager",
					"collisionVisitorsManagerFactory.isNull()");
		}
		collisionVisitorsManager = collisionVisitorsManagerFactory.create(type);
	}

	@Override
	public void setState(final WorldObjectState newState) {
		this.state = newState;
	}

	@Override
	public WorldObjectState getState() {
		return state;
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitWorldObject(this);
	}

	@Override
	public CollisionVisitorsManager collision() {
		collisionVisitorsManager.setVisitingObject(this);
		return collisionVisitorsManager;
	}

	@Override
	public void setTilesPos(final Vector2 pos) {
		this.tilesPos.set(pos);
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setSpeed(final int speed) {
		this.speed = speed;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void addLabel(final ROLabel type) {
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
			Gdx.app.error("CubicObject::addLabel", "unsupported label");
		}
		}
	}

	// ACCESSOR METHODS ->

	@Override
	public void setTilesPosX(final float x) {
		this.tilesPos.set(x, this.tilesPos.y);
	}

	@Override
	public void setTilesPosY(final float y) {
		this.tilesPos.set(this.tilesPos.x, y);
	}

	@Override
	public Vector2 getTilesPos() {
		return this.tilesPos;
	}

	@Override
	public float getTilesPosX() {
		return this.tilesPos.x;
	}

	@Override
	public float getTilesPosY() {
		return this.tilesPos.y;
	}

	@Override
	public void setVerticalPos(final Float height) {
		this.verticalPos = height;
	}

	@Override
	public float getVerticalPos() {
		return verticalPos;
	}

	@Override
	public void refreshTexture() {

	}

}