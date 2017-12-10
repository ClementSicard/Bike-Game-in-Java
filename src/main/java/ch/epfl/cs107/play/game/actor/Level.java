package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.actor.general.Bascule;
import ch.epfl.cs107.play.game.actor.general.Bike;
import ch.epfl.cs107.play.game.actor.general.Crate;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Pendule;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Node;

public abstract class Level extends Node implements Actor {
	public Level() {}
	
	public abstract void createAllActors();
	
	public void createText() {}

	public abstract Bike getBike();
	
	public abstract Finish getFlag();
	
	public abstract Crate getCrate();
	
	public abstract Terrain getTerrain();
	
	public abstract Bascule getBascule();
	
	public abstract Pendule getPendule();
}