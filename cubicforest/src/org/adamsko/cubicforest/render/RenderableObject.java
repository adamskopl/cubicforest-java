package org.adamsko.cubicforest.render;

import java.util.List;

import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.text.LabelsMaster;
import org.adamsko.cubicforest.world.WorldObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderableObject extends WorldObject implements LabelsMaster {

	/**
	 * Vector translating rendered image.
	 * Used to render images more intuitively.
	 */
	private Vector2 renderVector;
	/**
	 * 
	 */
	private TextureRegion textureRegion;
	
	/**
	 * Temporary solution: sequence number of the texture in an atlas row
	 */
	private int texNum = 0;
	
	private LabelsContainer labels;
	
	/**
	 * @param tr
	 */
	public RenderableObject(TextureRegion tr, int texNum) {
		this.textureRegion = tr;
		this.texNum = texNum;
		labels = new LabelsContainer();
	}
	
	public void setRenderVector(Vector2 vec) {
		this.renderVector = vec;
	}
	
	public Vector2 getRenderVector() {
		return renderVector;
	}
	
	/**
	 * @param tr
	 */
	public void setTextureRegion(TextureRegion tr) {
		this.textureRegion = tr;
	}
	
	/**
	 * @return
	 */
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
	}
	
	/**
	 * Temporary solution. Get sequence number of a texture in an atlas row.
	 * 
	 * @return sequence number of the texture in an atlas row
	 */
	public int getTexNum() {
		return texNum;
	}

	@Override
	public List<Label> getLabels() {
		return labels.getLabels();
	}

	public void addLabel(ROLabel_e type) throws Exception {
		switch(type) {
		case LABEL_TILEPOS: {
			labels.addLabel(tilesPos);
			break;
		}
		case LABEL_HEIGHT: {
			labels.addLabel(height);
			break;
		}
		case LABEL_NAME: {
			labels.addLabel(name);
			break;
		}
		default: {
			throw new Exception("unsupported Label type");
		}
		}
	}
	
	public void addLabel(Float value) {
		labels.addLabel(value);
	}
	
	public void addLabel(Integer value) {
		labels.addLabel(value);
	}
	
	public void addLabel(String value) {
		labels.addLabel(value);
	}
	
	public void altLabelLast(Color color, float scale, float vecX, float vecY) {
		labels.altLabelLast(color, scale, vecX, vecY);
	}
	
	@Override
	public Boolean hasLabels() {
		return (labels.getLabels().size() != 0);
	}
	
	public void clearLabels() {
		labels.clearLables();
	}
	
}