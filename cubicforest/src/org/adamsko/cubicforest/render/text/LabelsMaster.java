package org.adamsko.cubicforest.render.text;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Interface for classes managing {@link Label} objects. Allows to add labels
 * rendering actual values of objects parameters.
 * 
 * @author adamsko
 */
public interface LabelsMaster {

	/**
	 * Get labels managed by LabelsMaster.
	 * 
	 * @return List of Label objects
	 */
	public List<Label> getLabels();

	/**
	 * Check if {@link LabelsMaster} has any labels to render.
	 * 
	 * @return
	 */
	public Boolean hasLabels();

	/**
	 * Add label tracking {@link Float} value.
	 * 
	 * @param value
	 */
	void addLabel(final Float value);

	/**
	 * Add label tracking {@link Float} value.
	 * 
	 * @param value
	 */
	void addLabel(final Integer value);

	/**
	 * Add label tracking {@link String} value.
	 * 
	 * @param value
	 */
	void addLabel(final String value);

	/**
	 * Add label tracking {@link Vector2} value.
	 * 
	 * @param value
	 */
	void addLabel(final Vector2 value);

	/**
	 * Change parameters of lately added label. Without this, added labels would
	 * cover each other.
	 * 
	 * @param color
	 *            color of the new label
	 * @param scale
	 *            scale factor of the new label
	 * @param vecX
	 *            translate vector X
	 * @param vecY
	 *            translate vector Y
	 */
	void altLabelLast(final Color color, final float scale, final float vecX,
			final float vecY);

	/**
	 * Remove all added labels.
	 */
	void clearLabels();

}
