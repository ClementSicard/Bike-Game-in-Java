package ch.epfl.cs107.play.game.actor.Levels;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.Actor;
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

public class BasicBikeGameLevel extends Level implements Actor {
	
	protected Bike bike;
	protected Finish flag;
	protected Terrain terrain;
	protected ActorGame game;
	protected Bascule bascule; 
	protected Pendule pendule;
	protected Nuage nuage;
	protected Coin coin1, coin2, coin3;
	protected Checkpoint checkpoint;
	protected Tremplin tremplin;
	protected Pic pic;
	
	public BasicBikeGameLevel(ActorGame game) {
		this.game = game;
	}
	
	public void createAllActors() {
		terrain = new Terrain(game, new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0.0f, 0.0f,
				3.0f, -1.0f,
				8.0f, -1.0f,
				15.0f, -3.0f,
				16.0f, -3.0f,
				25.0f, 0.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, -4.0f,
				65.0f, 1.0f,
				6500.0f, -1000.0f), Color.CYAN, Color.WHITE);
		bike = new Bike(game, new Vector(-5.0f, 10.0f), "wheel.png"); 
		flag = new Finish(game, new Vector(70.0f, 0.4f), "flag.blue.png");
		pic = new Pic(game, new Vector(15.0f, -3.0f));
		tremplin = new Tremplin(game, new Vector(62.0f, -4.0f));
		bascule = new Bascule(game, false, new Vector(2.f, -1.2f), 0.3f);
		pendule = new Pendule(game, new Vector(8.0f , 7.0f));
		coin1 = new Coin(game, true, new Vector(-4.0f, 2.0f), 0.7f, 0.7f);
		coin2 = new Coin(game, true, new Vector(18.0f, -1.0f), 0.7f, 0.7f);
		coin3 = new Coin(game, true, new Vector(35.0f, -4.0f), 0.7f, 0.7f);
		checkpoint = new Checkpoint(game, true, new Vector(25.0f, 0.0f), "flag.red.png");
		nuage = new Nuage(game, new Vector(0.0f, 5.0f), 1.0f, 2.0f);
		//The actors are automatically added to the game when instanced : much easier than declaring all of them sequentially
	}

	public void draw(Canvas canvas) {}
	
	public Bike getBike() {
		return bike;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public Finish getFlag() {
		return flag;
	}

	public Crate getCrate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bascule getBascule() {
		// TODO Auto-generated method stub
		return bascule;
	}

	@Override
	public Pendule getPendule() {
		// TODO Auto-generated method stub
		return pendule;
	}

	@Override
	public Emitter getEmitter() {
//		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	@Override
	public Nuage getNuage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coin getCoin1() {
		// TODO Auto-generated method stub
		return coin1;
	}

	@Override
	public Coin getCoin2() {
		// TODO Auto-generated method stub
		return coin2;
	}

	@Override
	public Coin getCoin3() {
		// TODO Auto-generated method stub
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