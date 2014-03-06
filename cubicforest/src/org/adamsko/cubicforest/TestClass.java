package org.adamsko.cubicforest;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesHelper;

import com.badlogic.gdx.math.Vector2;

public class TestClass {
	
	public TestClass() {
		
		List<Vector2> vecs =  new ArrayList<Vector2>();
		vecs.add(new Vector2(2, 3));
		vecs.add(new Vector2(1, 3));
				
		Tile tileA = new Tile(vecs.get(0), null);
		Tile tileB = new Tile(vecs.get(1), null);
		
		System.out.println("tiles: " + tileA.toString() + tileB.toString());
		
		Vector2 posBetween = null;
		try {
			posBetween = TilesHelper.getPosBetween(tileA, tileB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Boolean adj = TilesHelper.areTilesAdjecant(tileA, tileB);
		
		System.out.println("posBetween: " + posBetween.toString());
		System.out.println("tiles: " + tileA.toString() + tileB.toString());
		
	}
	
}
