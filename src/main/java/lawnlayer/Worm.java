package lawnlayer;

import java.lang.Math;

import processing.core.PImage;

public class Worm extends Dad{
        protected int xVel;  // Speed of the shape
        protected int yVel;  // Speed of the shape

        protected int xdirection;  // Left or Right
        protected int ydirection;

    public Worm(){
        super(0,0);
        //int r = (int) (Math.random() * (upper - lower)) + lower;

        this.x  = (int) (Math.random() * (1260 - 40)) + 40;
        this.y = (int) (Math.random() * (680 - 120)) + 120;

        xVel = 3;  // Speed of the shape
        yVel = 2;  // Speed of the shape

        xdirection = 1;  // Left or Right
        ydirection = 1;  // Top to Bottom     



    }
    public void tick(){
        // Update the position of the shape
        this.x = this.x + ( this.xVel * this.xdirection );
        this.y = this.y + ( this.yVel * this.ydirection );
        
        // Test to see if the shape exceeds the boundaries of the screen
        // If it does, reverse its direction by multiplying by -1

        
        if (this.x > App.WIDTH-(App.SPRITESIZE*2) || this.x < App.SPRITESIZE) {
            this.xdirection *= -1;
        }
        if (this.y > App.HEIGHT-(App.SPRITESIZE*2) || this.y < App.TOPBAR+App.SPRITESIZE) {
            this.ydirection *= -1;
  }

    }

    
}
