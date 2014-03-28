package org.adamsko.cubicforest.world.object;

public class WorldObjectHelper {

	public static String typeToString(WorldObjectType_e type) throws Exception {
		switch (type) {
		case OBJECT_GENERIC:
			return "OBJECT_GENERIC";
		case OBJECT_HERO:
			return "OBJECT_PIG";
		case OBJECT_ENEMY:
			return "OBJECT_WOLF";
		case OBJECT_GATHER_CUBE:
			return "OBJECT_GATHER_CUBE";
		case OBJECT_TERRAIN:
			return "OBJECT_TERRAIN";
		default:
			throw new Exception("typeToString: objectUnknown");
		}
	}
	
}
