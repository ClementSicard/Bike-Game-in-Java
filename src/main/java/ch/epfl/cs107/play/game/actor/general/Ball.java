package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

	public class Ball extends GameEntity implements Actor {
	
		
		private PartBuilder ballBuilder;
		
		private ImageGraphics ballGraphics;
		
		
		public Ball (ActorGame game, boolean fixed, Vector position, float friction, float height, float width) {
			super(game, fixed, position);

				ballBuilder = getEntity().createPartBuilder();
				float ballRadius = 0.3f;
				Circle circle = new Circle(ballRadius);
			 	ballBuilder.setShape(circle);
			 	ballBuilder.setFriction(0.5f);
			 	ballBuilder.build();
			 	ballGraphics = new ImageGraphics("explosive.11.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
			 	ballGraphics.setDepth(0.5f);
			 	ballGraphics.setParent(this);	 	
			}
			
			public void setGraphicsParameters(ImageGraphics ballGraphics, float alpha, float depth) {
				ballGraphics.setAlpha(alpha);
				ballGraphics.setDepth(depth);
			}

			@Override
			public void draw(Canvas canvas) {
				ballGraphics.draw(canvas);
			}
			
			public Ball getBall() {
				return this;
			}
			
			public void destroy() {
				getEntity().destroy();
				getOwner().removeActor(this);
			}
		}

