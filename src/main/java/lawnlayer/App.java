package lawnlayer;

import java.io.File;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.A;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.core.PFont;

public class App extends PApplet {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int TOPBAR = 80;

    public static final int FPS = 60;

    public String configPath;
    public JSONObject json;
    public JSONArray levels;
    public int lives;
    public Double goalD;
    public int goal;
    public String level;

    public Ball ball;
    public Worm worm1;
    public Worm worm2;
    public Beetle beetle;
	
	public PImage grass;
    public PImage concrete;
    public PFont font;
    public PFont font2;

    public char[][] coordinates = new char[64][64];


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        json = loadJSONObject(configPath); //reading in the JSON File
        levels = json.getJSONArray("levels"); //
        lives  = json.getInt("lives");
        for (int i = 0 ; i < 1; i++) { //REMEMBER TO MAKE i < levels.size();
    
            JSONObject animal = levels.getJSONObject(i); 
        
            level = animal.getString("outlay");
            goalD = animal.getDouble("goal");
            JSONArray enemies = animal.getJSONArray("enemies");
            for (int j = 0; j< enemies.size(); j++){
                JSONObject enemySpecifications = enemies.getJSONObject(i);
                int enemyType = enemySpecifications.getInt("type");
                String enemySpawn = enemySpecifications.getString("spawn");
            }      
            
        }
        goalD = (goalD * 100);
        goal = goalD.intValue();
        System.out.println(level);

        
        font = createFont("ArialMT", 40);
        font2 = createFont("ArialMT", 20);

        
        // Load images during setup
		this.grass = loadImage("bin/main/lawnlayer/grass.png");
        this.concrete = loadImage("bin/main/lawnlayer/concrete_tile.png");
        this.beetle = new Beetle();
        this.beetle.setSprite(loadImage("bin/main/lawnlayer/beetle.png"));
        
        this.ball = new Ball();
        this.ball.setSprite(loadImage("bin/main/lawnlayer/ball.png"));
        
        this.worm1 = new Worm();
        this.worm1.setSprite(loadImage("bin/main/lawnlayer/worm.png"));
        
        this.worm2 = new Worm();
        this.worm2.setSprite(loadImage("bin/main/lawnlayer/worm.png"));

        File f = new File(level);
        try{
            Scanner scan = new Scanner(f);
            int i = 0;
            while (scan.hasNext()){
                String wholeLine = scan.nextLine();
                for (int j = 0; j<wholeLine.length(); j++){
                    coordinates[i][j] = wholeLine.charAt(j);
                }
                i++;
            }
            scan.close();
        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        
    }
	
    /**
     * Draw all elements in the game by current frame. 
     */
    public void draw() {
        background(100,70,36);
        
        textFont(font);
        text("Lives: " + lives, 200, 40);
        text("%/"+(goal)+"%",1000,40);
        textFont(font2);
        text("Level: ",1150,70);
        
        //SETTING BACKGROUND COLOR USING RGB 
        
        // PLAYER/ENEMY LOGIC
        this.ball.tick();
        this.worm1.tick();
        this.worm2.tick();
        
        for (int i = 0; i<coordinates.length; i++){
            for (int j =0;j<coordinates[i].length;j++){
                char letter = coordinates[j][i];
                if (letter == 'X'){
                    this.image(this.concrete,i*20,(j*20) + App.TOPBAR);
                }
            }
        }

        //CONCRETE DRAWING
        
        
        // PLAYER/ENEMY DRAWING
        this.ball.draw(this);
        this.worm1.draw(this);
        this.worm2.draw(this);
                
    }

    public void keyPressed() {
            // Left: 37
            // Up: 38
            // Right: 39
            // Down: 40
        if (this.keyCode == 37) {
            this.ball.pressLeft();
        } else if (this.keyCode == 38) {
            this.ball.pressUp();
        } else if (this.keyCode == 39) {
            this.ball.pressRight();
        } else if (this.keyCode == 40) {
            this.ball.pressDown();
        }
    }

    public static void main(String[] args) {
        PApplet.main("lawnlayer.App");
    }
}
