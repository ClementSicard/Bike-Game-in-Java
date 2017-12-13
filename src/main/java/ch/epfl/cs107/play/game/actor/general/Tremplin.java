package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.math.Vector;


public class Tremplin extends Trigger implements Actor {

	private ImageGraphics graphics;
	private BasicContactListener listener;
	
	public Tremplin(ActorGame game, Vector position) {
		
		super(game, true, position, 1.f, .4f); 
		getOwner().addActor(this);
		listener = new BasicContactListener();		
		this.getEntity().addContactListener(listener); //Used in order to record contacts with other Entities
	}
	
	
	@Override
	public Vector getPosition() {
		return getEntity().getPosition();
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		//The graphic representation changes whether the instance has a contact with the bike or not (normal/extended)
		if (!this.getListener().hasContacts())
		{
			graphics = new ImageGraphics("jumper.normal.png", 1.f, 1.f);
			graphics.setParent(this);
		}
		else
		{
			graphics = new ImageGraphics("jumper.extended.png", 1.f, 1.f);
			graphics.setParent(this);
		}
		graphics.draw(canvas);
	}
	
	
	public void destroy() {
		getEntity().destroy();
	}
}
