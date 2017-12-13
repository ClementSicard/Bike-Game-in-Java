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
	
	public Bascule (ActorGame game, boolean fixed, Vector position, float ballRadius) {
		super(game, fixed, position);
		//Instances the objects needed for the creation
		ball = new Ball (game, true, position.add(1.f, 1.5f), ballRadius);
		plank = new Plank (game, false, position.add(-2.0f, 3.0f), 1.0f, 0.2f, 5.0f);
		revoluteConstraintBuilder = getOwner().createRevoluteConstraintBuilder(); //Uses the method from ActorGame to be able to use the attribute 'world'
		revoluteConstraintBuilder.setFirstEntity(ball.getEntity());
  		revoluteConstraintBuilder.setFirstAnchor(new Vector(0.5f, 0.5f));
  		revoluteConstraintBuilder.setSecondEntity(plank.getEntity());
  		revoluteConstraintBuilder.setSecondAnchor(new Vector(3.0f, 0.1f));
  		revoluteConstraintBuilder.setInternalCollision(true) ;
  		constraint = revoluteConstraintBuilder.build();     
  		getOwner().addActor(ball);
  		getOwner().addActor(plank);
  		getOwner().addActor(this);
}

	@Override
	public void draw(Canvas canvas) {
		//Has to draw both ball and plank
		plank.draw(canvas);
		ball.draw(canvas);
	}
	
	public void destroy() {
		//Deletes both plank and fixed ball
		constraint.destroy();
		ball.getEntity().destroy();
		plank.getEntity().destroy();
		this.getEntity().destroy();
		getOwner().removeActor(ball);
		getOwner().removeActor(plank);
		getOwner().removeActor(this);
	}
}

