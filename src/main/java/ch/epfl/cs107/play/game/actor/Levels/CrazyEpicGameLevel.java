package ch.epfl.cs107.play.game.actor.Levels;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Level;
import ch.epfl.cs107.play.game.actor.general.Bascule;
import ch.epfl.cs107.play.game.actor.general.Bike;
import ch.epfl.cs107.play.game.actor.general.Checkpoint;
import ch.epfl.cs107.play.game.actor.general.Coin;
import ch.epfl.cs107.play.game.actor.general.Crate;
import ch.epfl.cs107.play.game.actor.general.Emitter;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Nuage;
import ch.epfl.cs107.play.game.actor.general.Pendule;
import ch.epfl.cs107.play.game.actor.general.Pic;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Tremplin;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class CrazyEpicGameLevel extends Level {
	
	protected Bike bike;
	protected Finish flag;
	protected Terrain terrain;
	protected ActorGame game;
	protected Pendule pendule;
	protected Coin coin1, coin2, coin3;
	protected Pic pic;
	protected Tremplin tremplin;
	protected Checkpoint checkpoint;
	protected Nuage nuage;
	
	public CrazyEpicGameLevel(ActorGame game) {
		this.game = game;
	}
	
	public void createAllActors() {
		terrain = new Terrain(game, new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0.0f, 0.0f,
				10.0f, -2.0f,
				12.0f, -3.0f,
				17.0f, -3.0f,
				19.0f, -2.0f,
				25.0f, -8.0f,
				37.0f, -10.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				6500.0f, -1000.0f), Color.GREEN, Color.WHITE);
		bike = new Bike(game, new Vector(-5.0f, 10.0f), "wheel2.png"); 
		flag = new Finish(game, new Vector(65.0f, 0.0f), "flag.green.png");	
		pendule = new Pendule(game, new Vector(25.0f, -3.0f));
		coin1 = new Coin(game, true, new Vector(-4.0f, 2.0f), 0.7f, 0.7f);
		coin2 = new Coin(game, true, new Vector(18.0f, -1.0f), 0.7f, 0.7f);
		coin3 = new Coin(game, true, new Vector(35.0f, -4.0f), 0.7f, 0.7f);
		pic = new Pic(game, new Vector(13.0f, -3.0f));
		tremplin = new Tremplin(game, new Vector(18.0f, -2.0f));
		nuage = new Nuage(game, new Vector(0.0f, 4.0f), 1.0f, 2.0f);
		checkpoint = new Checkpoint(game, true, new Vector(30.0f, -9.0f), "flag.green.png");
		
		//The actors are automatically added to the game when instanced : much easier than declaring all of them sequentially
	}

	
	public void draw(Canvas canvas) {}

	@Override
	public Bike getBike() {
		
		return bike;
	}

	@Override
	public Finish getFlag() {
		
		return flag;
	}

	@Override
	public Crate getCrate() {
		
		return null;
	}

	@Override
	public Terrain getTerrain() {
		
		return terrain;
	}

	@Override
	public Bascule getBascule() {
		
		return null;
	}

	@Override
	public Pendule getPendule() {
		
		return null;
	}

	@Override
	public Emitter getEmitter() {
		return null;
	}

	@Override
	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	
	public Coin getCoin1() {
		return coin1;
	}


	@Override
	public Nuage getNuage() {
		return nuage;
	}

	@Override
	public Coin getCoin2() {
		return coin2;
	}

	@Override
	public Coin getCoin3() {
		return coin3;
	}

	@Override
	public Pic getPic() {
		// TODO Auto-generated method stub
		return pic;
	}

	@Override
	public Tremplin getTremplin() {
		// TODO Auto-generated method stub
		return tremplin;
	}
}
