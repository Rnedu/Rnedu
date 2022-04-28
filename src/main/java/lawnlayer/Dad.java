package lawnlayer;
import processing.core.PImage;
import processing.core.PApplet;

public abstract class Dad{
    
     // The Shape's x & y-coordinate.
    protected int x;
    protected int y;

    // The Shape's sprite.
    public PImage sprite;

    //The Shape's x & y velocity
    protected int xVel;
    protected int yVel;

    //The Shape's x & y Direction (USED FOR BOUNCING)
    protected int xdirection;  // Left or Right
    protected int ydirection;

    public Dad(int x, int y){
        this.x = x;
        this.y = y;

        this.xVel = 0;
        this.yVel = 0;

    }
    
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    public abstract void tick();
        //Base level condition to make sure objects dont go above or below box
 
    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }

    
}
