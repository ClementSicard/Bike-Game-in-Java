package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor{
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	public final static float MAX_WHEEL_SPEED = 20.0f;
	
	
	public Bike(ActorGame game, Vector position) {
		
		super(game, false, position);
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon(
				0.0f, 0.5f,
				0.5f, 1.0f,
				0.0f, 2.0f,
				-0.5f, 1.0f);
		partBuilder.setShape(polygon);
		partBuilder.setFriction(0.5f);
		
		partBuilder.setGhost(true);
		partBuilder.build();
		Wheel wheel1 = new Wheel(getOwner(), 0.5f, new Vector(4.0f, 5.0f));
		Wheel wheel2 = new Wheel(getOwner(), 0.5f, new Vector(4.0f, 5.0f));
		
		graphics.setParent(this);
		game.addActor(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
}
