package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class CrateGame extends ActorGame{
	
	private Crate crate1, crate2, crate3;
	
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
		crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
		addActor(crate1);
		addActor(crate2);
		addActor(crate3);
		this.setViewCandidate(null);
		return true;
	}
	
	 public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-cl
	    	
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
}
