package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class GameEntity {
	
	private Entity entity;
	private ActorGame game;
	private EntityBuilder builder;
	private ImageGraphics graphics;
	private ShapeGraphics graphics2;
	
	public GameEntity(ActorGame game, boolean fixed, Vector position) {
		this.game = game;
		builder = game.createEntityBuilder();
		builder.setPosition(position);
		builder.setFixed(fixed);
		entity = builder.build();	
	}
	
	public GameEntity(ActorGame game, boolean fixed) {
		this.game = game;
		builder = this.game.createEntityBuilder();
		builder.setFixed(fixed);	
		entity = builder.build();
	}
	
	public void destroy() {
		entity.destroy();
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
	protected ActorGame getOwner() {
		return game;
	}
	
	public Transform getTransform() {
		return getEntity().getTransform(); 
	}

	public Vector getVelocity() { 
		return getEntity().getVelocity();
	}
}
