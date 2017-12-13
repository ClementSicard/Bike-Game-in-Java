package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageParticle;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Emitter extends GameEntity implements Actor {

	private ImageParticle particle;
	
	//Used to draw Particles
	public Emitter(ActorGame game, boolean fixed, Vector position, Shape shape, String image, float width, float height, Vector anchor, float alpha, float depth) {
		super(game, fixed, position);
		particle = new ImageParticle(image, width, height, anchor, alpha, depth);
		particle.getTransform().onPoint(shape.sample());
		particle.setAcceleration(new Vector(1.0f, 1.0f));
		particle.setAngularAcceleration(1.0f);
		particle.setAngularPosition(1.0f);
		particle.setAngularVelocity(3.0f);
		particle.setVelocity(new Vector(1.0f, 1.0f));
		particle.setPosition(shape.sample());
		particle.setAcceleration(shape.sample());
	}

	@Override
	public void draw(Canvas canvas) {
		particle.draw(canvas);
		
	}
	
	
	
	
}
