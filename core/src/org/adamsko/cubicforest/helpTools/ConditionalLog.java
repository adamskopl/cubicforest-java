package org.adamsko.cubicforest.helpTools;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

/**
 * Conditional logging class: using {@link Gdx} logging. Allows to turn on/off
 * logging in external classes.
 */
public class ConditionalLog {

	/**
	 * List of classes using {@link ConditionalLog} class.
	 */
	private static List<LogObject> logObjects;

	public ConditionalLog() {
		logObjects = new ArrayList<LogObject>();
	}

	public static void addObject(final Object newObject, final String objectName) {
		if (!contains(newObject)) {
			final LogObject newLogObject = new LogObject(newObject, objectName);
			logObjects.add(newLogObject);
		}
	}

	/**
	 * Set conditional logging for given object.
	 * 
	 * @param switchedObject
	 *            Object for witch switch is performed.
	 * @param use
	 *            True, if conditional logging for passed object should be used.
	 */
	public static void setUsage(final Object switchedObject, final boolean use) {
		if (!contains(switchedObject)) {
			Gdx.app.error("ConditionalLog::setUsage",
					"switchedObject not on the list");
			return;
		}
		final LogObject searchedObject = getLogObject(switchedObject);
		searchedObject.setLogUsage(use);
	}

	public static void debug(final Object loggedObject, final String message) {
		logMessage(loggedObject, false, message);
	}

	public static void error(final Object loggedObject, final String message) {
		logMessage(loggedObject, true, message);
	}

	/**
	 * Help method for {@link #debug(Object, String)} and
	 * {@link #error(Object, String)}
	 * 
	 * @param errorMessage
	 *            true if printed message is an error
	 */
	private static void logMessage(final Object loggedObject,
			final boolean errorMessage, final String message) {
		final LogObject logObject = getLogObject(loggedObject);

		if (!logObject.isNull()) {
			if (logObject.isLogged()) {
				if (errorMessage) {
					Gdx.app.error(logObject.getName(), message);
				} else {
					Gdx.app.debug(logObject.getName(), message);
				}
			}
		} else {
			Gdx.app.error("ConditionalLog::logMessage()", "no loggedObject "
					+ loggedObject.toString());
		}
	}

	/**
	 * Get {@link LogObject} for given {@link Object}.
	 */
	private static LogObject getLogObject(final Object searchedObject) {
		for (final LogObject logObject : logObjects) {
			if (logObject.getObject().equals(searchedObject)) {
				return logObject;
			}
		}
		Gdx.app.error("ConditionalLog::getLogObject()",
				"searchedObject not on the list");
		return NullLogObject.instance();
	}

	/**
	 * Check if given {@link Object} is on the loggingObjects list.
	 * 
	 * @param checkedObject
	 *            checked object
	 */
	private static boolean contains(final Object checkedObject) {
		for (final LogObject logObject : logObjects) {
			if (logObject.getObject().equals(checkedObject)) {
				return true;
			}
		}
		return false;
	}
}
