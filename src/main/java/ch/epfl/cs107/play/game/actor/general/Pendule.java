package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
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
	
	public Pendule(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		partBuilder = getEntity().createPartBuilder();
//		Polyline polyline= new Polyline(, position);
//		graphics = new ShapeGraphics(polyline, Color.WHITE, Color.WHITE, 1.0f);
		ball = new Ball(game, false, position.add(-4.0f, 0.0f), 0.5f);
		crate = new Crate(game, true, position, 5.0f, 1.0f, 1.0f);
		
		builder = getOwner().createRopeConstraintBuilder();
		builder.setFirstEntity(crate.getEntity());
		builder.setFirstAnchor(new Vector(0.5f, 0.5f));
		builder.setSecondEntity(ball.getEntity());
		builder.setSecondAnchor(Vector.ZERO);
		builder.setMaxLength(4.0f);
		builder.setInternalCollision(true);
		constraint = builder.build();
		getOwner().addActor(crate);
		getOwner().addActor(ball);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		ball.draw(canvas);
		crate.draw(canvas);
//		graphics.draw(canvas);
		
	}
	
	public void destroy() {
		ball.getEntity().destroy();
		crate.getEntity().destroy();
		getOwner().removeActor(ball);
		getOwner().removeActor(crate);
	}
}
