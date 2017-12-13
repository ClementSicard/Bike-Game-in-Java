package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public abstract class GameEntity {
	
	private Entity entity;
	private ActorGame game;
	private EntityBuilder builder;
	
	//The class has 2 constructors, depending on if we want to give the entity a particular position (more often used)
	public GameEntity(ActorGame game, boolean fixed, Vector position) {

		try {
			if (game == null || position == null) 
			{
				throw new NullPointerException();
			}
			this.game = game;
			builder = this.game.createEntityBuilder();
			builder.setPosition(position);
			builder.setFixed(fixed);
			entity = builder.build();
		}
		catch (NullPointerException a) {
		System.out.println("Parameters are equal to zero");
		}
	}

	public GameEntity(ActorGame game, boolean fixed) {
		try {
			if (game == null) 
			{
				throw new NullPointerException();
			}
			this.game = game;
			builder = this.game.createEntityBuilder();
			builder.setFixed(fixed);
			 
			entity = builder.build();

		}
		 
		catch (NullPointerException exception)  //Handles the case in which there is an error caused by a NullPointer
		{
			System.out.println("The parameter is equal to zero");
		}
	}

		public void destroy() {
			getEntity().destroy();
		}

		//Following methods give the user everything she/he needs to create a level
		public Entity getEntity() {
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

