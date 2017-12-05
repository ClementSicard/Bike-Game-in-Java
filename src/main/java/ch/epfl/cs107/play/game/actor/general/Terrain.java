package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ShapeGraphics graphics;
	
	
	public Terrain(ActorGame game, Color bord, Color fond) {
		super(game, true);
		partBuilder = getEntity().createPartBuilder();
		Polyline polyline = new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0.0f, 0.0f,
				3.0f, 1.0f,
				8.0f, 1.0f,
				15.0f, 3.0f,
				16.0f, 3.0f,
				25.0f, 0.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				6500.0f, -1000.0f);
		partBuilder.setFriction(5.0f);
		partBuilder.setShape(polyline);
		partBuilder.build();
		graphics = new ShapeGraphics(polyline, fond, bord, 0.1f, 1.0f, 0.0f);
		graphics.setParent(this);
		
		getOwner().addActor(this);
	}


	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);
		
	}

}
