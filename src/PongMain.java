import processing.core.PApplet;
import processing.core.PFont;
import processing.net.*;
/**
 * Created by Matt on 3/10/2015.
 */
public class PongMain extends PApplet
{
    Ball ball1;
    Client client;
    int inputFromServer;
    PFont font;

    boolean inMainMenu = true;
    boolean playing = false;

    public static void main(String[] args)
    {
        PApplet.main(new String[]{"PongMain"});
    }

    public void setup()
    {
        size(1280, 720, P2D);
        noStroke();
        rectMode(CENTER);
        ball1 = new Ball(this);
        client = new Client(this, "127.0.0.1", 5204);
        font = createFont("../data/arialbd.ttf", 80);
    }

    public void draw()
    {
        if (inMainMenu)
            mainMenu();
        if (playing)
            play();
    }

    public void mainMenu()
    {
        background(50);
        textAlign(CENTER);
        textSize(80);
        fill(240);
        text("nPong!", width/2, height/2.6f);
        fill(120);
        rect(width*.35f, height/2, width*.25f, width*.05f);
        rect(width*.65f, height/2, width*.25f, width*.05f);
        textSize(30);
        fill(240);
        text("singleplayer", width*.35f, height/1.95f);
        text("multiplayer", width*.65f, height/1.95f);

    }
    public void play()
    {
        background(50);
        fill(255, 204);

        if (client.available() > 0)
        {
            inputFromServer = (int)client.readChar();
        }

        rect(30*width/640, mouseY, 20*width/640, 100*width/640);
        rect(width - 30*width/640, inputFromServer*width/640, 20*width/640, 100*width/640);
        ball1.display();
        ball1.move();
        println(mouseX);
        //check if the ball hit the player paddle
        if (ball1.x-ball1.w/2 <= 40*width/640 && (ball1.y < mouseY + 50*width/640 && ball1.y > mouseY - 50*width/640))
        {
            ball1.velocity_x = -ball1.velocity_x;
        }
        //check if the ball hit the computer paddle
        if (ball1.x+ball1.w/2 >= width - 40*width/640 && ball1.y < inputFromServer && ball1.y > inputFromServer - 50*width/640)
        {
            ball1.velocity_x = -ball1.velocity_x;
        }
    }

}