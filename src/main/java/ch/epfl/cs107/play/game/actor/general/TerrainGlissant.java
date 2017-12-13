package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.window.Canvas;

public class TerrainGlissant extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ShapeGraphics graphics;
	
	//The friction can be modified; not the same class as 'Terrain' so that the instanciation is more understandable by the user
	public TerrainGlissant(ActorGame game, Polyline polyline, Color bord, Color fond, float friction) {
		super(game, true);
		partBuilder = getEntity().createPartBuilder();
		partBuilder.setFriction(friction);
		partBuilder.setShape(polyline);
		partBuilder.build();
		graphics = new ShapeGraphics(polyline, fond, bord, 0.2f, 1.0f, 0.0f);
		graphics.setParent(this);
		getOwner().addActor(this);
	}


	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);
	}
	
	public void destroy() {
		getEntity().destroy();
	}

}
