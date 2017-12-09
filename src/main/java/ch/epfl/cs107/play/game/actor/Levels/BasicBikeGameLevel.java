package ch.epfl.cs107.play.game.actor.Levels;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.general.Bike;
import ch.epfl.cs107.play.game.actor.general.Crate;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class BasicBikeGameLevel extends Level implements Actor {
	

	protected Bike bike;
	protected Finish flag;
	protected Terrain terrain;
	protected ActorGame game;
	
	public BasicBikeGameLevel(ActorGame game) {
		this.game = game;
	}
	
	public void createAllActors() {
		terrain = new Terrain(game, Color.CYAN, Color.WHITE);
		bike = new Bike(game, new Vector(-5.0f, 10.0f)); 
		flag = new Finish(game, new Vector(50.0f, -4.0f));
		game.addActor(terrain);
		game.addActor(bike);
		game.addActor(flag);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
	}
	
	public Bike getBike() {
		return bike;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public Finish getFlag() {
		return flag;
	}

	@Override
	public Crate getCrate() {
		// TODO Auto-generated method stub
		return null;
	}
}
