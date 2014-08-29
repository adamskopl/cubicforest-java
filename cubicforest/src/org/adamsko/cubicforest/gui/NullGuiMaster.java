package org.adamsko.cubicforest.gui;

public class NullGuiMaster extends GuiMaster {
	private static NullGuiMaster instance = null;

	private NullGuiMaster() {
		super();
	}

	public static NullGuiMaster instance() {
		if (instance == null) {
			instance = new NullGuiMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
