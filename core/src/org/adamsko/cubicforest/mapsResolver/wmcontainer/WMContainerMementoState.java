package org.adamsko.cubicforest.mapsResolver.wmcontainer;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

/**
 * {@link WorldObjectsMastersContainer} memento state
 * 
 * @author adamsko
 * 
 */
public class WMContainerMementoState {

	// WorldObjectMaster objects mementos. The assumption is, that list
	// of WorldObjectsMaster objects from their container is not changing, and
	// therefore WorldObjectsMastersContainer can be recreated with that list
	List<WOMMemento> wOMMementos;

	public WMContainerMementoState(
			final WorldObjectsMastersContainer worldObjectsMastersContainer) {

		wOMMementos = new ArrayList<WOMMemento>();

		for (final WorldObjectsMaster worldObjectsMaster : worldObjectsMastersContainer
				.getWorldObjectsMasters()) {
			wOMMementos.add(worldObjectsMaster.createMemento());
		}

	}

	public List<WOMMemento> getWOMMementos() {
		return wOMMementos;
	}

}
