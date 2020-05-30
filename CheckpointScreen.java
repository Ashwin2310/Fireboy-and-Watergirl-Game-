import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.io.*;
import javax.swing.*;

public class CheckpointScreen extends Applet implements MouseListener
{
    AudioClip checkpointClip;
    Image checkpointBackground;
    Button levelOne, levelTwo, levelThree, levelFour, levelFive;

    public void init ()
    {
	this.setLayout (null);
	setFocusable (true);

	checkpointBackground = getImage (getDocumentBase (), "checkpoint.gif");
	checkpointClip = getAudioClip (getDocumentBase (), "music.wav");

	levelOne = new Button ("Level One");
	levelOne.setBounds (180, 120, 250, 40);
	add (levelOne);

	levelTwo = new Button ("Level Two");
	levelTwo.setBounds (180, 190, 250, 40);
	add (levelTwo);

	levelThree = new Button ("Level Three");
	levelThree.setBounds (180, 260, 250, 40);
	add (levelThree);

	levelFour = new Button ("Level Four");
	levelFour.setBounds (180, 330, 250, 40);
	add (levelFour);

	levelFive = new Button ("Level Five");
	levelFive.setBounds (180, 400, 250, 40);
	add (levelFive);

	levelOne.addMouseListener (this);
	levelTwo.addMouseListener (this);
	levelThree.addMouseListener (this);
	levelFour.addMouseListener (this);
	levelFive.addMouseListener (this);
    }


    public void update (Graphics g)
    {
	g.drawImage (checkpointBackground, 0, 0, 900, 600, this);
    }


    public void paint (Graphics g)
    {

	checkpointClip.loop ();
	update (g);


    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }



}
