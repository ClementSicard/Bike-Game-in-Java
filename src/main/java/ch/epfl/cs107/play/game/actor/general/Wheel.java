package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.actor.general.*;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	private WheelConstraint constraint;
	private WheelConstraintBuilder constraintBuilder;
	
	public Wheel(ActorGame game, float ballRadius, Vector position) {
		
		super(game, false, position);
		partBuilder = getEntity().createPartBuilder();
		Circle circle = new Circle(ballRadius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(2.0f);
		partBuilder.build();
		graphics = new ImageGraphics("explosive.hollow.11.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
	}
	
	public Wheel(ActorGame game, float ballRadius) {
		super(game, false);
		partBuilder = getEntity().createPartBuilder();
		Circle circle = new Circle(ballRadius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(2.0f);
		partBuilder.build();
		graphics = new ImageGraphics("explosive.hollow.11.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
	}

	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);		
	}
	
	public void attach(Entity vehicle, Vector anchor, Vector axis) {
		WheelConstraintBuilder constraintBuilder 
		= getOwner().createWheelConstraintBuilder();
		constraintBuilder.setFirstEntity(vehicle);
		constraintBuilder.setFirstAnchor(anchor);
		constraintBuilder.setSecondEntity(getEntity());
		constraintBuilder.setSecondAnchor(Vector.ZERO);
		constraintBuilder.setAxis(axis);
		constraintBuilder.setFrequency(3.0f);
		constraintBuilder.setDamping(0.5f);
		constraintBuilder.setMotorMaxTorque(10.0f);
		constraint = constraintBuilder.build();
	}
	
	public void power(float speed) {
		constraintBuilder = getOwner().createWheelConstraintBuilder();
		constraintBuilder.setMotorEnabled(true);
		constraintBuilder.setMotorSpeed(speed);
		constraint = constraintBuilder.build();
	}
	
	public void relax() {
		constraintBuilder = getOwner().createWheelConstraintBuilder();
		constraintBuilder.setMotorEnabled(false);
		constraintBuilder.setMotorSpeed(0.0f);
		constraint = constraintBuilder.build();
	}
	
	public void detach() {
		constraint.destroy();
	}
	
	public float getSpeed() {
		double difference = getEntity().getAngularVelocity() - getEntity().getVelocity().getLength();
		return Math.abs((float) difference);
	}
	
	
}
