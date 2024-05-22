import greenfoot.*;

public class Player extends Actor {
    private boolean oldTouchingPipe = false;
    private static boolean dead;
    private GreenfootImage[] images;
    private int currentFrame;
    private int animationSpeed;
    private int animationCounter;
    private int speed;

    public Player(double yVel) {
        images = new GreenfootImage[6];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("Blue_Flying " + (i + 1) + ".png");
            images[i].scale(100, 80);
        }
        setImage(images[0]); // Set initial image
        currentFrame = 0;
        animationSpeed = 5; // Adjust animation speed as needed
        dead = false;
    }

    public Player() {
        this(0);
    }

    public void act() {
        animate();
        movement();
        boolean touchingPipe = false;
        for (Pipe pipe : getWorld().getObjects(Pipe.class)) {
            if (Math.abs(pipe.getX() - getX()) < 40) {
                if (Math.abs(pipe.getY() + 30 - getY()) > 37) {
                    dead = true;
                }
                touchingPipe = true;
            }
        }
        if (!oldTouchingPipe && touchingPipe && !dead) {
            Score.add(1);
        }
        oldTouchingPipe = touchingPipe;

        if (dead) {
            World myWorld = getWorld();
            if (myWorld instanceof FlappyWorld) {
                ((FlappyWorld) myWorld).gameOver();
            }
            myWorld.removeObject(this);
        }
    }

    private void animate() {
        animationCounter++;
        if (animationCounter % animationSpeed == 0) {
            currentFrame = (currentFrame + 1) % images.length;
            setImage(images[currentFrame]);
        }
    }

    public void movement() {
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("right")) {
            x += speed;
        }
        if (Greenfoot.isKeyDown("left")) {
            x -= speed;
        }
        if (Greenfoot.isKeyDown("up")) {
            y -= speed;
        }
        if (Greenfoot.isKeyDown("down")) {
            y += speed;
        }

        setLocation(x, y);
    }

    public static boolean isAlive() {
        return !dead;
    }

    public static boolean isDead() {
        return dead;
    }

    public void setLocation(double x, double y) {
        super.setLocation((int) x, (int) y);
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
