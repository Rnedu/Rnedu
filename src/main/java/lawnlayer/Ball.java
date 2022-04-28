package lawnlayer;


//import java.time.chrono.HijrahEra;

public class Ball extends Dad{

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    private int timer;
    private int speed;

    public Ball(){
        super(0, App.TOPBAR);
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.timer = 0;
        this.speed = 20;

    }

    public void tick(){
        this.timer++;

        if (this.timer % 10 == 0){

            if (this.moveLeft) 
                this.x -= speed;
            else if (this.moveRight)
                this.x += speed;
    
            if (this.moveUp)
                this.y -= speed;
            else if (this.moveDown)
                this.y += speed;
                
        }
        if (this.y < App.TOPBAR){
            this.y = App.TOPBAR;
            this.moveUp = false;
        }
        if (this.y > App.HEIGHT){
            this.y = App.HEIGHT - App.SPRITESIZE;
            this.moveDown = false;
        }
        if (this.x > App.WIDTH){
            this.x = App.WIDTH-App.SPRITESIZE;
            this.moveRight = false;
        }
        if (this.x < 0){
            this.x = 0;
            this.moveLeft = false;
        }
            
    }
     
     // Called in App when the left key is pressed.
  
    public void pressLeft() {
        if (this.moveRight== true)
            this.moveLeft = false;
        else {
            this.moveLeft = true;
            this.moveDown = false;
            this.moveUp = false;
        }

    }

    // Called in App when the right key is pressed.
    
    public void pressRight() {
        if (this.moveLeft== true)
            this.moveRight = false;
        else {
            this.moveRight = true;
            this.moveDown = false;
            this.moveUp = false;
        }

    }

    public void pressUp() {
        if (this.moveDown== true)
            this.moveUp = false;
        else {
            this.moveUp = true;
            this.moveRight = false;
            this.moveLeft = false;
        }

    }

    
    public void pressDown() {
        if (this.moveUp== true)
            this.moveDown = false;
        else {
            this.moveDown = true;
            this.moveRight = false;
            this.moveLeft = false;
        }
    }

    }
    

