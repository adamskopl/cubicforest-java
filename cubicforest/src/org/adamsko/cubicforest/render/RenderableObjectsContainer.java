package org.adamsko.cubicforest.render;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsContainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderableObjectsContainer extends WorldObjectsContainer{

	protected List <RenderableObject> renderableObjects;
	protected Texture objectsTexture;
	protected TextureRegion[] atlas;
	
	public RenderableObjectsContainer(String textureName, int tileW, int tileH) {
		renderableObjects = new ArrayList<RenderableObject>();
		objectsTexture = new Texture(Gdx.files.internal("data/" + textureName +".png"));
		atlas = new TextureRegion(objectsTexture).split(tileW, tileH)[0]; 
	}
}