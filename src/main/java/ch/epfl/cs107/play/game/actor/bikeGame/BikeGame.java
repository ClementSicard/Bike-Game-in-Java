package ch.epfl.cs107.play.game.actor.bikeGame;

import java.awt.Color;
import java.util.List;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.ui.Window.Level;

import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	
	private Terrain terrain;
	private Bike bike;
//	private Crate crate1, crate2, crate3;
	private Finish flag;
	private boolean sight = true;
    private boolean stayOnScreen, stayOnScreen1;
    private boolean endOfGame;
    protected List<Level> levelList;
    private int level;
    
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		super.begin(window, fileSystem);
		
		if (level == 0)
		{
			//crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
			//crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
	//		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
		//	terrain = new Terrain(this, Color.CYAN, Color.WHITE);
			flag = new Finish(this, new Vector(50.0f, -5.0f));
			Vector position = new Vector(5.0f, 6.0f);
			bike = new Bike(this, position);
			this.setViewCandidate(bike);
		}
		else if (level == 1)
		{
			//crate1 = new Crate(this, false, new Vector(0.0f, 5.0f), 0.5f, 1.0f, 1.0f);
			//crate2 = new Crate(this, false, new Vector(0.2f, 7.0f), 0.5f, 1.0f, 1.0f);
	//		crate3 = new Crate(this, false, new Vector(2.0f, 6.0f), 0.5f, 1.0f, 1.0f);
//			terrain = new Terrain(this, Color.GREEN, Color.WHITE);
			flag = new Finish(this, new Vector(60.0f, 0.0f));
			Vector position = new Vector(5.0f, 6.0f);
			bike = new Bike(this, position);
			this.setViewCandidate(bike);
		}
		return true;
	}
	
	
	public void update(float deltaTime) {
	        super.update(deltaTime); //Calling the update() method from the super-class
	        
	        
	        if (bike.getHit() && endOfGame != true) 
	        {
	        	bike.destroy();
	        	stayOnScreen = true;
		        endOfGame = true;
		        
	        }
	        else if (bike.getHit() && flag.getListener().hasContactWith(bike.getEntity()))
	        {
	        	bike.destroy();
	        }
	        
	        if (flag.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
	        {
	        	stayOnScreen1 = true;
	        	endOfGame = true;

		        bike.getRightWheel().power(0.0f);
		        bike.getLeftWheel().power(0.0f);
	        }
	        
	        if (stayOnScreen1)
	        {
	        	bike.setArmsUp(true);
	        	displayEndOfTheGame();
	        }
	        else if (stayOnScreen && bike.getHit())
        	{
        		displayGameOver();
        	}

	 
	        
	        //Setting up to date the movement of the biker
		    if (endOfGame != true)
		    {		
		        if ((this.getKeyboard().get(KeyEvent.VK_UP).isDown())) {
		        	if (bike.getOrientation())
		        	{
		        		bike.goRight();
		        	}
		        	else
		        	{
		        		bike.goLeft();
		        	}
				}
		        
		        if(endOfGame != true)
			        {
			        if ((this.getKeyboard().get(KeyEvent.VK_DOWN).isDown())) {
						bike.getRightWheel().power(0.f);
						bike.getLeftWheel().power(0.f);
						
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_SPACE).isPressed())) {
						bike.changeOrientation();
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_LEFT).isDown())) 
			        {
			        	bike.getBike().applyAngularForce(40.0f);
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown())) {
						bike.getBike().applyAngularForce(-40.0f);
					}
			    }
		    }
		    else if (endOfGame = true)
		    {
		    	bike.getLeftWheel().power(0.0f);
		    	bike.getRightWheel().power(0.0f);
		    	
			    if (this.getKeyboard().get(KeyEvent.VK_R).isPressed()) //When [R] is pressed, the game starts over
			    {
			    	removeAllActors();
			    	endOfGame = false;
			    	stayOnScreen = false;
			    	stayOnScreen1 = false;
			    	bike.setHit(false);
			    	startOver(this, deltaTime);
			    }
			    else if (this.getKeyboard().get(KeyEvent.VK_ENTER).isPressed() && stayOnScreen1)
			    {
			    	removeAllActors();
			    	endOfGame = false;
			    	stayOnScreen = false;
			    	stayOnScreen1 = false;
			    	bike.setHit(false);
			    	level++;
			    	if(level <= 1)
			    	{
			    		startOver(this, deltaTime);
			    	}
			    	else
			    	{
			    		
			    	}
			    }
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
	    
	    @Override
	    public Finish getFinish() {
	    	return this.flag;
	    } 
}
