package test;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String json = "{" + "'title': 'Computing and Information systems',"
				+ "'id' : 1," + "'children' : 'true'," + "'groups' : [{"
				+ "'title' : 'Level one CIS'," + "'id' : 2,"
				+ "'children' : 'true'," + "'groups' : [{"
				+ "'title' : 'Intro To Computing and Internet'," + "'id' : 3,"
				+ "'children': 'false'," + "'groups':[]" + "}]" + "}]" + "}";

		try {
			Data d = new Gson().fromJson(json, Data.class);
		} catch (JsonSyntaxException jse) {
			System.out.print(jse.toString());
		}

		


	}
}

class Data {
	// private String title;
	private Long id;
	private Boolean children;
	private List<Data> groups;

	// public String getTitle() { return title; }
	public Long getId() {
		return id;
	}

	public Boolean getChildren() {
		return children;
	}

	public List<Data> getGroups() {
		return groups;
	}

	// public void setTitle(String title) { this.title = title; }
	public void setId(Long id) {
		this.id = id;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public void setGroups(List<Data> groups) {
		this.groups = groups;
	}

	public String toString() {
		return String.format("title:,id:%d,children:%s,groups:%s", id,
				children, groups);
	}
}
