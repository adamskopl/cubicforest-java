package org.adamsko.cubicforest.helpTools;

import org.adamsko.cubicforest.Nullable;

class LogObject implements Nullable {

	private final String name;
	private final Object logObject;
	/**
	 * Is logObject used? When true, conditional debug for this object is turned
	 * on.
	 */
	private boolean used;

	LogObject(final boolean nullConstructor) {
		this.name = "";
		this.logObject = null;
	}

	LogObject(final Object logOject, final String name) {
		this.logObject = logOject;
		this.name = name;
		used = false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	String getName() {
		return name;
	}

	/**
	 * Get {@link Object} for this log object.
	 */
	Object getObject() {
		return logObject;
	}

	/**
	 * Turn on/off logging for this object
	 * 
	 * @param used
	 *            indicates if conditional logging for this object should be
	 *            used.
	 */
	void setLogUsage(final boolean used) {
		this.used = used;
	}

	/**
	 * Check if conditional logging for this object is used.
	 */
	boolean isLogged() {
		return used;
	}

}
