package ch.epfl.cs107.play.game.actor.bikeGame;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.Levels.BasicBikeGameLevel;
import ch.epfl.cs107.play.game.actor.Levels.CrazyEpicGameLevel;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {

	private boolean orientation = true, displayGO, displayWD, displayFM, endOfGame;
    protected List<Level> levelList;
    private int level = 0;
    private Level level1;
    private Bike bike;
    private Terrain terrain;
    private Finish flag;
    private Bascule bascule;
    private Pendule pendule;
    private Collectable collectable;
    private TextGraphics scoreText;
    private int score = 0;
    private float time = 0.f, alpha = 1.5f;
    
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		levelList = createLevelList();
		startLevel(level);

		return true;

	}
	
	
	public void update(float deltaTime) {
		 super.update(deltaTime); //Calling the update() method from the super-class
	        
		 	scoreText = new TextGraphics("Score : " + score , 0.3f, Color.WHITE, Color.WHITE, 0.01f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
		 	scoreText.setParent(getCanvas());
	    	scoreText.setRelativeTransform(Transform.I.translated(0.0f, 0.0f));
	    	scoreText.draw(getCanvas());
	    	
	    	displayNextLevel(deltaTime);
	    	
		    if (collectable != null)
		    {
		    	if (collectable.getListener().hasContactWith(bike.getEntity()))
		    	{
		    		score++;
		    		collectable.destroy();
		    	}
		    }
	        
	        if (bike.getHit() && endOfGame != true) 
	        {
	        	bike.destroy();
	        	displayGO = true;
		        endOfGame = true; 
	        }
	        else if (bike.getHit() && flag.getListener().hasContactWith(bike.getEntity()))
	        {
	        	bike.destroy();
	        }
	        
	        if (flag.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
	        {
	        	if (level != levelList.size()-1)
	        	{
		        	displayWD = true;
	        	}
	        	if (level == levelList.size() - 1)
	        	{
	        		displayFM = true;
	        	}
	        	endOfGame = true;
		        bike.getRightWheel().power(0.0f);
		        bike.getLeftWheel().power(0.0f);
	        }
	        
	        if (displayWD)
	        {
	        	bike.setArmsUp(true);
	        	displayEndOfTheGame();
	        }
	        else if (displayGO && bike.getHit())
	     	{
	     		displayGameOver();
	     	}
	        else if (displayFM)
	        {
	        	displayFinalMessage();
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
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_SHIFT).isPressed())) {
						bike.changeOrientation();
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_SPACE).isPressed())) { //Makes the bike jump
			        	bike.getEntity().applyImpulse(new Vector(4.0f, 15.0f), bike.getEntity().getPosition());
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
		    	
			    if (this.getKeyboard().get(KeyEvent.VK_R).isPressed() && endOfGame == true) //When [R] is pressed, the game starts over
			    {
			    	 removeAllActors();
			    	 endOfGame = false;
			    	 displayWD = false;
			    	 displayGO = false;
			    	 displayFM = false;
			    	 bike.setHit(false);
			    	 startOver(this, deltaTime);
			    }
			    if (this.getKeyboard().get(KeyEvent.VK_ENTER).isPressed() && (displayWD || displayFM))
			    {
			    	destroyAllEntities();
			    	removeAllActors();
			    	reset();
			    	level++;
			    	
			    	if(level < levelList.size() - 1)
			    	{
			    		startOver(this, deltaTime);
			    	}
			    	else if (level == levelList.size() -1 )
			    	{
			    		level %= levelList.size();
			    		
			    		
			    			reset();
			    			startOver(this, deltaTime);
			    		}
			    	}
			    }
		    }

	

		// This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
	    
	    public boolean getorientation() {
	    	return orientation;
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
	    
	    public void startLevel(int a) {
	      	levelList.get(a).createAllActors();
	      	instanciateActors();
	    }
	    
	    @Override
	    public void addActor(Actor actor) {
	    	super.addActor(actor);
	    }
	    
	    public void instanciateActors() {
    	  	level1 = levelList.get(level);
	        bike = level1.getBike();
	        flag = level1.getFlag();
	        terrain = level1.getTerrain();
	        bascule = level1.getBascule();
	        pendule = level1.getPendule();
	        collectable = level1.getCollectable();
	        setViewCandidate(bike);
	    }
	    
	    public void reset() {
	    	clearAll();
	    	endOfGame = false;
	    	displayGO = false;
	    	displayWD = false;
	    	displayFM = false;
	    	bike.setHit(false);
	    }
	    
	    public void displayNextLevel(float deltaTime) {
	    	 if (level <= levelList.size() - 1 && time <= 1.5f && level !=0)
	        {
	        	level1.createText(getCanvas(), alpha);
	        	time += deltaTime;
	        	alpha -= deltaTime;
	        }
	    }
}