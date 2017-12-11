package ch.epfl.cs107.play.game.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.general.Bascule;
import ch.epfl.cs107.play.game.actor.general.Bike;
import ch.epfl.cs107.play.game.actor.general.Checkpoint;
import ch.epfl.cs107.play.game.actor.general.Collectable;
import ch.epfl.cs107.play.game.actor.general.Crate;
import ch.epfl.cs107.play.game.actor.general.Emitter;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Nuage;
import ch.epfl.cs107.play.game.actor.general.Pendule;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Node;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Level extends Node implements Actor {
	
	private TextGraphics graphics;
	
	public Level() {}
	
	public abstract void createAllActors();
	
	public void createText(Canvas canvas, float alpha) {
		graphics = new TextGraphics("NEXT LEVEL!", 0.27f, Color.WHITE, Color.BLACK, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f, 100.0f);
    	graphics.setParent(canvas);
    	graphics.setRelativeTransform(Transform.I.translated(0.f, -1.0f));
    	graphics.setAlpha(alpha);
    	graphics.draw(canvas);
	}

	public abstract Bike getBike();
	
	public abstract Finish getFlag();
	
	public abstract Crate getCrate();
	
	public abstract Terrain getTerrain();
	
	public abstract Bascule getBascule();
	
	public abstract Pendule getPendule();

	public abstract Emitter getEmitter();
	
	public abstract Checkpoint getCheckpoint();
	
	public abstract Collectable getCollectable();
	
	public abstract Nuage getNuage();
}