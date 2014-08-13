package org.adamsko.cubicforest.render.world;

import java.util.List;

import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.text.LabelsMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderableObject implements LabelsMaster {

	protected LabelsContainer labels;

	protected RenderableObjectType renderType;

	public RenderableObjectType getRenderType() {
		return renderType;
	}

	public void setRenderType(final RenderableObjectType renderType) {
		this.renderType = renderType;
	}

	/**
	 * Vector translating rendered image. Used to render images more
	 * intuitively.
	 */
	private Vector2 renderVector;
	/**
	 * 
	 */
	private TextureRegion textureRegion;

	/**
	 * Temporary solution: sequence number of the texture in an atlas row
	 */
	private int texNum = 0;

	/**
	 * @param tr
	 */
	public RenderableObject(final TextureRegion tr, final int texNum) {
		this.textureRegion = tr;
		this.texNum = texNum;
		renderType = RenderableObjectType.TYPE_UNDEFINED;
		labels = new LabelsContainer();
		renderVector = new Vector2();
	}

	public void setRenderVector(final Vector2 vec) {
		this.renderVector = vec;
	}

	public Vector2 getRenderVector() {
		return renderVector;
	}

	/**
	 * @param tr
	 */
	public void setTextureRegion(final TextureRegion tr) {
		this.textureRegion = tr;
	}

	/**
	 * @return
	 */
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
	}

	/**
	 * Temporary solution. Get sequence number of a texture in an atlas row.
	 * 
	 * @return sequence number of the texture in an atlas row
	 */
	public int getTexNum() {
		return texNum;
	}

	public int getTexWidth() {
		return textureRegion.getRegionWidth();
	}

	public int getTexHeight() {
		return textureRegion.getRegionHeight();
	}

	@Override
	public List<Label> getLabels() {
		return labels.getLabels();
	}

	public void addLabel(final Float value) {
		labels.addLabel(value);
	}

	public void addLabel(final Integer value) {
		labels.addLabel(value);
	}

	public void addLabel(final String value) {
		labels.addLabel(value);
	}

	public void addLabel(final Vector2 value) {
		labels.addLabel(value);
	}

	public void altLabelLast(final Color color, final float scale,
			final float vecX, final float vecY) {
		labels.altLabelLast(color, scale, vecX, vecY);
	}

	@Override
	public Boolean hasLabels() {
		return (labels.getLabels().size() != 0);
	}

	public void clearLabels() {
		labels.clearLables();
	}

}