package org.adamsko.cubicforest.render.text;

import java.util.List;

/**
 * Interface for classes using {@link LabelsContainer} class.
 * 
 * @author adamsko
 * 
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
}
