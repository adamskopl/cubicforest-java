package org.adamsko.cubicforest.render.world.object;

import java.util.List;

import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChanger;
import org.adamsko.cubicforest.render.world.object.tileDirection.TileDirectionChangerDefault;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChanger;
import org.adamsko.cubicforest.render.world.object.visualState.VisualStateChangerDefault;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderableObjectDefault implements RenderableObject {

	protected LabelsContainer labels;
	/**
	 * vector for which object's image is translated by
	 */
	private Vector2 renderVector;

	private TextureRegion textureRegion;

	/**
	 * Experimental texture region representing model made from cubes.
	 */
	private TextureRegion textureRegionCubic;

	private Vector2 renderVectorCubic;

	/**
	 * Temporary solution: sequence number of the texture in an atlas row
	 */
	private int texNum = 0;

	protected RenderableObjectType renderType;
	private final ObjectsTextureChanger objectsTextureChanger;
	private final VisualStateChanger visualStateChanger;
	private final TileDirectionChanger tileDirectionChanger;

	/**
	 * @param textureRegion
	 */
	public RenderableObjectDefault(
			final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion textureRegion, final int texNum) {
		this.textureRegion = textureRegion;
		this.texNum = texNum;
		renderType = RenderableObjectType.TYPE_UNDEFINED;
		labels = new LabelsContainer();
		renderVector = new Vector2();
		renderVectorCubic = new Vector2();
		textureRegionCubic = null;
		this.objectsTextureChanger = objectsTextureChanger;
		this.tileDirectionChanger = new TileDirectionChangerDefault(this,
				this.objectsTextureChanger);
		this.visualStateChanger = new VisualStateChangerDefault(this,
				this.objectsTextureChanger);
	}

	@Override
	public RenderableObjectType getRenderType() {
		return renderType;
	}

	@Override
	public void setRenderVector(final Vector2 vec) {
		this.renderVector = vec;
	}

	@Override
	public Vector2 getRenderVector() {
		return renderVector;
	}

	@Override
	public void setTextureRegion(final TextureRegion tr) {
		this.textureRegion = tr;
	}

	@Override
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
	}

	@Override
	public Vector2 getRenderVectorCubic() {
		return renderVectorCubic;
	}

	@Override
	public void setRenderVectorCubic(final Vector2 vec) {
		this.renderVectorCubic = vec;
	};

	@Override
	public TextureRegion getTextureRegionCubic() {
		return textureRegionCubic;
	}

	@Override
	public void setTextureRegionCubic(final TextureRegion textureRegionCubic) {
		this.textureRegionCubic = textureRegionCubic;
	}

	@Override
	public int getTexNum() {
		return texNum;
	}

	@Override
	public int getTexWidth() {
		return textureRegion.getRegionWidth();
	}

	@Override
	public int getTexHeight() {
		return textureRegion.getRegionHeight();
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