import greenfoot.*;

public class FlappyWorld extends World {
    private int coinTimer = 0;
    private GreenfootImage bgHell = new GreenfootImage("HELL.gif");
    private GreenfootSound backsoundDay = new GreenfootSound("Day.mp3");
    private GreenfootSound backsoundNight = new GreenfootSound("Night.mp3");
    private int level;
    public Pipe Pipa1 = new Pipe();
    public Pipe Pipa2 = new Pipe();
    public Player Bat = new Player(-1.3);

    public FlappyWorld() {    
        super(600, 400, 1);
        initializeWorld();
    }

    public void initializeWorld() {
        addObject(Bat, 100, 300);
        addObject(Pipa1, 300, 175);
        addObject(Pipa2, 600, 175);
        Pipa1.setSpeed(1);
        Pipa2.setSpeed(1);
        Bat.setSpeed(1);
        level = 1;
        if (getObjects(Score.class).isEmpty()) {
            addObject(new Score(), 300, 100);
        }
    }

    public void act() {
        checkScore();
        backsoundEfect();
        checkGameOver();
        spawnCoins();
    }
    
    public void backsoundEfect(){
        if (Player.isAlive() && level == 1){
            backsoundDay.play();
        }else if (Player.isAlive() && level == 2){
            backsoundNight.play();
        }else{
            backsoundDay.stop();
            backsoundNight.stop();
        }
    }
    
    public void checkScore() {
        if (Score.getScore() >= 15) {
            level = 2;
            Pipa1.setSpeed(2);
            Pipa2.setSpeed(2);
            Bat.setSpeed(2);
            setBackground(bgHell);
            backsoundDay.stop();
        }
    }

    public void checkGameOver() {
        if (isGameOver()) {
            gameOver();
        }
    }

    public void gameOver() {
        GameOver gameOver = new GameOver();
        addObject(gameOver, getWidth() / 2, getHeight() / 2 - 50);
    }

    public boolean isGameOver() {
        return false;  // Gantilah dengan logika game over yang sesuai
    }

    public void spawnCoins() {
        coinTimer++;
        if (coinTimer >= 500) {
            if(getObjects(Coin.class).isEmpty()){
                addObject(new Coin(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
            }
            coinTimer = 0;
        }
    }
    
    public int getLevel(){
        return level;
    }
}
