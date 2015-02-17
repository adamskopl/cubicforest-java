package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

public class CubicJsonModelDefault implements CubicJsonModel {

	private String name;
	private List<CubicJsonFrame> frames;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public List<CubicJsonFrame> getFrames() {
		return frames;
	}

	@Override
	public void setFrames(final List<CubicJsonFrame> frames) {
		this.frames = frames;
	}

}
