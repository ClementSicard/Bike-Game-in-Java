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
	private Crate crate1, crate2, crate3;
	private boolean sight = true;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
		crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
		terrain = new Terrain(this);
		Vector position = new Vector(5.0f, 6.0f);
		bike = new Bike(this, position);

		this.setViewCandidate(null);
		
		return true;
	}
	
	
	public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-class
    	
	        
	        if (this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
            {
	        	
	        } 
            else if (this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
            {
            	
            }
            else if (this.getKeyboard().get(KeyEvent.VK_SPACE).isDown())
            {
            	sight = !sight;
            }
            else if (this.getKeyboard().get(KeyEvent.VK_UP).isDown())
            {
            	
            }
            else if (this.getKeyboard().get(KeyEvent.VK_DOWN).isDown())
            {
            	
            }
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
	    
	    public boolean getSight() {
	    	return sight;
	    }
}
