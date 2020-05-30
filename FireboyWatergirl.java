/*CPT Game
Programmed by Mary Grace Wong and Ashwin Thomas on May 24, 2018*/
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.io.*;
import javax.swing.*;

public class FireboyWatergirl extends Applet implements MouseListener
{
    Button startGame, rules;
    Font titleFont;
    Color titleColor, backgroundColor;
    Image poster, fire, water, text1, text2, text3;
    String[] ruleOption = {"Rules", "Obstacles"};
    int ruleChoice;

    AudioClip clip;

    public void init ()
    {
	this.setLayout (null);

	poster = getImage (getDocumentBase (), "fireBoyWaterGirlT.png");
	fire = getImage (getDocumentBase (), "fireGif.gif");
	water = getImage (getDocumentBase (), "waterGif.gif");
	text1 = getImage (getDocumentBase (), "fireFont2.png");
	text2 = getImage (getDocumentBase (), "andFont2.png");
	text3 = getImage (getDocumentBase (), "waterFont2.png");


	clip = getAudioClip (getDocumentBase (), "music.wav");

	titleFont = new Font ("Times New Roman", Font.PLAIN + Font.BOLD, 35);
	titleColor = new Color (128, 229, 255);

	startGame = new Button ("Start Game");
	startGame.setBounds (250, 450, 100, 40);
	add (startGame);

	rules = new Button ("View Rules");
	rules.setBounds (250, 500, 100, 40);
	add (rules);



	startGame.addMouseListener (this);
	rules.addMouseListener (this);
    } //End of init




    public void update (Graphics g)
    {


	g.drawImage (fire, 50, 50, 100, 100, this);
	g.drawImage (water, 470, 50, 90, 90, this);
	g.drawImage (poster, 100, 220, 400, 200, this);

	g.drawImage (text1, 180, 60, 250, 30, this);
	g.drawImage (text2, 250, 97, 100, 20, this);
	g.drawImage (text3, 160, 130, 300, 30, this);
    }



    public void paint (Graphics g)
    {

	update (g);
	clip.loop ();

	g.setColor (Color.black);
	g.fillRect (0, 0, 600, 600);

    }


    public void mousePressed (MouseEvent e)
    {
	/*if (e.getSource () == startGame)
	{
	    starting = true;
	}*/

	 if (e.getSource () == rules)
	{
	    ruleChoice = JOptionPane.showOptionDialog (null, "Choose an option.", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ruleOption, null);

	    if (ruleChoice == 0)
	    {
		JOptionPane.showMessageDialog (null, "Move Fire Boy with the arrow keys and move Water Girl with the WASD keys.\nGet through the obstacles in order to reach the doors at the end of the level.\nBoth Fireboy and Watergirl must reach their respective doors in order to pass the level.\nCollect the gems and finish the level quickly to get a high score.", "Rules", JOptionPane.INFORMATION_MESSAGE);
	    }

	    else
	    {
		JOptionPane.showMessageDialog (null, "Lava Pool - Fireboy can safely pass through lava pools, but Watergirl will get hurt.\nWater Puddle - Watergirl can safely pass through water puddles, but Fireboy will get hurt.\nAcid Pools - Both Fireboy and Watergirl cannot pass through these green pools.\nSwitches/Buttons - Activates an event. Switches stay on and buttons require an object to stay on top of it.\nTeleport - Teleports the character to another teleport on the level.\nArrow Shooter - shoots arrows that must be dodged.\nLight Sensors - Direct a light beam onto the sensors to activate an event.", "Obstacles", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
    }
} //End of class
