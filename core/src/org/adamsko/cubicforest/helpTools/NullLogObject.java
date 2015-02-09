package org.adamsko.cubicforest.helpTools;

public class NullLogObject extends LogObject {
	private static NullLogObject instance = null;

	private NullLogObject() {
		super(true);
	}

	public static NullLogObject instance() {
		if (instance == null) {
			instance = new NullLogObject();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
