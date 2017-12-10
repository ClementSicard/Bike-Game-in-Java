package ch.epfl.cs107.play.game.actor.general;


import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Plank extends GameEntity implements Actor {

	
	private PartBuilder plankBuilder;
	
	private ImageGraphics plankGraphics;
	
	
	public Plank (ActorGame game, boolean fixed, Vector position, float friction, float height, float width) {
		super(game, fixed, position);

			plankBuilder = getEntity().createPartBuilder();
			Polygon plank = new Polygon(
	                new Vector(0.0f, 0.0f), 
	                new Vector(width, 0.0f), 
	                new Vector(width, height),
	                new Vector(0.0f, height));
		 	plankBuilder.setShape(plank);
		 	plankBuilder.setFriction(0.5f);
		 	plankBuilder.build();
		 	plankGraphics = new ImageGraphics("wood.3.png", width, height);
		 	plankGraphics.setDepth(0.5f);
		 	plankGraphics.setParent(this);
		 	getOwner().addActor(this);
		 	
		 
		}
		
		public void setGraphicsParameters(ImageGraphics plankGraphics, float alpha, float depth) {
			plankGraphics.setAlpha(alpha);
			plankGraphics.setDepth(depth);
		}

		public void draw(Canvas canvas) {
			plankGraphics.draw(canvas);
		}
		
		public Plank getPlank() {
			return this;
		}
		
		public void destroy() {
			getEntity().destroy();
		}
}
