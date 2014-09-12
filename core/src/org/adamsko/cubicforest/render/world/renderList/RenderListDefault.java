package org.adamsko.cubicforest.render.world.renderList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;

public class RenderListDefault implements RenderList {

	private final List<RenderableObject> renderList;
	private final Comparator<RenderableObject> rOCompoarator;
	private final String name;

	public RenderListDefault(final String name) {
		renderList = new ArrayList<RenderableObject>();
		rOCompoarator = new RenderableObjectComparator();
		this.name = name;
	}

	@Override
	public void sort() {
		Collections.sort(renderList, rOCompoarator);
	}

	@Override
	public void add(final List<RenderableObject> list) {
		for (final RenderableObject ro : list) {
			// its possible, that added object is already on the list. it's a
			// valid situation, only if object is about to be removed with
			// 'remove(final List<RenderableObject> list)'!
			renderList.add(ro);
		}
	}

	@Override
	public List<RenderableObject> get() {
		return renderList;
	}

	@Override
	public void update(final List<RenderableObject> list) {
	}

	@Override
	public void remove(final List<RenderableObject> list) {
		for (final RenderableObject ro : list) {
			renderList.remove(ro);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int size() {
		return renderList.size();
	}

}