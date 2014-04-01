package test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Vector2 vecA = new Vector2(1, 1);
		Vector2 vecB = vecA;
		
		MyClass classA = new MyClass(10);
		classA.print();
		
		MyClass classB = classA;
		classB.setVal(88);

		classB.print();
		classA.print();
		
	}

}
