package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

public class CubicGsonModel {

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
