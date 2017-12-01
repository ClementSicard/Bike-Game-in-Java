package ch.epfl.cs107.play.game.actor.bikeGame;

import ch.epfl.cs107.play.game.actor.ImageGraphics;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	
	private Terrain terrain;
	private Crate crate1, crate2, crate3;
	private Bike bike;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		terrain = new Terrain(this);
		crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
		crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
		bike = new Bike(this, new Vector(4.0f, 5.0f));
		addActor(terrain);
		addActor(crate1);
		addActor(crate2);
		addActor(crate3);
		addActor(bike);
		this.setViewCandidate(null);
		return true;
	}
	
	 public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-cl
	    	
	        if (this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
            { 
	        	bike.getRightWheel().power(5.0f);
	        	
            } 
            else if (this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
            { 
            	bike.getLeftWheel().power(-5.0f);
            }
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
}
