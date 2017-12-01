package ch.epfl.cs107.play.game.actor;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public abstract class ActorGame implements Game{
	
	//List of actors intervening
	private ArrayList<Actor> listActors;

	//Word in which the Actors evolve
	private World world;
	
	//External data required to initialize it as a Game
	private Window window;
	private FileSystem fileSystem;
	
	//Viewport properties
	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate; //Used to center the camera on a Positionable
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f;
	private static final float VIEW_SCALE = 10.0f;
	
	
	public Keyboard getKeyboard() {
		return window.getKeyboard();
	}
	
	public Canvas getCanvas() {
		return window;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;
		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));
		viewCenter = Vector.ZERO;
		viewTarget = Vector.ZERO;
		listActors = new ArrayList<Actor>(); 
		
		return true;
	}
	
	
	public void update(float deltaTime) {
		
		world.update(deltaTime);
		
		for (Actor actor : listActors) 
		{
			actor.update(deltaTime);
		}
        
     // Update expected viewport center
        if (viewCandidate != null)
        {
	        viewTarget = viewCandidate.getPosition().add(viewCandidate.getVelocity().mul(VIEW_TARGET_VELOCITY_COMPENSATION)) ;
        }
        
        // Interpolate with previous location
        float ratio = (float)Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND , deltaTime) ;
        viewCenter = viewCenter.mixed(viewTarget , ratio) ;
        // Compute new viewport
        Transform viewTransform =
        Transform.I.scaled(VIEW_SCALE).translated(viewCenter) ;
        window.setRelativeTransform(viewTransform) ;
        
        for (Actor actor : listActors) {
        	actor.draw(window);
        }
		
	}
	
	public void end() {}
	
	
	public void setViewCandidate(Positionable viewCandidate) {
		this.viewCandidate = viewCandidate;
		
	}
	
	public EntityBuilder createEntityBuilder() {
		return world.createEntityBuilder();
	}
	
	public void addActor(Actor actor) {
		listActors.add(actor);
	}
	
	public void removeActor(Actor actor) {
		for (Actor a : listActors) 
		{
			if (a == actor)
			{
				listActors.remove(a);
			}
		}
	}
}
