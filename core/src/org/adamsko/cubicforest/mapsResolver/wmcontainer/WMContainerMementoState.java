package org.adamsko.cubicforest.mapsResolver.wmcontainer;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

import com.badlogic.gdx.Gdx;

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

	public boolean isEqual(final WMContainerMementoState checkedState) {

		if (getWOMMementos().size() != checkedState.getWOMMementos().size()) {
			Gdx.app.debug("WMContainerMementoState", "sizes diff");
			return false;
		}

		final List<WOMMemento> checkedMementos = checkedState.getWOMMementos();

		int i = 0;
		for (final WOMMemento memento : getWOMMementos()) {
			if (!memento.isEqual(checkedMementos.get(i))) {
				return false;
			}
			i++;
		}

		return true;
	}
}
