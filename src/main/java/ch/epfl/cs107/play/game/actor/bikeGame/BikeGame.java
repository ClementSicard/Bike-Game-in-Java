package ch.epfl.cs107.play.game.actor.bikeGame;

import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	
	private Terrain terrain;
//	private Crate crate1, crate2, crate3;
	private Bike bike;
	private Wheel leftWheel, rightWheel;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		terrain = new Terrain(this);
//		crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
//		crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
//		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
		Vector position = new Vector(5.0f, 6.0f);
		leftWheel = new Wheel(this, 0.5f, position.add(-1.0f, 0.f));
		rightWheel = new Wheel(this, 0.5f, position.add(1.0f, 0.f));
		bike = new Bike(this, position, leftWheel, rightWheel);
		
		addActor(terrain);
//		addActor(crate1);
//		addActor(crate2);
//		addActor(crate3);
//		addActor(bike);
//		addActor(leftWheel);
//		addActor(rightWheel);
		this.setViewCandidate(null);
		return true;
	}
	
	 public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-cl
	    	
	        if (this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
            { 
	        	rightWheel.power(5.0f);
	        	rightWheel.relax();
            } 
            else if (this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
            { 
            	leftWheel.power(-5.0f);
            	leftWheel.relax();
            }
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
}
