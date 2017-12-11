package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;


abstract public class Trigger {
	
	private EntityBuilder entityBuilder;
	private Entity entity;
	private ActorGame game;
	private BasicContactListener finishListener;
	private PartBuilder partBuilder;

	
	public Trigger(ActorGame game, boolean fixed, Vector position, String name, float width, float height) {

		try {
			if (game == null || position == null) { throw new NullPointerException(); 
		}
		this.game = game;
		entityBuilder = game.createEntityBuilder();

		entityBuilder.setFixed(fixed);
		entityBuilder.setPosition(position);
        entity = entityBuilder.build();
        
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon ( 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 100.0f, 0.0f, 100.0f );
		
		partBuilder.setShape(polygon);
		partBuilder.setGhost(true);
		partBuilder.build(); 
		
		}
		
		catch (NullPointerException z) {
			System.out.println("Parameters are null");
		}
		
		//finishGraphics = new ShapeGraphics(polygon, Color.WHITE, Color.BLUE, 1f);

		// image.setParent(this);
		
		// getOwner().addActor(this);
		
		finishListener = new BasicContactListener();
		
		this.getEntity().addContactListener(finishListener);
        
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
	protected ActorGame getOwner() {
		return game;
	}
	
	public BasicContactListener getListener() {
		return finishListener;
	}
	
	public Transform getTransform() {
		return getEntity().getTransform();  
	}
	
	public Vector getVelocity() {
		return getEntity().getVelocity();
	}
	
	
	
	public void destroy() {
		entity.destroy();
			
	}
	
	
} 