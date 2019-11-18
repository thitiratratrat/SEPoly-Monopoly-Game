package allSpritePlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static javax.imageio.ImageIO.read;

public class BufferedImageLoader {
    public BufferedImage loadImage(String pathRelativeToThis) throws IOException {
        URL url = this.getClass().getResource(pathRelativeToThis);
        BufferedImage img = read(url);
        return img;
    }
}
