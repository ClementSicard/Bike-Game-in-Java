package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;

import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Checkpoint extends Trigger implements Actor {

	private ImageGraphics checkpointGraphics;
	private BasicContactListener finishListener;
	private int score;
	
	
	public Checkpoint(ActorGame game, boolean fixed, Vector position, String name, float width, float height) {
		super(game, fixed, position, name, width, height);
		
		checkpointGraphics = new ImageGraphics(name, width, height);
		checkpointGraphics.setParent(this); 
		getTriggerOwner().addActor(this);
		
		finishListener = new BasicContactListener();		
		this.getTriggerEntity().addContactListener(finishListener);
	}
	
	public void incrementScore() {
		score++;
	}
	

	@Override
	public void draw(Canvas canvas) {
		checkpointGraphics.draw(canvas);
	}
	
	
	

}
