package ch.epfl.cs107.play.game.actor.general;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Particle implements Graphics, Positionable {
	
		private Vector position;
		private Vector velocity;
		private Vector acceleration;
		
		private float angularPosition;
		private float angularVelocity;
		private float angularAcceleration;
		
		
		public void draw(Canvas canvas) {}
		
		public abstract Particle copy();
		
		@Override
		public Transform getTransform() {
			return
					Transform.I.rotated(angularPosition).translated(position);
		}
		
		public void update(float deltaTime) {
			velocity = velocity.add(acceleration.mul(deltaTime)) ;
			position = position.add(velocity.mul(deltaTime)) ;
			angularVelocity += angularAcceleration * deltaTime ;
			angularPosition += angularVelocity * deltaTime ;	
		}
		
		@Override
		public Vector getVelocity() {
			return velocity;
		}
		
		public Vector getPosition() {
			return position;
		}
		
		public Vector getAcceleration() {
			return acceleration;
		}
		
		public void setVelocity(Vector velocity) {
			this.velocity = velocity;
		}
		
		public void setAcceleration(Vector acceleration) {
			this.acceleration = acceleration;
		}
		
		public void setPosition(Vector position) {
			this.position = position;
		}
		
		public void setAngularVelocity(float angularVelocity) {
			this.angularVelocity = angularVelocity;
		}
		
		public void setAngularPosition(float angularPosition) {
			this.angularPosition = angularPosition;
		}
		
		public void setAngularAcceleration(float angularAcceleration) {
			this.angularAcceleration = angularAcceleration;
		}
}
