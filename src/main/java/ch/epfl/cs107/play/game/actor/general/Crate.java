package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Crate extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	
	
	public Crate (ActorGame game, boolean fixed, Vector position, float friction, float height, float width) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon(
                new Vector(0.0f, 0.0f), 
                new Vector(width, 0.0f), 
                new Vector(width, height),
                new Vector(0.0f, height));
	 	partBuilder.setShape(polygon);
	 	partBuilder.setFriction(0.5f);
	 	partBuilder.build();
	 	graphics = new ImageGraphics("box.4.png", width, height);
	 	graphics.setParent(this);
	 	game.addActor(this);
	 
	}
	 
	public Crate(ActorGame game, boolean fixed, float height, float width) {
		super(game, fixed);
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon(
                new Vector(0.0f, 0.0f), 
                new Vector(width, 0.0f), 
                new Vector(width, height),
                new Vector(0.0f, height));
		partBuilder.setFriction(0.5f);
		partBuilder.setShape(polygon);
		partBuilder.build();
		graphics = new ImageGraphics("box.4.png", width, height);
		graphics.setParent(this);
		game.addActor(this);
	}
	
public void setGraphicsParameters(ImageGraphics graphics, float alpha, float depth) {
	graphics.setAlpha(alpha);
	graphics.setDepth(depth);
}

@Override
public Transform getTransform() { //Overriding the methods implemented from ActorGame abstract class such that they don't return 'null'

	return getEntity().getTransform(); 
}

@Override
public Vector getVelocity() { 
	// TODO Auto-generated method stub
	return getEntity().getVelocity();
}

@Override
public void draw(Canvas canvas) {
	// TODO grAuto-generated method stub
	graphics.draw(canvas);
}
	
}
