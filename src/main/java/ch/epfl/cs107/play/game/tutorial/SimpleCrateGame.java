package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
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


public class SimpleCrateGame implements Game {
	
	private Window window;
	
	private World world;
	
	private Entity block;
	
	private Entity crate;
	
	private ImageGraphics graphics, graphics2;
	
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));
		
		
		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(1.0f, 0.5f));
		block = entityBuilder.build();
		
		PartBuilder partBuilder = block.createPartBuilder(); //Attribution of a 1x1 polygon to 'bloc'
		
		Polygon polygon = new Polygon (
				new Vector(0.0f, 0.0f),
				new Vector(1.0f, 0.0f),
				new Vector(1.0f, 1.0f),
				new Vector(0.0f, 1.0f)
				);
		partBuilder.setShape(polygon);
		partBuilder.setFriction(0.5f);
		
		partBuilder.build();
		
		graphics2 = new ImageGraphics("stone.broken.4.png", 1, 1);
		graphics2.setAlpha(1.0f);
		graphics2.setDepth(0.0f);
		graphics2.setParent(block);
		
		
		entityBuilder.setPosition(new Vector(0.2f, 4.0f));
		entityBuilder.setFixed(false);
		crate = entityBuilder.build();
		
		PartBuilder pb2 = crate.createPartBuilder();
		pb2.setShape(polygon);
		pb2.build();
		
		graphics = new ImageGraphics("box.4.png", 1, 1);
		graphics.setAlpha(1.0f);
		graphics.setDepth(1.0f);
		graphics.setParent(crate);
		
		
		
		
		return true;
	}
	
	 public void update(float deltaTime) {
	        
	    	//Game logic comes here
	    	//Nothing to do, yet
	    	
	    	//Simulate physics
	    	//Our body is fixed, though, nothing will move
	    	world.update(deltaTime);
	    	
	    	//we must place the camera where we want
	    	//We will look at the origin (identity) and increase the view size a bit
	    	window.setRelativeTransform(Transform.I.scaled(10.0f));
	    	
	    	//We can render our scene now
	    	graphics.draw(window);
	    	graphics2.draw(window);
	        // The actual rendering will be done now, by the program loop
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
	    
}
