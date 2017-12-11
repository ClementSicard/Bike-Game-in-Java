package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.BasicContactListener;


public class Checkpoint extends Trigger implements Actor {
	
	private BasicContactListener listener;
	private ImageGraphics graphics;
	private int score;
	
	
	
	public Checkpoint(ActorGame game, boolean fixed, String image, Vector position, float width, float height) {
		super(game, fixed, position, image, width, height);
		
		graphics = new ImageGraphics(image, width, height);
		graphics.setParent(this); 
		getOwner().addActor(this);
		listener = new BasicContactListener();		
		this.getEntity().addContactListener(listener);
	}
	
	
	public void scoreIncrementation() {
		score++;
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);
	}
}
