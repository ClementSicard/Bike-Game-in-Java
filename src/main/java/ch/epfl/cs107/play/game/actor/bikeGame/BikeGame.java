package ch.epfl.cs107.play.game.actor.bikeGame;

import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	
	private Terrain terrain;
	private Bike bike;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		terrain = new Terrain(this);
		Vector position = new Vector(5.0f, 6.0f);
		bike = new Bike(this, position);

		addActor(terrain);
		addActor(bike);
		this.setViewCandidate(null);
		
		return true;
	}
	
	
	 public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-class
    	
	        if (this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
            { 
	        	bike.getLeftWheel().power(5.0f);
	        	bike.getRightWheel().relax();
            } 
            else if (this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
            { 
            	bike.getRightWheel().power(-5.0f);
            	bike.getLeftWheel().relax();
            }
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
}
