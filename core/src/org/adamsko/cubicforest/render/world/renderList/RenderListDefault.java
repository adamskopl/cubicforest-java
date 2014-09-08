package org.adamsko.cubicforest.render.world.renderList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;

import com.badlogic.gdx.Gdx;

public class RenderListDefault implements RenderList {

	private final List<RenderableObject> renderList;
	private final Comparator<RenderableObject> rOCompoarator;

	public RenderListDefault() {
		renderList = new ArrayList<RenderableObject>();
		rOCompoarator = new RenderableObjectComparator();
	}

	@Override
	public void sort() {
		Collections.sort(renderList, rOCompoarator);
	}

	@Override
	public void add(final List<RenderableObject> list) {
		for (final RenderableObject ro : list) {
			if (renderList.contains(ro)) {
				Gdx.app.error("RenderListDefault::add()",
						"object already on the list");
			}
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

}