package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

public class CubicJsonModel {

	private String name;
	private List<CubicJsonFrame> frames;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<CubicJsonFrame> getFrames() {
		return frames;
	}

	public void setFrames(final List<CubicJsonFrame> frames) {
		this.frames = frames;
	}

}
