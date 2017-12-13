package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Nuage extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	
	
	public Nuage(ActorGame game, Vector position, float height, float width) {
		super(game, true, position);
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon(
                new Vector(0.0f, 0.0f), 
                new Vector(width, 0.0f), 
                new Vector(width, height),
                new Vector(0.0f, height));
		partBuilder.setShape(polygon);
		partBuilder.setGhost(true); //The cloud is set ghost : it won't affect the bike's trajectory if there is a contact between them (a cloud is a passive Actor)
		graphics = new ImageGraphics("cloud.png", width, height);
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
