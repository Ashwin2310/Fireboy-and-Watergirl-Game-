import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.io.*;
import javax.swing.*;

public class LoadingScreen extends Applet implements Runnable
{
    Image loadingScreen, loadingText, Screen;
    AudioClip loadingClip;
    Thread runner;
    Graphics ScreenG, ScreenF;

    public void init ()
    {
	loadingScreen = getImage (getDocumentBase (), "giphy.gif");
	loadingText = getImage (getDocumentBase (), "loadingText.png");

	Screen = createImage (600, 600);
	ScreenG = Screen.getGraphics ();
	ScreenF = Screen.getGraphics ();

    }


    public void start ()
    {
	if (runner == null)
	{
	    runner = new Thread (this);
	    runner.start ();
	}
    }


    public void stop ()
    {
	if (runner != null)
	{
	    runner.stop ();
	    runner = null;
	}
    }


    public void run ()
    {
	while (true)
	{
	    try
	    {
		runner.sleep (50);
	    }
	    catch (Exception e)
	    {
	    }


	    ScreenG.drawImage (loadingScreen, 0, 0, 600, 600, this);
	    ScreenF.drawImage (loadingText, 150, 250, 300, 70, this);

	    repaint ();
	}
    }



    public void update (Graphics g)
    {
	paint (g);
    }


    public void paint (Graphics g)
    {

	g.drawImage (Screen, 0, 0, this);

    }
} //End of class
