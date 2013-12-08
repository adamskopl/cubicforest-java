package org.adamsko.cubicforest.render;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author adamsko
 *
 */
public class WorldRenderer {

	OrthographicCamera cam;
	RenderCalculator calc;
	List<RenderableObjectsMaster> renderableObjectsMasters;
	SpriteBatch batch = new SpriteBatch(5460);
	
	/**
	 * Width of the isometric tile in pixels.
	 */
	int tileWidth = 50;
	
	ImmediateModeRenderer10 renderer;
	
	Texture cubesAtlas;
	Texture tilesAtlas;
	Texture testAtlas;
	TextureRegion testCube, testCube2;
	TextureRegion testCross;
	TextureRegion tile;
	TextureRegion tileLight;
	TextureRegion testRec;
	
	
	FPSLogger fps = new FPSLogger();
	
	public WorldRenderer () {
		renderer = new ImmediateModeRenderer10();
		calc = new RenderCalculator(tileWidth);
		this.cam = new OrthographicCamera(480, 320);
		this.cam.position.set(0, -100, 0);
		
		renderableObjectsMasters = new ArrayList<RenderableObjectsMaster>();
		
		cubesAtlas = new Texture(Gdx.files.internal("data/cube-atlas.png"));
		tilesAtlas = new Texture(Gdx.files.internal("data/tiles-atlas.png"));
		testAtlas = new Texture(Gdx.files.internal("data/test-atlas.png"));
		TextureRegion[] split = new TextureRegion(cubesAtlas).split(20, 20)[0];
		TextureRegion[] splitTiles = new TextureRegion(tilesAtlas).split(50, 31)[0];
		TextureRegion[] splitTest = new TextureRegion(testAtlas).split(10, 40)[0];
		
		testCube = split[0];
		testCube2 = split[1];
		testCross = split[1];
		tile = splitTiles[0];
		tileLight = splitTiles[1];
		testRec = splitTest[0];
		
		Gdx.app.debug("cf", "w, h " + tile.getRegionWidth() + " " + tile.getRegionHeight());
		
	}
	
	public void render (float deltaTime) {
		
		cam.update();
		
		batch.setProjectionMatrix(cam.combined);
		
		
		
		batch.begin();

		renderROMs();
		
////		batch.draw(testCube, 90, 15);
//		batch.draw(testCube, 80, 10);
//		batch.draw(testCube, 100, 10);
//		batch.draw(testCube, 70, 5);
//		batch.draw(testCube, 90, 5);
//		batch.draw(testCube, 110, 5);
//		batch.draw(testCube, 80, -0);
//		batch.draw(testCube, 100, 0);		
//		batch.draw(testCube, 90, -5);
//		
////		batch.draw(testCube2, 60, 0);
//		batch.draw(testCube2, 50, -5);
//		batch.draw(testCube2, 70, -5);
//		batch.draw(testCube2, 40, -10);
//		batch.draw(testCube2, 60, -10);
//		batch.draw(testCube2, 80, -10);
//		batch.draw(testCube2, 50, -15);
//		batch.draw(testCube2, 70, -15);		
//		batch.draw(testCube2, 60, -20);
		batch.end();
		
		renderGrid();
		fps.log();
	}
	
	void renderGrid() {
		cam.update(false);
		renderer.begin(cam.combined, GL10.GL_POINTS);
				
		int amount = 20;		
		Vector3 vec = new Vector3();
		for(int row = 0; row < amount; row++) {
			for(int col = 0; col < amount; col++) {
				Vector2 screenCoord = calc.getScreenCoord(new Vector2(row, col));
				vec.set(screenCoord, 0);
				renderer.color(1.0f, 0.8f, 0.8f, 1.0f);
				renderer.vertex(vec);
			}
		}
		renderer.end();
	}
	
	public void dispose () {
		batch.dispose();
	}
	
	public void addROM(RenderableObjectsMaster ROM) {
		renderableObjectsMasters.add(ROM);
	}
	
	public void renderObject(RenderableObject rObj) {
		Vector2 objPos = rObj.getWorldPos();
		Vector2 screenPos = calc.getScreenCoord(objPos);
		screenPos.add(rObj.getRenderVector());
		batch.draw(rObj.getTextureRegion(), screenPos.x, screenPos.y);
	}
	
	public void renderROM(RenderableObjectsMaster ROM) {
		List<RenderableObject> rObjects = ROM.getRendarbleObjects();
		for(RenderableObject rObj : rObjects) {
			renderObject(rObj);
		}
	}
	
	public void renderROMs() {
		for(RenderableObjectsMaster ROM : renderableObjectsMasters) {
			renderROM(ROM);
		}
	}
}
