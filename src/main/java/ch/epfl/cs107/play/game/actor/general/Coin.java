package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Vector;

public class Coin extends Trigger {

	public Coin(ActorGame game, boolean fixed, Vector position, String name, float width, float height) {
		super(game, fixed, position, name, width, height);
	}
}
