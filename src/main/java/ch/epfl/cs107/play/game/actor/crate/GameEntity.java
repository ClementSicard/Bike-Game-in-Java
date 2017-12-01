package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Vector;

abstract class GameEntity {
	
	private Entity entity;
	private ActorGame game;
	private EntityBuilder builder;
	
	GameEntity(ActorGame game, boolean fixed, Vector position) {
		this.game = game;
		builder = game.createEntityBuilder();
		builder.setPosition(position);
		builder.setFixed(fixed);
		entity = builder.build();	
	}
	
	GameEntity(ActorGame game, boolean fixed) {
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
}
