package org.adamsko.cubicforest.render.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObject;

/**
 * Manager of the {@link RenderableObject} objects. Responsible 
 * for an order of the rendering.
 * @author adamsko
 */
public class RenderListMaster {

	/**
	 * List of {@link RenderableObject} objects.. 
	 * Should be always ready for render (sorted).
	 */
	private List<RenderableObject> renderList;
	
	private RenderableObjectComparator rOCompoarator;
	
	public RenderListMaster() {
		renderList = new ArrayList<RenderableObject>();
		rOCompoarator = new RenderableObjectComparator();
		
	}
	
	/**
	 * Sort renderList.
	 */
	private void sortRenderList() {
		Collections.sort(renderList, rOCompoarator);
	}
	
	/**
	 * @return sorted {@link RenderableObject} list 
	 */
	public List<RenderableObject> gerRenderList() {
		return renderList;
	}
	
	/**
	 * Add new {@link RenderableObject} objects to the list.
	 * @param list
	 */
	public void addRenderableObjects(List<RenderableObject> list) {
		/*
		 * TODO check if objects are already on the renderList.
		 * error.
		 */
		for(RenderableObject ro : list) {
			renderList.add(ro);
		}
		sortRenderList();
	}
	
	/**
	 * Receiving the list of objects, which are already on renderList,
	 * but have changes their parameters (height or tilePos). Update
	 * those objects positions on the renderList. 
	 * @param list List with {@link RenderableObject} objects to be updated.
	 */
	public void updateRenderableObjects(List<RenderableObject> list) {
		
	}
	
	/**
	 * 
	 * @param list list with {@link RenderableObject} objects to be removed
	 * from the renderList.
	 */
	public void removeRenderableObjects(List<RenderableObject> list) {
		
	}
	
}