package org.adamsko.cubicforest.render.world;

import java.util.List;

import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.text.LabelsContainer;

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
	 * Temporary solution: sequence number of the texture in an atlas row
	 */
	private int texNum = 0;

	protected RenderableObjectType renderType;

	/**
	 * @param textureRegion
	 */
	public RenderableObjectDefault(final TextureRegion textureRegion,
			final int texNum) {
		this.textureRegion = textureRegion;
		this.texNum = texNum;
		renderType = RenderableObjectType.TYPE_UNDEFINED;
		labels = new LabelsContainer();
		renderVector = new Vector2();
	}

	@Override
	public RenderableObjectType getRenderType() {
		return renderType;
	}

	@Override
	public void setRenderType(final RenderableObjectType renderType) {
		this.renderType = renderType;
	}

	@Override
	public void setRenderVector(final Vector2 vec) {
		this.renderVector = vec;
	}

	@Override
	public Vector2 getRenderVector() {
		return renderVector;
	}

	/**
	 * @param tr
	 */
	@Override
	public void setTextureRegion(final TextureRegion tr) {
		this.textureRegion = tr;
	}

	/**
	 * @return
	 */
	@Override
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
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

}