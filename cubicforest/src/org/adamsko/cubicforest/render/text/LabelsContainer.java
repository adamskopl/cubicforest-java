package org.adamsko.cubicforest.render.text;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Container and manager of {@link Label} objects. Receives references to
 * objects, that has to be rendered wit {@link Label}.
 * 
 * @author adamsko
 * 
 */
public class LabelsContainer {

	private List<Label> labels;

	public LabelsContainer() {
		labels = new ArrayList<Label>();
	}

	public List<Label> getLabels() {
		return labels;
	}

	/**
	 * Change parameters of the last label.
	 * 
	 * @param color
	 *            new color
	 * @param scale
	 *            scale factor of the font
	 * @param vecX
	 *            transformation value X
	 * @param vecY
	 *            transformation value Y
	 */
	public void altLabelLast(Color color, float scale, float vecX, float vecY) {
		try {
			Label lastLabel = labels.get(labels.size() - 1);
			lastLabel.alt(color, scale, vecX, vecY);
		} catch (IndexOutOfBoundsException ex) {
			Gdx.app.error("exception", ex.toString());
		}
	}

	/**
	 * Add Float reference to be rendered.
	 * 
	 * @param valueToRender
	 */
	public void addLabel(Float valueToRender) {
		Label newLabel = new LabelFloat(valueToRender);
		labels.add(newLabel);
	}

	/**
	 * Add Float reference to be rendered.
	 * 
	 * @param valueToRender
	 */
	public void addLabel(Vector2 valueToRender) {
		Label newLabel = new LabelVector2(valueToRender);
		labels.add(newLabel);
	}

	/**
	 * Add Float reference to be rendered.
	 * 
	 * @param valueToRender
	 */
	public void addLabel(String valueToRender) {
		Label newLabel = new LabelString(valueToRender);
		labels.add(newLabel);
	}

}
