package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.math.RevoluteConstraint;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bascule extends GameEntity implements Actor {

	private Ball ball;
	private Plank plank;
	private RevoluteConstraintBuilder revoluteConstraintBuilder;
	private RevoluteConstraint constraint;
	
	public Bascule (ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		ball = new Ball (game, false, new Vector(2.0f, 2.0f), 0.5f);
		plank = new Plank (game, false, new Vector(-4.0f, 2.0f), 1.0f, 0.2f, 5.0f);
		revoluteConstraintBuilder = getOwner().createRevoluteConstraintBuilder () ;
		revoluteConstraintBuilder.setFirstEntity(ball.getEntity());
  		revoluteConstraintBuilder.setFirstAnchor(new Vector(0.5f, 0.5f));
  		revoluteConstraintBuilder.setSecondEntity(plank.getEntity());
  		revoluteConstraintBuilder.setSecondAnchor(new Vector(2.0f, -0.2f));
  		revoluteConstraintBuilder.setInternalCollision(true) ;
  		constraint = revoluteConstraintBuilder.build ();     
  		getOwner().addActor(ball);
  		getOwner().addActor(plank);
}

	@Override
	public void draw(Canvas canvas) {
		plank.draw(canvas);
		ball.draw(canvas);
	}
	
	public void destroy() {
		constraint.destroy();
		ball.getEntity().destroy();
		plank.getEntity().destroy();
		getOwner().removeActor(ball);
		getOwner().removeActor(plank);
	}
}

