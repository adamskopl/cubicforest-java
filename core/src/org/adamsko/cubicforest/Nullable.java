package org.adamsko.cubicforest;

/**
 * For creating null objects. Replaces operations like if(class == null). Class
 * for which nullable has to be made, should implement this. Original class
 * return 'true' for isNull() and nullable class (extending original) 'false'.
 * 
 * @author adamsko
 * 
 */
public interface Nullable {
	boolean isNull();
}
