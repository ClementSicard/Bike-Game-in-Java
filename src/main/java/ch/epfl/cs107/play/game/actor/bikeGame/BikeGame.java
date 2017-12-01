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
//	private Bike bike;
	private Wheel Wheel;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		terrain = new Terrain(this);
		Wheel = new Wheel(this, 0.5f, new Vector(-10.0f, 5.0f));
//		rightWheel = new Wheel(this, 0.5f, new Vector(-9.0f, 5.0f));

//		crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
//		crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
//		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
//		Vector position = new Vector(5.0f, 6.0f);
//		bike = new Bike(this, position);
		
		addActor(terrain);
//		addActor(crate1);
//		addActor(crate2);
//		addActor(crate3);
//		addActor(bike);
		addActor(Wheel);
		//addActor(rightWheel);
		this.setViewCandidate(null);
		return true;
	}
	
	 public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-cl
    	
	        if (this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
            { 
	        	Wheel.power(50.0f);
            } 
            else if (this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
            { 
            	Wheel.power(-50.0f);
            }
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
}
