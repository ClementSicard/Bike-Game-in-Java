package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class Bike extends GameEntity implements Actor{
	
	private PartBuilder partBuilder;
	private Wheel leftWheel, rightWheel;
	private ShapeGraphics armGraphics, headGraphics;
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
		
//		graphics = new ImageGraphics("billy.png", 6.0f, 4.0f);
//		graphics.setParent(this);
		
		leftWheel = new Wheel(getOwner(), 0.5f, position.add(-1.0f, 0.f));
		rightWheel = new Wheel(getOwner(), 0.5f, position.add(1.0f, 0.f));
		leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f));
		rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
		getOwner().addActor(leftWheel);
		getOwner().addActor(rightWheel);
		
		Circle head = new Circle(0.2f, getHeadLocation());
		headGraphics = new ShapeGraphics(head, Color.YELLOW, Color.ORANGE, 0.02f);
		Polyline arm = new Polyline(getShoulderLocation(), getHandLocation());
		armGraphics = new ShapeGraphics(arm, Color.ORANGE, Color.YELLOW, 0.5f);
	}

	@Override
	public void draw(Canvas canvas) {
		leftWheel.draw(canvas);
		rightWheel.draw(canvas);
		armGraphics.draw(canvas);
		headGraphics.draw(.getCanvas());
	}
	
	public Wheel getLeftWheel() {
		return leftWheel;
	}
	
	public Wheel getRightWheel() {
		return rightWheel;
	}
	
	
	private Vector getHeadLocation() {
		return new Vector(0.0f, 1.75f);
	}
	
	private Vector getHandLocation() {
		return new Vector(0.5f, 1.0f);
	}
	
	private Vector getShoulderLocation() {
		return new Vector(-0.1f, 1.4f);
	}
	
}
