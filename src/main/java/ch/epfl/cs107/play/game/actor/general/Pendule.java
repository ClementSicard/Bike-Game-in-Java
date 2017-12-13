package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.RopeConstraint;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pendule extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private Ball ball;
	private Crate crate;
	private BasicContactListener listener;
	private RopeConstraintBuilder builder;
	private RopeConstraint constraint;
	private ShapeGraphics graphics;
	
	public Pendule(ActorGame game, Vector position) {
		super(game, true, position);
		//Instances the objects needed for the creation
		ball = new Ball(game, false, position.add(-4.0f, 0.0f), 0.5f);
		crate = new Crate(game, true, position, 5.0f, 1.0f, 1.0f);
		builder = getOwner().createRopeConstraintBuilder(); //Using the 'world' attribute in class ActorGame
		//A rope constraint is built between the ball and the crate
		builder.setFirstEntity(crate.getEntity());
		builder.setFirstAnchor(new Vector(0.5f, 0.5f));
		builder.setSecondEntity(ball.getEntity());
		builder.setSecondAnchor(Vector.ZERO);
		builder.setMaxLength(4.0f);
		builder.setInternalCollision(true);
		constraint = builder.build();
		getOwner().addActor(crate);
		getOwner().addActor(ball);
		getOwner().addActor(this);
	}

	@Override
	public void draw(Canvas canvas) {
		//Has to draw both ball and crate
		ball.draw(canvas);
		crate.draw(canvas);
		//The following lines allow the rope between the crate and the ball to be drawn, using the position of each entity
		Polyline polyline= new Polyline(ball.getEntity().getPosition(), crate.getEntity().getPosition().add(0.5f, 0.5f));
		graphics = new ShapeGraphics(polyline, Color.WHITE, Color.WHITE, 0.1f);
		graphics.setDepth(0.0f);
		graphics.draw(canvas);
		
	}
	
	public void destroy() {
		//Destroy method has to destroy the ball and the crate so that the 'Pendule' is fully destroyed
		ball.getEntity().destroy();
		crate.getEntity().destroy();
		getOwner().removeActor(ball);
		getOwner().removeActor(crate);
	}
}
