package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import java.awt.Color;
import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ContactGame implements Game {
	
        private Window window;
        private World world;
        private Entity block, ball;
        private ImageGraphics blockGraphics;
        private ShapeGraphics ballGraphics;
        private BasicContactListener contactListener;

        
        @Override
        public boolean begin(Window window, FileSystem fileSystem) {
                this.window = window;
                world = new World();
                world.setGravity(new Vector(0.0f, -9.81f));
        
                
                
                EntityBuilder ballEntityBuilder = world.createEntityBuilder();
                ballEntityBuilder.setFixed(false);
                ballEntityBuilder.setPosition(new Vector(0.0f, 2.0f));
                ball = ballEntityBuilder.build();
                
                PartBuilder ballPartBuilder = ball.createPartBuilder();
                float ballRadius = 0.5f;
                Circle circle = new Circle(ballRadius);
                ballPartBuilder.setShape(circle);
                ballPartBuilder.setFriction(0.5f);
                ballPartBuilder.build();
                ballGraphics = new ShapeGraphics(circle, Color.BLUE, Color.BLUE, .1f, 1, 0);
                ballGraphics.setAlpha(1.0f);
                ballGraphics.setDepth(0.0f);
                ballGraphics.setParent(ball);
        
                
                
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
                
                contactListener = new BasicContactListener();
                ball.addContactListener(contactListener);
                          
            return true;
        }

        // This event is called at each frame
        @Override
        public void update(float deltaTime) {
                
                if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) 
                { 
                        ball.applyAngularForce(5.0f);
                } else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) 
                { 
                        ball.applyAngularForce(-5.0f);
                }
                
                
                world.update(deltaTime);

                window.setRelativeTransform(Transform.I.scaled(10.0f));

                blockGraphics.draw(window);
                
                int numberOfCollisions = contactListener.getEntities().size();
                
                if (numberOfCollisions > 0)
                {
                	ballGraphics.setFillColor(Color.RED);
                	ballGraphics.setOutlineColor(Color.RED);
                }
                        
                ballGraphics.draw(window);
                
                
        }

        // This event is raised after game ends, to release additional resources
        @Override
        public void end() {
                // Empty on purpose, no cleanup required yet
        }
}
