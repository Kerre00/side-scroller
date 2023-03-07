package se.liu.kevri781;

import java.awt.*;

public class GameStateManager
{
//    Manages the game states, such as the main menu, the game, and the pause menu.
    private GameState[] gameStates;
    private int currentState;

    public static final int NUMGAMESTATES = 1;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
//    public static final int LEVEL2STATE = 2;
//    public static final int GAMEOVERSTATE = 3;

    public GameStateManager()
    {
	gameStates = new GameState[NUMGAMESTATES];

	currentState = MENUSTATE;
	loadState(currentState);
    }

    private void loadState(int state)
    {
	if(state == MENUSTATE)
	    gameStates[state] = new GameState();
//	if(state == LEVEL1STATE)
//	    gameStates[state] = new GamePanel();
//	if(state == GAMEOVERSTATE)
//	    gameStates[state] = new GameOverState(this);
    }

    private void unloadState(int state)
    {
	gameStates[state] = null;
    }

    public void setState(int state)
    {
	unloadState(currentState);
	currentState = state;
	loadState(currentState);
    }

    public void update()
    {
	try
	{
	    gameStates[currentState].update();
	}
	catch(Exception e)
	{

	}
    }

    public void draw(Graphics2D g)
    {
	try
	{
	    gameStates[currentState].draw(g);
	}
	catch(Exception e)
	{

	}
    }

    public void keyPressed(int k)
    {
	gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k)
    {
	gameStates[currentState].keyReleased(k);
    }
}
