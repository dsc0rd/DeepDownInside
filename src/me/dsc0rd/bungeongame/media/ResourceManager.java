package me.dsc0rd.bungeongame.media;

import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class ResourceManager {
    public Texture halfHeartTexture, fullHeartTexture;
    public Image halfHeartImage, fullHeartImage;

    public ResourceManager() throws IOException {
        halfHeartTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/half_heart.png"));
        fullHeartTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/full_heart.png"));
        halfHeartImage = new Image(halfHeartTexture);
        fullHeartImage = new Image(fullHeartTexture);
    }
}
