package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	
	public Wheel(ActorGame game, float ballRadius, Vector position) {
		
		super(game, false, position);
		partBuilder = getEntity().createPartBuilder();
		Circle circle = new Circle(ballRadius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(2.0f);
		partBuilder.build();
	}


	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
}
