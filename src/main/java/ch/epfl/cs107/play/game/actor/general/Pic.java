package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pic extends Trigger implements Actor {
	private BasicContactListener listener;
	private ImageGraphics graphics;
	
	
	public Pic(ActorGame game, Vector position) {
		super(game, true, position, 1.0f, 1.0f);
		graphics = new ImageGraphics("spikes.png", 1.0f, 1.0f);
		graphics.setParent(this);
		getOwner().addActor(this);
		listener = new BasicContactListener();
		this.getEntity().addContactListener(listener); //Used to record contact with other entities (here the bike for instance) 
	}
	
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
	}
	
	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);
	}
	
}
