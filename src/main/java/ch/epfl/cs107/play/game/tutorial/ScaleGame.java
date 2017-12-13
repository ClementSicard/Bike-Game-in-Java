package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ScaleGame implements Game {

        private Window window;
        private World world;
        private Entity block, ball, plank;
        private ImageGraphics blockGraphics, ballGraphics, plankGraphics;
        
        @Override
        public boolean begin(Window window, FileSystem fileSystem) {
                this.window = window;
                world = new World();
                world.setGravity(new Vector(0.0f, -9.81f)); //Sets the gravity of the game
        
                
                //Creates the ball as an Entity
                EntityBuilder ballEntityBuilder = world.createEntityBuilder();
                ballEntityBuilder.setFixed(false);
                ballEntityBuilder.setPosition(new Vector(0.5f, 4.0f));
                ball = ballEntityBuilder.build();
                
                PartBuilder ballPartBuilder = ball.createPartBuilder();
                float ballRadius = 0.5f;
                Circle circle = new Circle(ballRadius);
                ballPartBuilder.setShape(circle);
                ballPartBuilder.setFriction(0.5f);
                ballPartBuilder.build();
                ballGraphics = new ImageGraphics("explosive.11.png", 2.0f*ballRadius, 2.0f*ballRadius, new Vector (0.5f, 0.5f));
                ballGraphics.setAlpha(1.0f);
                ballGraphics.setDepth(0.0f);
                ballGraphics.setParent(ball);
        
                
                //Creates the block as an Entity
                EntityBuilder blockEntityBuilder = world.createEntityBuilder();
                blockEntityBuilder.setFixed(true);
                blockEntityBuilder.setPosition(new Vector(-5.0f, -1.0f));
                float blockWidth = 10.f;
                float blockHeight = 1.0f;
                block = blockEntityBuilder.build();
                
                PartBuilder blockPartBuilder = block.createPartBuilder();
                Polygon polygon = new Polygon(
                                new Vector(0.0f, 0.0f), 
                                new Vector(blockWidth, 0.0f), 
                                new Vector(blockWidth, blockHeight),
                                new Vector(0.0f, blockHeight));
                
                blockPartBuilder.setShape(polygon);
                blockPartBuilder.setFriction(0.5f);
                blockPartBuilder.build();
                blockGraphics = new ImageGraphics("stone.broken.4.png", 10, 1);
                blockGraphics.setAlpha(1.0f);
                blockGraphics.setDepth(0.0f);
                blockGraphics.setParent(block);
                


                //Creates the plank as an entity
                EntityBuilder plankEntityBuilder = world.createEntityBuilder();
                plankEntityBuilder.setFixed(false);
                plankEntityBuilder.setPosition(new Vector(-2.5f, 0.8f));
                float plankWidth = 5.0f;
                float plankHeight = 0.2f;
                plank = plankEntityBuilder.build();
                
                PartBuilder plankPartBuilder = plank.createPartBuilder();
                Polygon polygon1 = new Polygon(
                                new Vector(0.0f, 0.0f), 
                                new Vector(plankWidth, 0.0f), 
                                new Vector(plankWidth, plankHeight),
                                new Vector(0.0f, plankHeight));
                
                plankPartBuilder.setShape(polygon1);
                plankPartBuilder.setFriction(0.5f);
                plankPartBuilder.build();
                plankGraphics = new ImageGraphics("wood.3.png", 5f, 0.2f);
                plankGraphics.setAlpha(1.0f);
                plankGraphics.setDepth(0.0f);
                plankGraphics.setParent(plank);
                
               //Creates the constraint between the plank and the block
                RevoluteConstraintBuilder revoluteConstraintBuilder =
                		world.createRevoluteConstraintBuilder () ;
                		revoluteConstraintBuilder.setFirstEntity(block) ;
                		revoluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth /2,
                		(blockHeight *7) /4)) ;
                		revoluteConstraintBuilder.setSecondEntity(plank) ;
                		revoluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth /2,
                		plankHeight /2)) ;
                		revoluteConstraintBuilder.setInternalCollision(true) ;
                		revoluteConstraintBuilder.build () ;
               
                return true;
        }

        // This event is called at each frame
        @Override
        public void update(float deltaTime) {
                
                if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
                { 
                	ball.applyAngularForce(5.0f);
                } 
                else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
                { 
                	ball.applyAngularForce(-5.0f);
                }
                
                
                world.update(deltaTime);

                window.setRelativeTransform(Transform.I.scaled(10.0f));

                blockGraphics.draw(window);
                        
                ballGraphics.draw(window);
                
                plankGraphics.draw(window);

        }

        // This event is raised after game ends, to release additional resources
        @Override
        public void end() {
                // Empty on purpose, no cleanup required yet
        }
}
