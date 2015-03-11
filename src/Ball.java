import processing.core.PApplet;

/**
 * Created by Matt on 3/10/2015.
 */
public class Ball
{

    //data
    float w,h;
    int c;
    float velocity_x, velocity_y;
    int x, y;
    PApplet parent;

    //construct
    Ball(PApplet p)
    {
        parent = p;
        w = 10;
        h = 10;
        c = parent.color(240);
        velocity_x = 2;
        velocity_y = 2;
        //starting place for this ball
        x = parent.width/2;
        y = parent.height/2;
    }

    public void display()
    {
        parent.fill(c);
        //parent.translate(x, y);
        parent. ellipse(x, y, w, h);
    }
    public void move()
    {
        x+=velocity_x;
        y+=velocity_y;

        // bounce off right edge of window
        if (x+(w/2) >= parent.width)
        {
            velocity_x = -velocity_x;
        }

        // bounce off left edge of window
        else if (x-(w/2) <= 0)
        {
            velocity_x = -velocity_x;
        }

        //bounce off top of window
        else if (y-(h/2) <= 0)
        {
            velocity_y = -velocity_y;
        }

        //bounce off bottom of window
        else if (y+(h/2) >= parent.height)
        {
            velocity_y = -velocity_y;
        }

    }
}
