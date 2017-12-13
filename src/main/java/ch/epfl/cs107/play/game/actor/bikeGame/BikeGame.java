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

	private boolean orientation = true, finalMenu, newLevel, displayGO, displayWD, displayFM, displayCPM, endOfGame, coinContact, cantMove;
    protected List<Level> levelList;
    private int levelIndex = 0;
    private Level level;
    private Bike bike;
    private Checkpoint checkpoint;
    private Terrain terrain;
    private Finish flag;
    private Bascule bascule;
    private Pendule pendule;
    private Coin coin1, coin2, coin3;
    private Pic pic;
    private Nuage nuage;
    private Tremplin tremplin;
    private TextGraphics scoreText;
    private int score = 0;
    private float time = 0.f, alpha = 1.5f, alpha2 = 1.5f, alpha3 = 1.5f, msgTime, freeze, checkpointTimer; //Used for display of text graphics
    
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		levelList = createLevelList();
		startLevel(levelIndex);

		return true;

	}
	
	
	public void update(float deltaTime) {
		 super.update(deltaTime); //Calling the update() method from the super-class

		 	displayScore();
		 
	    	displayNextLevel(deltaTime);
	    	
		    handleContact(coin1);
		    handleContact(coin2);
		    handleContact(coin3);
		    
		    displayScoreMessage(deltaTime); //The conditions of display are implemented within the method
		    
		    handleContact(pic, deltaTime);
		    handleContact(tremplin);
		    handleContact(checkpoint);
		    
		    displayCheckpointMessage(deltaTime);
		    
	    	msgTime += deltaTime;
		    
	    	cases(deltaTime);
	        
	        //Setting up to date the movement of the biker with the help of the keyboard
	        commands(deltaTime);		    
	}

		// This event is raised after game ends, to release additional resources
	    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
	    
	    
	    
	    
	    
	    
	    
	    
	    public boolean getOrientation() {
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
	    	//Gets all the actors of the desired level and links them with the uninstanced attributes of BikeGame
    	  	level = levelList.get(levelIndex);
	        bike = level.getBike();
	        flag = level.getFlag();
	        terrain = level.getTerrain();
	        bascule = level.getBascule();
	        pendule = level.getPendule();
	        coin1 = level.getCoin1();
	        coin2 = level.getCoin2();
	        coin3 = level.getCoin3();
	        nuage = level.getNuage();
	        tremplin = level.getTremplin();
	        pic = level.getPic();
	        checkpoint = level.getCheckpoint();
	        
	        setViewCandidate(bike);
	    }
	    
	    public void reset() {
	    	//Resets all the variables / booleans needed to instance a new level
	    	clearAll();
	    	orientation = true;
	    	coinContact = false;
	    	cantMove = false;
	    	endOfGame = false;
	    	displayGO = false;
	    	displayWD = false;
	    	displayFM = false;
	    	displayCPM = false;
	    	finalMenu = false;
	    	bike.setHit(false);
	    	score = 0; //The score has to be reset any time the level changes/the game is over
	    	time = 0.f; //The value it takes doesn't really matter since it's set to '0.f' when called
	    	checkpointTimer = 0.f; //The same
	    	alpha = 1.5f;
	    	alpha2 = 1.5f;
	    }
	    
	    public void displayNextLevel(float deltaTime) {
	    	//Displays the message with a little fade out
	    	if (levelIndex == 1 && time <= 1.5f && newLevel)
		        {
		        	level.createText(getCanvas(), alpha);
		        	time += deltaTime;
		        	alpha -= deltaTime;
		        }
	    }
	    
	    public void displayScoreMessage(float deltaTime) {
	    	//Displays the message with a little fade out
	    	if (coinContact && msgTime <= 3.0f && alpha2 >= 0.f)
	    	{
	    		TextGraphics graphics = new TextGraphics("+1!", 0.17f, Color.CYAN, Color.WHITE, 0.04f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
	    		graphics.setParent(getCanvas());
	    		graphics.setRelativeTransform(Transform.I.translated(0.1f, -1.0f));
	    		graphics.setAlpha(alpha2);
	    		graphics.draw(getCanvas());
	        	msgTime += deltaTime;
	        	alpha2 -= deltaTime;
	    	}
	    }
	    
	    public void displayCheckpointMessage(float deltaTime) {
			if (displayCPM && checkpointTimer <= 3.0f && alpha3 >= 0.f) 
			{	
	    		TextGraphics message = new TextGraphics("SUCCESS IS CLOSE!", 0.15f, Color.WHITE, Color.RED, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
		    	message.setParent(getCanvas());
		    	message.setRelativeTransform(Transform.I.translated(0.f, -0.8f));
		    	message.setAlpha(alpha3);
		    	message.draw(getCanvas());
		    	checkpointTimer += deltaTime;
		    	alpha3 -= deltaTime;
			}
		}
	    
	    public void handleContact(Coin coin)
	    //Generic method for the Type coin (override)
	    {
	    	if (coin != null)
		    {
		    	if (coin.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
		    	{
		    		score++;
		    		coin.destroy();
		    		removeActor(coin);
		    		coinContact = true;
		    		msgTime = 0.f;
		    	}
		    }
		    
	    }
	    
	    public void handleContact(Pic pic, float deltaTime) {
	    	//Makes the bike decelerate if contact ; a timer is set
	    	if (pic != null)
	    	{
	    		if (pic.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
		    	{
		    		cantMove = true;
		    		freeze = 0.f;
		    	}
	    		if (cantMove && freeze <= 1.5f) 
			    {
			    	bike.getRightWheel().power(5.f);
		    		bike.getLeftWheel().power(5.f);
		    		freeze += deltaTime;
			    } 
			    else if (freeze >= 2.5f) 
			    {
		    		cantMove = false;
			    }
		    }
	    }
	    
	    public void handleContact(Tremplin tremplin) {
	    	//Makes the bike jump if contact with "tremplin"
	    	if (tremplin != null)
	    	{
	    		if (tremplin.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
	    		{
	    			bike.getEntity().applyImpulse(new Vector(0.5f, 0.2f), bike.getPosition());
	    		}
	    	}
	    }
	    
	    
	    public void handleContact(Checkpoint checkpoint) {
	    	if (checkpoint != null)
	    	{
	    		if (checkpoint.getListener().hasContactWith(bike.getEntity()) && endOfGame != true) //Displays the encouraging message if the condition is fulfilled
	    		{
	    			displayCPM = true; //Makes the linked message display
	    		}
	    	}
	    }
	    	
	    public void displayScore() {
		    if (endOfGame != true)
		    //The color of the border changes depending on the levelIndex
		    {
		    	if (levelIndex == 0)
		        {
		        	scoreText = new TextGraphics("Score : " + score , 0.12f, Color.WHITE, Color.CYAN, 0.03f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
		        }
		        else if (levelIndex == 1)
		        {
		        	scoreText = new TextGraphics("Score : " + score , 0.12f, Color.WHITE, Color.GREEN, 0.03f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
		        }
			 	scoreText.setParent(getCanvas());
		    	scoreText.setRelativeTransform(Transform.I.translated(-0.65f, -0.65f));
		    	scoreText.draw(getCanvas());
		    }
	    }
	    
	    public void commands(float deltaTime) {
	    	if (endOfGame != true)
		    {		
		        if ((this.getKeyboard().get(KeyEvent.VK_UP).isDown())) {
		        	if (bike.getOrientation()) //Cases if the bike is right or left oriented
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
			        if ((this.getKeyboard().get(KeyEvent.VK_DOWN).isDown())) { //Stops the wheels if the lower arrow is pressed
						bike.getRightWheel().power(0.f);
						bike.getLeftWheel().power(0.f);
						
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_SHIFT).isPressed())) { //Changes the orientation of the bike if [SHIFT] is pressed
						bike.changeOrientation();
					}

			        
			        if ((this.getKeyboard().get(KeyEvent.VK_LEFT).isDown()))  //Raises the right wheel
			        {
			        	bike.getBike().applyAngularForce(40.0f);
					}
			        
			        if ((this.getKeyboard().get(KeyEvent.VK_RIGHT).isDown())) {  //Raises the left wheel
						bike.getBike().applyAngularForce(-40.0f);
					}
			    }
		    }
		    else if (endOfGame = true) //Handles the interaction with the keyboard when the the level is finished
		    {
		    	bike.getLeftWheel().power(0.0f);
		    	bike.getRightWheel().power(0.0f);
		    	
		    	if(levelIndex == 0 && (displayWD || displayFM))
		    	{
		    		levelIndex++;
		    	}
		    	
			    if (this.getKeyboard().get(KeyEvent.VK_R).isPressed() && endOfGame == true) //When [R] is pressed, the game starts over
			    {
			    	 newLevel = false; //This boolean makes the 'Next Level' message display if it's set to true
			    	 reset();
			    	 startOver(this, deltaTime);
			    }
			    if (this.getKeyboard().get(KeyEvent.VK_ENTER).isPressed() && levelIndex == 1 && displayFM == false)
			    //Passes to next level is the key [ENTER] is pressed
			    {
			    	reset();
			    	startOver(this, deltaTime);
			    	newLevel = true; //Makes the linked message display
			    }
			    if (this.getKeyboard().get(KeyEvent.VK_1).isPressed() || this.getKeyboard().get(KeyEvent.VK_NUMPAD1).isPressed() && displayFM) //Allows compatibility for computers which don't have a numpad
	    		//Restarts the game at level 1
			    {
	    			reset();
	    			levelIndex = 0;
	    			startOver(this, deltaTime);
	    		}
	    		if (this.getKeyboard().get(KeyEvent.VK_2).isPressed() || this.getKeyboard().get(KeyEvent.VK_NUMPAD2).isPressed()&& displayFM) //Allows compatibility for computers which don't have a numpad
	    		//Restarts the game at level 2
	    		{
	    			reset();
	    			levelIndex = 1;
	    			newLevel = false;
	    			startOver(this, deltaTime);
	    		}
	    		if (this.getKeyboard().get(KeyEvent.VK_ESCAPE).isPressed() && displayFM)
	    		{
	    			reset();
	    			System.exit(1); //Stops the execution of the program when ESCAPE is pressed in this particular case
	    		}
			    
		    }
	    }
	    
	    
	    public void cases(float deltaTime) {
	    	
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
	    	
	    	if (finalMenu && endOfGame == true && levelIndex == levelList.size()-1)
	    	{
	    		finalMenu(deltaTime);
	    	}
	        
	        if (flag.getListener().hasContactWith(bike.getEntity()) && endOfGame != true)
	        {
	        	if (levelIndex == 0)
	        	{
		        	displayWD = true;
	        	}
	        	if (levelIndex == 1)
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
	        	finalMenu = true;
	        }
	        
	        if (alpha <= 0.f) {
	    		alpha = 1.5f;
	    	}
	        
	        if (alpha2 <= 0.f) {
	        	alpha2 = 1.5f;
	        }
	    }
	    
	    
	    public void finalMenu(float deltaTime) {
	    	if (this.getKeyboard().get(KeyEvent.VK_1).isPressed())
    		{
    			reset();
    			levelIndex = 0;
    			startOver(this, deltaTime);
    		}
    		else if (this.getKeyboard().get(KeyEvent.VK_2).isPressed())
    		{
    			reset();
    			levelIndex = 1;
    			startOver(this, deltaTime);
    		}
    		else if (this.getKeyboard().get(KeyEvent.VK_ESCAPE).isPressed())
    		{
    			reset();
    			System.exit(1);
    		}
	    }
}
	    