package org.adamsko.cubicforest.gui;

public class NullGuiMasterDefault extends GuiMasterDefault {
	private static NullGuiMasterDefault instance = null;

	private NullGuiMasterDefault() {
		super(false);
	}

	public static NullGuiMasterDefault instance() {
		if (instance == null) {
			instance = new NullGuiMasterDefault();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
