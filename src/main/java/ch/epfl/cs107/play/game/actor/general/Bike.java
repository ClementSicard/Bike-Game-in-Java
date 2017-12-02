package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class Bike extends GameEntity implements Actor{
	
	private PartBuilder partBuilder;
	private Wheel leftWheel, rightWheel;
//	private ShapeGraphics graphics;
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
		partBuilder.setGhost(true);
		partBuilder.build();
		
//		graphics = new ShapeGraphics(polygon, Color.RED, Color.RED, 0.0f);
//		graphics.setParent(this);
		
		leftWheel = new Wheel(getOwner(), 0.5f, position.add(-1.0f, 0.f));
		rightWheel = new Wheel(getOwner(), 0.5f, position.add(1.0f, 0.f));
		leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f));
		rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
		getOwner().addActor(leftWheel);
		getOwner().addActor(rightWheel);
	}

	@Override
	public void draw(Canvas canvas) {
		leftWheel.draw(canvas);
		rightWheel.draw(canvas);
//		graphics.draw(canvas);
	}
	
	public Wheel getLeftWheel() {
		return leftWheel;
	}
	
	public Wheel getRightWheel() {
		return rightWheel;
	}
}
