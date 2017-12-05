package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public abstract class GameEntity {
	
	private Entity entity;
	private ActorGame game;
	private EntityBuilder builder;
	
	public GameEntity(ActorGame game, boolean fixed, Vector position) {
		this.game = game;
		builder = this.game.createEntityBuilder();
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
	
	public Window getWindow() {
		return game.getWindow();
	}

}
