package me.dsc0rd.bungeongame;

import me.dsc0rd.bungeongame.media.ResourceManager;
import me.dsc0rd.bungeongame.states.GameplayState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main extends StateBasedGame {
    public static final GameplayState gameplay = new GameplayState(4);

    public static ResourceManager resourceManager;

    public static final int height = 1080;
    public static final int width = 1920;

    private Main(String name) {
        super(name);
    }

    public static boolean debug = false;

    public static void main(String[] args) throws SlickException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        AppGameContainer container = new AppGameContainer(new Main("Deep Down Inside..."));
        container.setDisplayMode(width, height, true);

        container.start();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(gameplay);
        container.setTargetFrameRate(120);
        container.setShowFPS(false);
        enterState(4);

    }

}
