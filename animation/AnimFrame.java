package animation;

import java.awt.Image;

public class AnimFrame {
    Image image;
    long endTime;

    public AnimFrame(Image image, long endTime) {
        this.image = image;
        this.endTime = endTime;
    }
}
