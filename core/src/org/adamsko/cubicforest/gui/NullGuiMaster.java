package org.adamsko.cubicforest.gui;

public class NullGuiMaster extends GuiMasterDefault {
	private static NullGuiMaster instance = null;

	private NullGuiMaster() {
		super(false);
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
