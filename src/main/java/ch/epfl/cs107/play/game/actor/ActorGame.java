package ch.epfl.cs107.play.game.actor;

import java.awt.Color;
import java.util.ArrayList;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.actor.general.Finish;

public abstract class ActorGame implements Game{
	
	//List of actors intervening
	private ArrayList<Actor> listActors;

	//Word in which the Actors evolve
	private World world;
	
	//External data required to initialize it as a Game
	private Window window;
	private FileSystem fileSystem;
	private boolean sight = true;
	private Finish finish;
	
	//Viewport properties
	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate; //Used to center the camera on a Positionable
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f;
	private static final float VIEW_SCALE = 10.0f;
	private TextGraphics message;
	
	
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
	
	public WheelConstraintBuilder createWheelConstraintBuilder() {
		return world.createWheelConstraintBuilder();
	}
	
	public void addActor(Actor actor) {
		listActors.add(actor);
	}
	
	public void removeActor(Actor actor) {
		for (int a = listActors.size() - 1; a >= 0; a--) 
			
			/*The array is explored from end to beginning
			 *in* order to avoid a ConcurrentModificationException
			 *when actors are removed from the game
			 */
		{
			if (listActors.get(a) == actor)
			{
				listActors.remove(a);
			}
		}
	}
	
	public void removeAllActors() {
		listActors.removeAll(listActors);
	}
	
	public boolean getSight() {
		return sight;
	}

	public Window getWindow() {
		// TODO Auto-generated method stub
		return window;
	}
	
	public Finish getFinish() {
		return finish;
	}
	
	public int getListActorsSize() {
		return listActors.size();
	}
	
	public void displayGameOver() {
		message = new TextGraphics("GAME OVER", 0.3f, Color.CYAN, Color.BLACK, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	message.setParent(getCanvas());
    	message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
    	message.draw(getCanvas());
    	displayStartOverCommand();
	}
	
	public void displayEndOfTheGame() {
		message = new TextGraphics("WELL DONE!", 0.3f, Color.CYAN, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	message.setParent(getCanvas());
    	message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
    	message.draw(getCanvas());
    	
    	message = new TextGraphics("PRESS [ENTER] FOR NEXT LEVEL", 0.1f, Color.WHITE, Color.GREEN, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	message.setParent(getCanvas());
    	message.setRelativeTransform(Transform.I.translated(0.0f, -1.4f));
    	message.draw(getCanvas());
    	
    	displayStartOverCommand();
	}
	
	public void displayStartOverCommand() {
		message = new TextGraphics("PRESS [R] TO START OVER", 0.1f, Color.WHITE, Color.RED, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	message.setParent(getCanvas());
    	message.setRelativeTransform(Transform.I.translated(0.f, -1.3f));
    	message.draw(getCanvas());
    	
	}
	
	public void startOver(ActorGame game, float deltaTime) {
		game.begin(this.window, this.fileSystem);
	}
	
	public void displayFinalMessage() {
		message = new TextGraphics("PRESS [R] TO START OVER", 0.1f, Color.WHITE, Color.RED, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	message.setParent(getCanvas());
    	message.setRelativeTransform(Transform.I.translated(0.f, -1.3f));
    	message.draw(getCanvas());
	}
}
