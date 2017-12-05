package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class Bike extends GameEntity implements Actor{
	
	private PartBuilder partBuilder;
	private Wheel leftWheel, rightWheel;
	private ShapeGraphics armGraphics, headGraphics, rearGraphics, kneeGraphics, leftLegGraphics, rightLegGraphics, couisseGraphics;
	private ImageGraphics billyGraphics;
	private boolean sight = true;

	
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
		getOwner().addActor(this);
	}

	@Override
	public void draw(Canvas canvas) {
		Circle head = new Circle(0.2f, getHeadLocation());
		headGraphics = new ShapeGraphics(head, Color.YELLOW, Color.ORANGE, 0.02f);
		headGraphics.setParent(this);
		
		billyGraphics = new ImageGraphics("billy.png", 0.0f, 4.0f);
		billyGraphics.setParent(this);
	
		Polyline arm = new Polyline(getShoulderLocation(), getHandLocation());
		armGraphics = new ShapeGraphics(arm, Color.ORANGE, Color.YELLOW, 0.1f);
		armGraphics.setParent(this);
		
		Polyline rear = new Polyline(getShoulderLocation(), getRearLocation());
		rearGraphics = new ShapeGraphics(rear, Color.ORANGE, Color.YELLOW, 0.1f);
		rearGraphics.setParent(this);
		
		Polyline couisse = new Polyline(getRearLocation(), getKneeLocation());
		couisseGraphics = new ShapeGraphics(couisse, Color.ORANGE, Color.YELLOW, 0.1f);
		couisseGraphics.setParent(this);
		
		Polyline leftLeg = new Polyline(getKneeLocation(), getLeftFoot());
		leftLegGraphics = new ShapeGraphics(leftLeg, Color.ORANGE, Color.YELLOW, 0.1f);
		leftLegGraphics.setParent(this);
		
		Polyline rightLeg = new Polyline(getKneeLocation(), getRightfoot());
		rightLegGraphics = new ShapeGraphics(rightLeg, Color.ORANGE, Color.YELLOW, 0.1f);
		rightLegGraphics.setParent(this);
		
		leftWheel.draw(canvas);
		rightWheel.draw(canvas);
		billyGraphics.draw(canvas);
		headGraphics.draw(canvas);
		armGraphics.draw(canvas);
		rearGraphics.draw(canvas);
		leftLegGraphics.draw(canvas);
		rightLegGraphics.draw(canvas);
		couisseGraphics.draw(canvas);
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
		if (sight)
		{
			return new Vector(0.5f, 1.0f);
		}
		else
		{
			return new Vector(-0.5f, 1.0f);
		}
	}
	
	private Vector getShoulderLocation() {
		if (sight)
		{
			return new Vector(-0.1f, 1.4f);
		}
		else
		{
			return new Vector(0.1f, 1.4f);
		}
	}
	
	private Vector getRearLocation() {
		if (sight) 
		{
			return new Vector(-0.5f, 0.8f);
		}
		else
		{
			return new Vector(0.5f, 0.8f);
		}
	}
	
	private Vector getKneeLocation() {
		
		return new Vector(0.f, 0.5f);
	}
	
	private Vector getLeftFoot() {
		if (getOwner().getSight())
		{
			return new Vector(-0.1f, 0.1f);
		}
		else
		{
			return new Vector(0.1f, 0.1f);
		}
	}
	
	private Vector getRightfoot() {
		if (getOwner().getSight())
		{
			return new Vector(0.1f, 0.1f);
		}
		else
		{
			return new Vector(-0.1f, 0.1f);
		}
	}
	
	public void stopWheels() {
		leftWheel.setMotorState(false);
		rightWheel.setMotorState(false);
	}
	
	public void startWheels() {
		leftWheel.setMotorState(true);
		rightWheel.setMotorState(true);
	}
	
	public float getSpeedConstant() {
		return leftWheel.MAX_WHEEL_SPEED;
	}
	
	public void update() {
		
		
	}
	
	public Entity getBike() {
		
		return this.getEntity();
	}
}
