package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor {

	private PartBuilder partBuilder;
	private ImageGraphics wheelGraphics;
	private WheelConstraint constraint;
	private WheelConstraintBuilder constraintBuilder;

	public Wheel(ActorGame game, boolean fixed, Vector position, float radius) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
		
		Circle circle = new Circle(radius);
		partBuilder.setShape(circle);
		partBuilder.setFriction(0.5f);
		partBuilder.build();
		
		wheelGraphics = new ImageGraphics("wheel.png", 2.0f*radius, 2.0f*radius, new Vector (0.5f, 0.5f), 0.5f, 0.5f);
		wheelGraphics.setAlpha(1.0f);
		wheelGraphics.setDepth(0.0f);
		wheelGraphics.setParent(this);	
	}

	
	public void attach(Entity vehicle, Vector anchor, Vector axis) {
		constraintBuilder = getOwner().createWheelConstraintBuilder(); 
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
		constraint.setMotorEnabled(true);
		constraint.setMotorSpeed(speed);

	}
	
	public void relax() {
		constraint.setMotorEnabled(false);
	}
	
	
	public void detach() {
		constraint.destroy();
	}
	
	public float getSpeed() {
		float difference = getEntity().getAngularVelocity() - getEntity().getVelocity().getLength();
		return difference;
	}
	
	@Override
	public void draw(Canvas canvas) {
	
		wheelGraphics.draw(canvas);
	}
	
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
	}
}
