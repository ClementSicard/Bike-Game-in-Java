package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor {
	
	private PartBuilder partBuilder;
	private Wheel leftWheel, rightWheel;
	//ShapeGraphics used to draw the biker
	private ShapeGraphics leftHandGraphics, rightHandGraphics, armGraphics, headGraphics, rearGraphics, leftLegGraphics, rightLegGraphics, couisseGraphics, cadreGraphics;
	private boolean orientation = true, hit, armsUp = false;
	private final static float MAX_WHEEL_SPEED = 10.0f;
	private ContactListener bikeListener;
	
	public Bike(ActorGame game, Vector position, String image) { //We consider that the bike is necessarily mobile
		//String parameter allows to change the wheels representation depending on the levels
		super(game, false, position);
		partBuilder = getEntity().createPartBuilder();
		Polygon polygon = new Polygon(
				0.0f, 0.5f,
				0.5f, 1.0f,
				0.0f, 2.0f,
				-0.5f, 1.0f);
		partBuilder.setShape(polygon);
//		partBuilder.setGhost(true); //This parameter is no longer used
		partBuilder.setGhost(false);
		partBuilder.build();
		leftWheel = new Wheel(getOwner(), false, position.add(-1.0f, 0.f), 0.5f, image);
		rightWheel = new Wheel(getOwner(), false, position.add(1.0f, 0.f), 0.5f, image);
		leftWheel.attach(getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f));
		rightWheel.attach(getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
		getOwner().addActor(leftWheel);
		getOwner().addActor(rightWheel);
		getOwner().addActor(this);
		this.getEntity().addContactListener(listener);
	}

	@Override
	public void draw(Canvas canvas) {
		
		//All of these objects constitute parts of the biker
		Circle head = new Circle(0.2f, getShoulderLocation().add(new Vector(0.07f, 0.3f)));
		headGraphics = new ShapeGraphics(head, Color.WHITE, Color.WHITE, 0.02f);
		headGraphics.setParent(this);
	
		Polyline rear = new Polyline(getShoulderLocation(), getRearLocation());
		rearGraphics = new ShapeGraphics(rear, Color.WHITE, Color.WHITE, 0.1f);
		rearGraphics.setParent(this);
		
		Polyline couisse = new Polyline(getRearLocation(), getKneeLocation());
		couisseGraphics = new ShapeGraphics(couisse, Color.WHITE, Color.WHITE, 0.1f);
		couisseGraphics.setParent(this);
		
		Polyline leftLeg = new Polyline(getKneeLocation(), getLeftFoot());
		leftLegGraphics = new ShapeGraphics(leftLeg, Color.WHITE, Color.WHITE, 0.1f);
		leftLegGraphics.setParent(this);
		
		Polyline rightLeg = new Polyline(getKneeLocation(), getRightfoot());
		rightLegGraphics = new ShapeGraphics(rightLeg, Color.WHITE, Color.WHITE, 0.1f);
		rightLegGraphics.setParent(this);
		
		if (armsUp)
		{	
			if(orientation) {
				Polyline leftHandUp = new Polyline(getShoulderLocation(), new Vector(0.8f, 2.0f));
				leftHandGraphics = new ShapeGraphics(leftHandUp, Color.WHITE, Color.WHITE, 0.1f);
				Polyline rightHandUp = new Polyline(getShoulderLocation(), new Vector(0.8f, 2.0f));
				rightHandGraphics = new ShapeGraphics(rightHandUp, Color.WHITE, Color.WHITE, 0.1f);
				leftHandGraphics.setParent(this);
				rightHandGraphics.setParent(this);
				leftHandGraphics.draw(canvas);
				rightHandGraphics.draw(canvas);
			}
			else
			{
				Polyline leftHandUp = new Polyline(getShoulderLocation(), new Vector(-0.8f, 2.0f));
				leftHandGraphics = new ShapeGraphics(leftHandUp, Color.WHITE, Color.WHITE, 0.1f);
				Polyline rightHandUp = new Polyline(getShoulderLocation(), new Vector(-0.8f, 2.0f));
				rightHandGraphics = new ShapeGraphics(rightHandUp, Color.WHITE, Color.WHITE, 0.1f);
				leftHandGraphics.setParent(this);
				rightHandGraphics.setParent(this);
				leftHandGraphics.draw(canvas);
				rightHandGraphics.draw(canvas);
			}
		}
		else
		{
			Polyline arm = new Polyline(getShoulderLocation(), getHandLocation());
			armGraphics = new ShapeGraphics(arm, Color.WHITE, Color.WHITE, 0.1f);
			armGraphics.setParent(this);
			armGraphics.draw(canvas);	
		}	


		
		//Draws the biker and the wheels
		leftWheel.draw(canvas);
		rightWheel.draw(canvas);
		headGraphics.draw(canvas);
		rearGraphics.draw(canvas);
		leftLegGraphics.draw(canvas);
		rightLegGraphics.draw(canvas);
		couisseGraphics.draw(canvas);
	}

	
	public void destroy() {
		getEntity().destroy();
		getOwner().removeActor(this);
		leftWheel.detach();
		rightWheel.detach();
//		getOwner().removeActor(leftWheel);     //We prefer detaching the wheels rather than simply deleting them for graphical purposes
//		getOwner().removeActor(rightWheel);
	}
	
	
	//Following methods are used to draw the biker depending on certain parameters (for instance the orientation, or if the game is finished)
	public Wheel getLeftWheel() {
		return leftWheel;
	}
	
	public Wheel getRightWheel() {
		return rightWheel;
	}
	
	private Vector getHandLocation() {	
		if (orientation)
		{
			return new Vector(0.5f, 1.0f);
		}
		else
		{
			return new Vector(-0.5f, 1.0f); 
		}
	}
	
	private Vector getShoulderLocation() {
		if (orientation)
		{
			return new Vector(-0.1f, 1.4f);
		}
		else
		{
			return new Vector(0.1f, 1.4f);
		}
	}
	
	private Vector getRearLocation() {
		if (orientation) 
		{
			return new Vector(-0.5f, 0.8f);
		}
		else
		{
			return new Vector(0.5f, 0.8f);
		}
	}
	
	private Vector getLeftFoot() {
		float cosAngle = (float) Math.cos(getAngularPosition());
		float sinAngle = (float) Math.sin(getAngularPosition());
		
		if (orientation)
		{
			return new Vector((-cosAngle + getKneeLocation().getX()+0.25f)/5, (-sinAngle + getKneeLocation().getY()-0.5f)/5);
		}
		else
		{
			return new Vector(-(-cosAngle + getKneeLocation().getX()+0.25f)/5,- (-sinAngle + getKneeLocation().getY()-0.5f)/5);
		}
	}
	
	private Vector getRightfoot() {
		float cosAngle = (float) Math.cos(getAngularPosition() + Math.PI); //Dephasing the left foot from the right one
		float sinAngle = (float) Math.sin(getAngularPosition() + Math.PI);
		
		if (orientation)
		{
			return new Vector((-cosAngle + getKneeLocation().getX()+0.25f)/5, (-sinAngle + getKneeLocation().getY()-0.5f)/5);
		}
		else
		{
			return new Vector(-(-cosAngle + getKneeLocation().getX()+0.25f)/5,- (-sinAngle + getKneeLocation().getY()-0.5f)/5);
		}
	}
		
	public Entity getBike() {
		return this.getEntity();
	}

	public boolean getOrientation() {
		return orientation;
	}

	public void goRight() {
		if (leftWheel.getSpeed() > -MAX_WHEEL_SPEED)
		{
			getLeftWheel().power(-17.0f);
			getRightWheel().relax();
		}
	}
	
	private Vector getKneeLocation() {
		
		return new Vector(0.f, 0.5f);
	}

	public void goLeft() {
		if (rightWheel.getSpeed() < MAX_WHEEL_SPEED)
		{
			getRightWheel().power(17.f);
			getLeftWheel().relax();
		}	
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public void setHit(boolean a) {
		hit = a;
	}

	public void changeOrientation() {
		orientation = !orientation;
	}
	
	public ContactListener getListener() {
		return bikeListener;
	}
	
	public void setArmsUp(boolean a) {
		armsUp = a;
	}
	
	public double getAngularPosition() {
		if (orientation) 
		{
			return leftWheel.getAngularPostion();
		}
		else
		{
			return rightWheel.getAngularPostion();
		}
	}

	ContactListener listener = new ContactListener () { //Anonymous class to get contact the bike has with other Entities

		
		@Override
		public void beginContact(Contact contact) {
			if (contact.getOther().isGhost())
			{
				return;
			}
			//If in contact with the wheels, nothing happens (it prevents the biker from dying from an unwanted contact with the wheels)
			if (contact.getOther().getClass().equals(leftWheel.getClass()))
			{
				return;
			}
			if (contact.getOther().getClass().equals(getOwner().getFinish().getClass()))
			{
				
			}
			hit = true ;
		}
		
			
		@Override
		public void endContact(Contact contact) {
//			//Nothing needed here
		}
	};
}

