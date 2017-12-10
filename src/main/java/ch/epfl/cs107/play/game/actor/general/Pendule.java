package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Vector;

public class Pendule extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics ropeGraphics, ballGraphics;
	private Ball ball;
	private Crate crate;
	private BasicContactListener listener;
	
	public Pendule(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		ball = new Ball(game, false, new Vector(2.0f, 2.0f), 0.5f, 1.0f, 0.5f);
		crate = new Crate(game, )
	}
}
