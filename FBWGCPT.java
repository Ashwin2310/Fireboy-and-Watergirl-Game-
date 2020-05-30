/*CPT 2018 - MADE BY : MARY GRACE WONG AND ASHWIN THOMAS , DURING THE MONTHS OF MAY AND JUNE
	     PROGRAM : FIREBOY AND WATERGIRL GAME REMAKE (GAME IDEA FROM COOL MATH GAMES)
	     ICS4U FINAL GAME DEVELOPMENT ASSIGNMENT.
	     MADE ON APPLET (FOR AN OPTIMAL GAMING EXPERIENCE, USE APPLET WINDOW SIZE: 600 X 600) *WILL EXPERIENCE CONSTANT SCREEN GLITCHES/REFRESHES*
*/

/*Importing all the library classes that are need for the program to function*/
//--------------------------------------------------------------------------------
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.io.*;
import javax.swing.*;

//--------------------------------------------------------------------------------

public class FBWGCPT extends Applet implements KeyListener, MouseListener, MouseMotionListener, Runnable
{
    /*STATING THE GLOBAL VARIABLES*/
    //--------------------------------------------------------------------------------
    Button gameMenu; // the button that brings up the quit, restart, rules, obstacle info and the resume button

    String[] gameOption = {"Quit", "Restart", "Rules", "Obstacle Info", "Resume"};
    String str = "", deathMessage = "You died! Try again."; //str - is to give the message for mouse clicked

    int gameOptionChoice, yesNo; // just all options under the gameMenu button, yes or no options to quit or stay in the game
    int x, y, x2, y2, speed2, speed, gravity; // x and y are for player 1 (fireboy) and x2 and y2 are for player 2(watergirl)
    int mouseX, mouseY; //gives mouse clicked co-ordinates
    int platformX, platformSpeed, platformY, platformSpeed2, gate1, gate2; // the moving platforms with the respective speeds
    int counter, blueGemCounter, redGemCounter; //all collectibles in the game

    boolean left, right, up, rest, left2, right2, up2, rest2; // changing the counter of this variable results in the change in the players location. Implemented for both players
    boolean foundBlueGem1, foundBlueGem2, foundBlueGem3, foundBlueGem4, foundRedGem1, foundRedGem2, foundRedGem3, foundRedGem4; // made for each of the 8 (4 red and 4 blue) gems that are placed throughout the map

    Image ground, ground2, backgroundBrick, waterDoor, fireDoor, fireBoy, waterGirl, firePool, waterPool, acidPool, platform, flame, water, movingPlatform, blueGem, gate, redGem, teleport, upsideDownTeleport, blockage, redButton, redButtonPressed, spikes, spikesUpsideDown; //importing images
    AudioClip levelOneClip;

    static Font Arial = new Font ("Arial", Font.BOLD, 15); // font used for time remaining

    Thread time; // time which is displayed on the bottom left part of the screen
    //BufferedImage bf;

    //--------------------------------------------------------------------------------

    public void init ()
    {

	/*START INIT*/
	this.setLayout (null); //so that we can change the layout of the window

	addMouseListener (this);
	addMouseMotionListener (this);
	addKeyListener (this);
	setFocusable (true);

	// initializing the variables
	x = 70;
	y = 480;
	x2 = -20;
	y2 = 470;
	gravity = 0;
	speed = 0;
	speed2 = 0;
	left = false;
	up = false;
	right = false;
	rest = true;
	left2 = false;
	up2 = false;
	right2 = false;
	rest2 = true;
	blueGemCounter = 0;
	redGemCounter = 0;
	platformX = 120;
	platformY = 270;
	platformSpeed = 10;
	platformSpeed2 = 5;

	gate1 = 100;
	gate2 = 100;


	ground = getImage (getDocumentBase (), "ground.jpg");
	ground2 = getImage (getDocumentBase (), "block3.png");
	backgroundBrick = getImage (getDocumentBase (), "brick.png");
	waterDoor = getImage (getDocumentBase (), "waterDoor.png");
	fireDoor = getImage (getDocumentBase (), "fireDoor.png");
	fireBoy = getImage (getDocumentBase (), "fireboy.jpg");
	waterGirl = getImage (getDocumentBase (), "watergirl.png");
	firePool = getImage (getDocumentBase (), "firePool.png");
	platform = getImage (getDocumentBase (), "platform.png");
	waterPool = getImage (getDocumentBase (), "waterPool.png");
	acidPool = getImage (getDocumentBase (), "acidPool.png");
	movingPlatform = getImage (getDocumentBase (), "movingPlatform.png");
	blueGem = getImage (getDocumentBase (), "blueGem.png");
	gate = getImage (getDocumentBase (), "gate.png");
	redGem = getImage (getDocumentBase (), "redGem.png");
	teleport = getImage (getDocumentBase (), "teleport.png");
	upsideDownTeleport = getImage (getDocumentBase (), "upsideDownTeleport.png");
	blockage = getImage (getDocumentBase (), "blockage.png");
	redButton = getImage (getDocumentBase (), "redButton.png");
	redButtonPressed = getImage (getDocumentBase (), "redButtonPressed.png");
	spikes = getImage (getDocumentBase (), "spikes.png");
	spikesUpsideDown = getImage (getDocumentBase (), "spikesUpsideDown.png");

	levelOneClip = getAudioClip (getDocumentBase (), "Arcade-Funk.wav");

	gameMenu = new Button ("Menu");
	gameMenu.setBounds (550, 20, 40, 20);
	add (gameMenu);

	gameMenu.addMouseListener (this);
	/*END OF INIT*/
    } //End of init



    public void update (Graphics g)
    {
	/*START UPDATE METHOD*/

	for (int b = 0 ; b < 3 ; b++) //creating background graphics
	{
	    for (int c = 0 ; c < 7 ; c++)
	    {
		g.drawImage (backgroundBrick, 0 + (200 * b), 600 - (c * 100), 200, 100, this);
	    }
	}

	for (int i = 0 ; i < 6 ; i++) //floor
	{
	    g.drawImage (ground, 0 + (100 * i), 520, 150, 80, this);

	}

	for (int a = 0 ; a < 6 ; a++) //bottom layer
	{
	    g.drawImage (ground2, -10 + (65 * a), 410, 80, 70, this);
	}

	for (int a2 = 0 ; a2 < 4 ; a2++)
	{
	    g.drawImage (ground2, 340 + (65 * a2), 330, 80, 70, this);
	}

	for (int a3 = 0 ; a3 < 3 ; a3++)
	{
	    g.drawImage (ground2, -10 + (a3 * 65), 330, 80, 70, this);
	}

	for (int d = 0 ; d < 7 ; d++)
	{
	    g.drawImage (ground2, 200 + (65 * d), 230, 80, 70, this);
	}

	for (int e = 0 ; e < 2 ; e++)
	{
	    g.drawImage (ground2, -10 + (65 * e), 160, 80, 70, this);
	}


	for (int h = 0 ; h < 6 ; h++)
	{
	    g.drawImage (ground2, -10 + (65 * h), 60, 80, 70, this);
	}

	g.drawImage (ground2, 380, 70, 80, 70, this);

	g.drawImage (fireDoor, -10, -30, 150, 160, this);
	g.drawImage (waterDoor, 70, -30, 160, 160, this);
	g.drawImage (firePool, 370, 380, 300, 200, this);
	g.drawImage (acidPool, 490, 220, 130, 50, this);
	g.drawImage (acidPool, 390, 220, 130, 50, this);
	g.drawImage (acidPool, 290, 220, 130, 50, this);
	g.drawImage (acidPool, 190, 220, 130, 50, this);

	g.drawImage (spikes, 270, 60, 100, 50, this);
	g.drawImage (spikesUpsideDown, 220, 265, 100, 50, this);
	g.drawImage (spikesUpsideDown, 455, 365, 100, 50, this);

	g.drawImage (gate, 70, 100, 100, gate1, this);
	g.drawImage (gate, 400, 265, 100, gate2, this);
	g.drawImage (blockage, 90, 160, 140, 140, this);

	g.drawImage (teleport, -15, 310, 130, 70, this);
	g.drawImage (upsideDownTeleport, 460, -30, 160, 90, this);

	g.drawImage (movingPlatform, platformX, 115, 100, 100, this);
	platformX = platformX + platformSpeed;

	if (platformX < 120 || platformX > 480) // moving the platforms
	{
	    platformSpeed = -platformSpeed;
	}

	g.drawImage (movingPlatform, 220, platformY, 100, 100, this);

	platformY = platformY + platformSpeed2;

	if (platformY < 270 || platformY > 355)
	{
	    platformSpeed2 = -platformSpeed2;
	}

	if (!foundBlueGem1) //gem detection - check the method named "checkBlueGem and checkRedGem" (before mousepressed method)
	{
	    g.drawImage (blueGem, 485, 410, 40, 40, this);
	    foundBlueGem1 = checkGem (x2, y2, 485, 410);
	    if (foundBlueGem1)
		blueGemCounter++;
	}
	if (!foundBlueGem2)
	{
	    g.drawImage (blueGem, 5, 120, 40, 40, this);
	    foundBlueGem2 = checkGem (x2, y2, 5, 120);
	    if (foundBlueGem2)
		blueGemCounter++;
	}
	if (!foundBlueGem3)
	{
	    g.drawImage (blueGem, 520, 40, 40, 40, this);
	    foundBlueGem3 = checkGem (x2, y2, 520, 40);
	    if (foundBlueGem3)
		blueGemCounter++;
	}
	if (!foundBlueGem4)
	{
	    g.drawImage (blueGem, 320, 120, 40, 40, this);
	    foundBlueGem4 = checkGem (x2, y2, 320, 120);
	    if (foundBlueGem4)
		blueGemCounter++;
	}
	if (!foundRedGem1)
	{
	    g.drawImage (redGem, 200, 115, 55, 55, this);
	    foundRedGem1 = checkGem (x, y, 200, 115);
	    if (foundRedGem1)
		redGemCounter++;
	}
	if (!foundRedGem2)
	{
	    g.drawImage (redGem, 40, 115, 55, 55, this);
	    foundRedGem2 = checkGem (x, y, 40, 115);
	    if (foundRedGem2)
		redGemCounter++;
	}
	if (!foundRedGem3)
	{
	    g.drawImage (redGem, 20, 220, 55, 55, this);
	    foundRedGem3 = checkGem (x, y, 20, 220);
	    if (foundRedGem3)
		redGemCounter++;
	}
	if (!foundRedGem4)
	{
	    g.drawImage (redGem, 490, 280, 55, 55, this);
	    foundRedGem4 = checkGem (x, y, 490, 280);
	    if (foundRedGem4)
		redGemCounter++;
	}

	g.setFont (Arial);
	g.setColor (Color.white);

	g.drawImage (blueGem, 05, 555, 35, 35, this);
	g.drawString ("x " + blueGemCounter, 10, 555); // for the bottom display - showing the gem counter incrementing

	g.drawImage (redGem, 40, 553, 45, 45, this);
	g.drawString ("x " + redGemCounter, 50, 555);

	g.drawImage (redButton, 60, 395, 50, 50, this);
	g.drawImage (redButton, 490, 315, 50, 50, this);

	g.drawImage (fireBoy, x, y, 30, 40, this);
	g.drawImage (waterGirl, x2, y2, 80, 60, this);

	if (left)                                           //fireboy motion
	{
	    x -= 10;
	    if (x < 0)
		x = 0;
	}
	if (right)
	{
	    x += 10;
	    if (x > 570)
		x = 570;
	}
	if (up && rest)
	{
	    gravity = 4;
	    speed = 30;
	    rest = false; //so character can jump

	}

	y -= speed; //Velocity upwards
	speed -= gravity; //Velocity deceleration

	if (speed > -30)
	{
	    speed -= gravity;
	}

	if (left2)                                          //watergirl motion
	{
	    x2 -= 10;
	    if (x2 < 0)
		x2 = 0;
	}
	if (right2)
	{
	    x2 += 10;
	    if (x2 > 550)
		x2 = 550;
	}
	if (up2 && rest2)
	{
	    gravity = 4;
	    speed2 = 30;
	    rest2 = false;

	}

	if (speed2 >= 30)
	{
	    speed -= gravity;
	}

	y2 -= speed2; //Velocity upwards
	speed2 -= gravity; //Velocity deceleration

	g.setColor (Color.green);
	g.setFont (Arial);
	g.drawString ("Time Remaining: " + String.valueOf ("\n" + counter), 425, 590); // total time = 5 mins, 300 seconds

	if ((x >= 213 && x <= 595) || (x2 >= 213 && x <= 595)) //acid pools
	{
	    if ((y >= 210 && y <= 230) || (y2 >= 210 && y2 <= 230))
	    {
		x = 70;
		y = 480;
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, deathMessage); // every death = level restarts
		restart ();
	    }
	}


	if (x2 >= 470 && x2 <= 540) //lowest firePool
	{
	    if (y2 >= 475 && y2 <= 520)
	    {
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, deathMessage);
		restart ();
	    }
	}

	if ((x >= 460 && x <= 550) || (x2 >= 460 && x2 <= 550)) //spikes
	{
	    if ((y >= 365 && y <= 375) || (y2 >= 365 && y2 <= 375))
	    {
		x = 70;
		y = 480;
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, deathMessage);
		restart ();
	    }
	}

	if ((x >= 225 && x <= 310) || (x2 >= 225 && x2 <= 310)) //moving platform spikes
	{
	    if ((y >= 280 && y <= 285) || (y2 >= 280 && y2 <= 285))
	    {
		x = 70;
		y = 480;
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, deathMessage);
		restart ();

	    }
	}

	if (x >= 5 && x <= 85) //teleport
	{
	    if (y >= 275 && y <= 315)
	    {
		x = 515;
		y = 20;
	    }
	}

	if (x2 > 5 && x2 <= 85) //teleport player 2
	{
	    if (y2 >= 275 && y2 <= 315)
	    {
		x = 515;
		y = 20;
	    }
	}


	if ((x >= 275 && x <= 360) || (x2 >= 275 && x2 <= 360))  //top lowest spike
	{
	    if ((y >= 70 && y <= 80) || (y2 >= 70 && y2 <= 80))
	    {
		x = 70;
		y = 480;
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, deathMessage);
		restart ();
	    }
	}

	if ((x >= 65 && x <= 100) || (x2 >= 65 && x2 <= 100)) //button 1
	{
	    if ((y >= 385 && y <= 410) || (y2 >= 385 && y2 <= 410))
	    {
		gate2 = 10;
	    }
	}

	if ((x >= 495 && x <= 530) || (x2 >= 495 && x2 <= 530)) //button 2
	{
	    if ((y >= 320 && y <= 335) || (y2 >= 320 && y2 <= 335))
	    {
		gate1 = 10;
	    }
	}



	if (speed > 0 && x > -40 && x < 380 && y > 390 && y < 455) //Collision for first platform roof
	{
	    speed = 0;
	}

	if (speed2 > 0 && x2 > -40 && x2 < 380 && y2 > 390 && y2 < 455) //Collision for first platform roof
	{
	    speed2 = 0;
	}

	if (speed <= 0 && x > -40 && x < 600 && y > 475) //Collision for first floor - bottom side of the first platform
	{
	    speed = 0;
	    rest = true;
	}

	if (speed2 <= 0 && x2 > -40 && x2 < 600 && y2 > 475) //Collision for first floor - bottom side of the first platform
	{

	    speed2 = 0;
	    rest2 = true;
	}


	if (speed < 0 && x > -40 && x < 385 && y < 430 && y > 375) //1st Platform (the long one) - to stay in region 2
	{
	    speed = 0;
	    rest = true;
	}

	if (speed2 < 0 && x2 > -40 && x2 < 385 && y2 < 430 && y2 > 375) //1st Platform (the long one) - to stay in region 2
	{
	    speed2 = 0;
	    rest2 = true;
	}



	if (speed <= 0 && x > 210 && x < 300 && y < 390 && y > platformY + 20) // vertical motion of the brown lift
	{
	    rest = true;
	    speed = -platformSpeed2;
	}
	if (speed < 0 && x > -40 && x < 185 && y < 350 & y > 320) //2nd region , left brick
	{
	    speed = 0;
	    rest = true;
	}
	if (speed < 0 && x > 350 && x < 600 && y < 350 && y > 310) //2nd region , right brick
	{
	    speed = 0;
	    rest = true;
	}
	if (speed < 0 && x > 195 && x < 600 && y < 250 && y > 210) // 3rd region, main brick
	{
	    speed = 0;
	    rest = true;
	}
	if (speed < 0 && x > -40 && x < 120 && y < 180 && y > 140) // 3rd region, left brick
	{
	    speed = 0;
	    rest = true;
	}
	if (speed <= 0 && x > platformX && x < platformX + 70 && y < 170 && y > 130) // horizontal motion of the top lift
	{
	    rest = true;
	    speed = 0;
	    x += platformSpeed;
	}
	if (speed < 0 && x > -40 && x < 425 && y < 75 && y > 42) // 4rd region, main brick
	{
	    speed = 0;
	    rest = true;
	}


	if (x2 > 230 && x2 < 300 && y2 < 390 && y2 > platformY) // vertical motion of the brown lift
	{
	    rest2 = true;
	    speed2 = -platformSpeed2;
	}
	if (speed2 < 0 && x2 > -40 && x2 < 185 && y2 < 350 & y2 > 200) //2nd region , left brick
	{
	    speed2 = 0;
	    rest2 = true;
	}
	if (speed2 < 0 && x2 > 340 && x2 < 600 && y2 < 350 && y2 > 270) //2nd region , right brick
	{
	    speed2 = 0;
	    rest2 = true;
	}
	if (x2 > 215 && x2 < 600 && y2 < 110 && y2 > 220) // 3rd region, main brick
	{
	    speed2 = 0;
	    rest2 = true;
	}

	//win condition
	if ((x2 >= 130 && x2 <= 165) || (x >= 40 && x <= 80))
	{
	    if ((y >= 40 && y <= 55) || (y2 >= 40 && y2 <= 55))
	    {
		x = 70;
		y = 480;
		x2 = -20;
		y2 = 470;
		JOptionPane.showMessageDialog (null, "Congratuations! You won! The game will now close.");
		System.exit (0);
	    }
	}

	delay (50);
	repaint ();

	g.fillOval (mouseX, mouseY, 10, 10); //to get the exact co-ordinates in the map
	g.drawString (mouseX + "," + mouseY, mouseX + 10, mouseY - 10);
	g.drawString (str, mouseX + 10, mouseY + 20);
	showStatus (str + " at " + mouseX + "," + mouseY);

	/*END UPDATE METHOD*/
    } //update method




    public void paint (Graphics g)
    {
	/*START PAINT METHOD*/
	//levelOneClip.loop ();
	update (g);
	/*END PAINT METHOD*/
    } //End of paint


    public void run ()  // thread which gives countdown - every second
    {
	/*START RUN*/
	while (counter > 0 && time != null)
	{
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (InterruptedException e)
	    {
	    }
	    --counter;
	    repaint ();

	}
	/*END RUN*/

    } //end run


    public void stop ()
    {
	time = null;
    } //end stop


    public void start ()  //creates the thread
    {

	counter = 300;
	time = new Thread (this);
	time.start ();
    } //end start


    public void restart ()  //sets fireboy and watergirl to the original positions - occurs when timer runs out or the players die
    {
	x = 70;
	y = 480;
	x2 = -20;
	y2 = 470;
	gravity = 0;
	speed = 0;
	left = false;
	up = false;
	right = false;
	rest = true;
	left2 = false;
	right2 = false;
	up2 = false;
	rest2 = true;

	gate1 = 100;
	gate2 = 100;

    } //end restart


    private boolean checkGem (int x, int y, int setterX, int setterY)  // the hitbox for where the red gem is placed
    {
	if ((x > setterX - 15 && x < setterX + 15) && (y > setterY - 15 && y < setterY + 15))
	    return true;
	else
	    return false;
    } //end checkGem


    private boolean checkBlueGem (int x2, int y2, int setterX, int setterY)  // the hitbox for where the blue gem is placed
    {
	if ((x2 > setterX - 15 && x2 < setterX + 15) && (y2 > setterY - 15 && y2 < setterY + 15))
	    return true;
	else
	    return false;
    } //end checkBlueGem


    public void mousePressed (MouseEvent e)  // mouse pressed for the menu button
    {
	if (e.getSource () == gameMenu)
	{
	    gameOptionChoice = JOptionPane.showOptionDialog (null, "What would you like to do?", "Game Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameOption, null);

	    if (gameOptionChoice == 0)
	    {
		yesNo = JOptionPane.showOptionDialog (null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (yesNo == 0)
		    JOptionPane.showMessageDialog (null, "Thank you for playing!");
		System.exit (0);


	    } //Exit button

	    else if (gameOptionChoice == 1)
	    {
		restart ();
	    } //Restart button

	    else if (gameOptionChoice == 2)
	    {
		JOptionPane.showMessageDialog (null, "Move Fire Boy with the arrow keys and move Water Girl with the WASD keys.\nGet through the obstacles in order to reach the doors at the end of the level.\nBoth Fireboy and Watergirl must reach their respective doors in order to pass the level.\nCollect the gems and finish the level quickly to get a high score.", "Rules", JOptionPane.INFORMATION_MESSAGE);

	    }
	    else if (gameOptionChoice == 3)
	    {
		JOptionPane.showMessageDialog (null, "Lava Pool - Fireboy can safely pass through lava pools, but Watergirl will get hurt.\nWater Puddle - Watergirl can safely pass through water puddles, but Fireboy will get hurt.\nAcid Pools - Both Fireboy and Watergirl cannot pass through these green pools.\nSwitches/Buttons - Activates an event. Switches stay on and buttons require an object to stay on top of it.\nTeleport - Teleports the character to another teleport on the level. ", "Obstacles", JOptionPane.INFORMATION_MESSAGE);

	    }

	    else
	    {
		//exit joptionpane panel
	    } //Resume button
	} //End of outer if statement
    } //End of mousePressed


    public void mouseClicked (MouseEvent e)  //mouse clicked for the co-ordinates
    {
	mouseX = e.getX ();
	mouseY = e.getY ();
	str = "Mouse Clicked";
	repaint ();
    } //End of mouseClicked


    public void mouseEntered (MouseEvent e)  //other Mouse Events that need to be implemented
    {
    } //End of mouseEntered


    public void mouseReleased (MouseEvent e)
    {
    } //End of mouseReleased


    public void mouseExited (MouseEvent e)
    {
    } //End of mouseExited


    public void mouseMoved (MouseEvent e)
    {
    }


    public void mouseDragged (MouseEvent e)
    {
    }


    public void keyPressed (KeyEvent ke)
    {
	int keyCode = ke.getKeyCode ();
	if (keyCode == 37) // left arrow
	{
	    left = true;
	}


	else if (keyCode == 38) //up
	{
	    up = true;

	}


	else if (keyCode == 39) //right
	{
	    right = true;
	}


	else if (keyCode == KeyEvent.VK_A)
	{
	    left2 = true;

	}


	else if (keyCode == KeyEvent.VK_W)
	{
	    up2 = true;

	}


	else if (keyCode == KeyEvent.VK_D)
	{
	    right2 = true;

	}
    } //End of keyPressed


    public void keyReleased (KeyEvent ke)
    {
	int keyCode = ke.getKeyCode (); // to get the ascii value of a certain key
	if (keyCode == 37) // left arrow                                                // all number keys are for fireboy and the numbers represent the arrow keys
	{
	    left = false;
	}


	else if (keyCode == 38) //up
	{
	    up = false;

	}


	else if (keyCode == 39) //right
	{
	    right = false;
	}


	else if (ke.getKeyCode () == KeyEvent.VK_A)                                      //all Letter keys are for watergirl
	{
	    left2 = false;

	}


	else if (ke.getKeyCode () == KeyEvent.VK_W)
	{
	    up2 = false;

	}


	else if (ke.getKeyCode () == KeyEvent.VK_D)
	{
	    right2 = false;
	}
    } //End of keyPressed


    public void keyTyped (KeyEvent e)  //other Key Events that need to be implemented
    {
    } //End of keyTyped


    public void delay (int time)  // this method was called in the update method and can change how quickly the game loads // given time = 50
    {
	try
	{
	    Thread.sleep (time);
	} //End of try


	catch (Exception e)
	{

	} //End of catch
    } //End of delay method



} //End of class
