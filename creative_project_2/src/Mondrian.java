import java.util.*;
import java.awt.*;

public class Mondrian {

    // Fields
    Color[] mondrianColors;
    Random random;

    /**
     * Instantiates Mondrian object used to paint Mondrian images.
     */
    public Mondrian() {
        mondrianColors = new Color[]{Color.RED, Color.CYAN, Color.YELLOW, Color.WHITE};
        random = new Random();
    }

    /**
     * Paint a basic Mondrian image.
     * 
     * @param pixels Matrix of Color objects that represent the canvas.
     */
    public void paintBasicMondrian(Color[][] pixels) {
        paintBasicMondrian(pixels, 0, 0, pixels[0].length, pixels.length);
    }

    public void paintComplexMondrian(Color[][] pixles) {

    }

    /**
     * Recursive auxilliary method to paint the Mondrian.
     * 
     * @param pixels Matrix of Color objects that represent the canvas.
     * @param w1 The starting column of the current section.
     * @param h1 The starting row of the current section
     * @param w2 The ending column of the current section.
     * @param h2 The ending row of the current section.
     */
    private void paintBasicMondrian(Color[][] pixels, int w1, int h1, int w2, int h2) {
        int fullHeight = pixels.length;
        int fullWidth = pixels[0].length;
        int height = h2 - h1;
        int width = w2 - w1;

        if (width != 0 && height != 0) {
            int splitWidth = w1 + random.nextInt(width);
            int splitHeight = h1 + random.nextInt(height);

            if (height >= fullHeight / 4 && width >= fullWidth / 4) {
                paintBasicMondrian(pixels, w1, h1, splitWidth, splitHeight);
                paintBasicMondrian(pixels, splitWidth + 1, h1, w2, splitHeight);
                paintBasicMondrian(pixels, w1, splitHeight + 1, splitWidth, h2);
                paintBasicMondrian(pixels, splitWidth + 1, splitHeight + 1, w2, h2);
            } else if (height >= fullHeight / 4) {
                paintBasicMondrian(pixels, w1, h1, w2, splitHeight);
                paintBasicMondrian(pixels, w1, splitHeight + 1, w2, h2);
            } else if (width >= fullWidth / 4) {
                paintBasicMondrian(pixels, w1, h1, splitWidth, h2);
                paintBasicMondrian(pixels, splitWidth + 1, h1, w2, h2); 
            } else {
                fillRegion(pixels, w1, h1, w2, h2);
            }
        }
    }

    /**
     * Auxilliary method to fill a specified region with a random Mondrian color.
     * 
     * @param pixels Matrix of Color objects that represent the canvas.
     * @param w1 The starting column of the current section.
     * @param h1 The starting row of the current section
     * @param w2 The ending column of the current section.
     * @param h2 The ending row of the current section.
     */
    private void fillRegion(Color[][] pixels, int w1, int h1, int w2, int h2) {
        Color color = mondrianColors[random.nextInt(mondrianColors.length)];
        for (int i = h1; i < h2; i++) {
            for (int j = w1; j < w2; j++) {
                pixels[i][j] = color;
            }
        }
    }
}
