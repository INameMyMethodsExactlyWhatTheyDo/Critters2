package assignment5;

import assignment5.Critter.CritterShape;

public class Critter2 extends Critter {
	public String toString() { return "2"; }
	
	
	private int dir;
	//critter move in rectangles
	private int length;
	private int width;
	private int perim;
	/**
	 * This critter class creates critters that move in rectangular paths
	 */
	public Critter2() {
		length = Critter.getRandomInt(Params.world_width/4);
		width = Critter.getRandomInt(Params.world_height/4);
		perim = 0; //2*length + 2*width;
		shape = CritterShape.STAR;

	}
	
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.INDIANRED; 
	}
	
	/**
	 * This method determines the direction of the critter based on the rectangle
	 * @return Returns the direction the critter will go
	 */
	public int chooseDir() {
		//choose a path of the rectangle to go
		if(perim <length) {
			perim++;
			return 0;
		}
		else if(perim <(length+width)) {
			perim++;
			return 2;
		}
		else if(perim <(length+width+length)) {
			perim++;
			return 4;
		}
		else if(perim <(length+width+length+width)) {
			perim = 0;
			return 6;
		}
		return 0;
	}
	@Override
	/**
	 * This method is invoked every worldTimeStep, choose critter direction and reproduction
	 */
	public void doTimeStep() {
		
		int walkOrRun = Critter.getRandomInt(1);
		if(walkOrRun == 0) {
			walk(chooseDir());
		}
		else {
			run(chooseDir());
		}
		
		//reproduce
		if(getEnergy() >= Params.min_reproduce_energy + 20) {
				
			Critter2 child = new Critter2();
			child.length = this.length;
			child.width = this.width;

			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	/**
	 * method determines whether the critter will fight
	 */
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}
	/**
	 * 
	 * @param critter2s list of the type of critters to show stats
	 */
	public static String runStats(java.util.List<Critter> critter2s) {
		int tall = 0;
		int longer = 0;;
		
		for (Object obj : critter2s) {
			Critter2 c = (Critter2) obj;
			if(c.width >c.length) {
				longer++;
			}
			else if(c.length > c.width) {
				tall++;
			}
		}
		System.out.print("" + critter2s.size() + " total Critter2s    ");
		System.out.print("" + tall + " Critter1s make long and wide rectangles    ");
		System.out.print("" + longer + " Critter1s make tall and narrow rectangles    ");

		System.out.println();
		return "critter2";
	}
	
	
//	public void test (List<Critter> l) {
//		
//	}
}
