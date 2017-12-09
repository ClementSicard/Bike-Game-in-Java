package ch.epfl.cs107.play.game.actor.bikeGame;

import java.util.Arrays;
import java.util.List;
import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.Levels.BasicBikeGameLevel;
import ch.epfl.cs107.play.game.actor.Levels.CrazyEpicGameLevel;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class BikeGameCopie extends ActorGame {

	private boolean sight = true;
    private boolean stayOnScreen, stayOnScreen1;
    private boolean endOfGame;
    protected List<Level> levelList;
    private int level = 0;
    private Level level1;
    private Bike bike;
    private Terrain terrain;
    private Finish flag;
    
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		
		levelList = createLevelList();
		//createText();
		startGame(level);
		
		return true;

	}
	
	
	public void update(float deltaTime) {
	        super.update(deltaTime);  //Calling the update() method from the super-class
	        
	        level1 = levelList.get(level);
	        bike = levelList.get(level).getBike();
	        flag = levelList.get(level).getFlag();
	        terrain = levelList.get(level).getTerrain();
	        setViewCandidate(bike);
	        
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
		        	if (bike.getSight())
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
						bike.changeSight();
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
		    	bike.getLeftWheel().relax();
		    	bike.getRightWheel().relax();
		    	
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
//			    	System.out.println(level);
			    	System.out.println(levelList.size());
			    	
			    	if (level <= levelList.size() - 1)
			    	{
			    		startGame(level);
			    	}
			    	else
			    	{
			    		displayFinalMessage();
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
	    
	    protected List<Level> createLevelList() {
	    	return Arrays.asList(
	    			new BasicBikeGameLevel(this),
	    			new CrazyEpicGameLevel(this));
	    			
	    }
	    
	    public void startGame(int a) {
	      	levelList.get(a).createAllActors();
	    }
	    
	    @Override
	    public void addActor(Actor actor) {
	    	super.addActor(actor);
	    }
}