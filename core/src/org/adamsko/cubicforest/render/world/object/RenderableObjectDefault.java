package org.adamsko.cubicforest.render.world.object;

import java.util.List;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.NullCTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.tileDirection.NullTileDirectionChanger;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChanger;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChangerDefault;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChanger;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChangerGeneric;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChangerWorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class RenderableObjectDefault implements RenderableObject {

	protected LabelsContainer labels;

	/**
	 * Experimental texture region representing model made from cubes.
	 */
	private CTextureRegion textureRegionCubic;

	private Vector2 renderVectorCubic;

	protected RenderableObjectType renderType;
	private VisualStateChanger visualStateChanger;
	private TileDirectionChanger tileDirectionChanger;

	/**
	 * @param textureRegion
	 */
	public RenderableObjectDefault() {
		renderType = RenderableObjectType.TYPE_UNDEFINED;
		labels = new LabelsContainer();
		renderVectorCubic = new Vector2();
		textureRegionCubic = NullCTextureRegion.instance();

		CLog.addObject(this, "RenderableObjectDefault");
		CLog.setUsage(this, true);
	}

	@Override
	public void setTexturesManager(final TexturesManager texturesManager,
			final WorldObjectType worldObjectType) {
		if (texturesManager.isNull()) {
			CLog.error(this, "setTexturesManager null " + toString());
		}

		this.tileDirectionChanger = new TileDirectionChangerDefault(this,
				worldObjectType, texturesManager);
		this.visualStateChanger = new VisualStateChangerWorldObjectType(this,
				texturesManager, worldObjectType);

		this.visualStateChanger.changeState(RenderableObjectVisualState.NORMAL);

	}

	@Override
	public void setTexturesManager(final TexturesManager texturesManager,
			final int width, final int height) {
		if (texturesManager.isNull()) {
			CLog.error(this, "setTexturesManager null " + toString());
		}

		this.tileDirectionChanger = NullTileDirectionChanger.instance();
		this.visualStateChanger = new VisualStateChangerGeneric(this,
				texturesManager, width, height);
		this.visualStateChanger.changeState(RenderableObjectVisualState.NORMAL);
	}

	@Override
	public RenderableObjectType getRenderType() {
		return renderType;
	}

	@Override
	public Vector2 getRenderVectorCubic() {
		return renderVectorCubic;
	}

	@Override
	public void setRenderVector(final Vector2 vec) {
		this.renderVectorCubic = vec;
	}

	@Override
	public CTextureRegion getTextureRegion() {
		return textureRegionCubic;
	}

	@Override
	public void setTextureRegion(final CTextureRegion textureRegionCubic) {
		if (textureRegionCubic.isNull()) {
			CLog.error(this, "setTextureRegionCubic null " + toString());
		}
		this.textureRegionCubic = textureRegionCubic;
	}

	@Override
	public List<Label> getLabels() {
		return labels.getLabels();
	}

	@Override
	public void addLabel(final Float value) {
		labels.addLabel(value);
	}

	@Override
	public void addLabel(final Integer value) {
		labels.addLabel(value);
	}

	@Override
	public void addLabel(final String value) {
		labels.addLabel(value);
	}

	@Override
	public void addLabel(final Vector2 value) {
		labels.addLabel(value);
	}

	@Override
	public void altLabelLast(final Color color, final float scale,
			final float vecX, final float vecY) {
		labels.altLabelLast(color, scale, vecX, vecY);
	}

	@Override
	public Boolean hasLabels() {
		return (labels.getLabels().size() != 0);
	}

	@Override
	public void clearLabels() {
		labels.clearLables();
	}

	@Override
	public TileDirectionChanger tileDirection() {
		return tileDirectionChanger;
	}

	@Override
	public VisualStateChanger visualState() {
		return visualStateChanger;
	};

}