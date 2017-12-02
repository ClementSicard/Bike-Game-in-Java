package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	private WheelConstraint constraint;
	private WheelConstraintBuilder builder;
	private WheelConstraintBuilder[] builderArray = new WheelConstraintBuilder[2];
	private WheelConstraint[] attachArray = new WheelConstraint[1];
	
	
	public Wheel(ActorGame game, float ballRadius, Vector position) {
		
		super(game, false, position);
		partBuilder = getEntity().createPartBuilder();
		Circle circle = new Circle(ballRadius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(5.0f);
		partBuilder.build();
		
		graphics = new ImageGraphics("wheel.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
		graphics.setParent(this);
	}
	
	public Wheel(ActorGame game, float ballRadius) {
		super(game, false);
		partBuilder = getEntity().createPartBuilder();
		Circle circle = new Circle(ballRadius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(3.0f);
		partBuilder.build();
		graphics = new ImageGraphics("explosive.11.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
		graphics.setParent(this);
	}

	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);		
	}
	
	
	public void attach(Entity vehicle, Vector anchor, Vector axis) {
		builder = getOwner().createWheelConstraintBuilder();
		builder.setMotorEnabled(false);
		builder.setFirstEntity(vehicle);
		builder.setFirstAnchor(anchor);
		builder.setSecondEntity(getEntity());
		builder.setSecondAnchor(Vector.ZERO);
		builder.setAxis(axis);
		builder.setFrequency(3.0f);
		builder.setDamping(0.5f);
		builder.setMotorMaxTorque(10.0f);
		builderArray[0] = builder;
		constraint = builder.build();
		attachArray[0] = constraint;
	}
	
	public void power(float speed) {
		builder = builderArray[0];
		builder.setMotorEnabled(true);
		builder.setMotorSpeed(speed);
		constraint = builder.build();
	}
	
	public void relax() {
		builder = builderArray[0];
		builder.setMotorEnabled(false);
	}
	
	public void detach() {
		attachArray[0].destroy();
	}
	
	public float getSpeed() {
		double difference = getEntity().getAngularVelocity() - getEntity().getVelocity().getLength();
		return Math.abs((float) difference);
	}
	
	
}
