package ch.epfl.cs107.play.game.actor.Levels;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.general.Bascule;
import ch.epfl.cs107.play.game.actor.general.Bike;
import ch.epfl.cs107.play.game.actor.general.Crate;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Pendule;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class CrazyEpicGameLevel extends Level {
	
	private Bike bike;
	private Finish flag;
	private Terrain terrain;
	private ActorGame game;
	
	public CrazyEpicGameLevel(ActorGame game) {
		this.game = game;
	}
	
	public void createAllActors() {
		terrain = new Terrain(game, new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0.0f, 0.0f,
				10.0f, 1.0f,
				12.0f, 1.0f,
				17.0f, 3.0f,
				19.0f, 2.0f,
				25.0f, 0.0f,
				26.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				6500.0f, -1000.0f), Color.GREEN, Color.WHITE);
		bike = new Bike(game, new Vector(-5.0f, 10.0f)); 
		flag = new Finish(game, new Vector(55.0f, -4.0f));	
		game.addActor(terrain);
		game.addActor(bike);
		game.addActor(flag);
	}

	
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bike getBike() {
		// TODO Auto-generated method stub
		return bike;
	}

	@Override
	public Finish getFlag() {
		// TODO Auto-generated method stub
		return flag;
	}

	@Override
	public Crate getCrate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Terrain getTerrain() {
		// TODO Auto-generated method stub
		return terrain;
	}

	@Override
	public Bascule getBascule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pendule getPendule() {
		// TODO Auto-generated method stub
		return null;
	}


}
