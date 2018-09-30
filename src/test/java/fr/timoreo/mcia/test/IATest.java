package fr.timoreo.mcia.test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class IATest {
    public static int[] labels;
    public static List<int[][]> images;
    private static final int HEIGHT = 28;
    private static final int WIDTH = 28;
    private static BufferedImage img = null;

    public static void main(String... args) {
        IATest t = new IATest();
        t.test();
    }

    public static void load() {
        ClassLoader classLoader = IATest.class.getClassLoader();
        labels = MnistReader.getLabels(classLoader.getResource("train/label/train-labels-idx1-ubyte").getFile());
        images = MnistReader.getImages(classLoader.getResource("train/images/train-images-idx3-ubyte").getFile());
        File file = new File(classLoader.getResource("test_img.jpg").getFile());
        try {
            img = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showImg(int[][] data) {
        WritableRaster raster1 = img.getRaster();
        /*final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = (Graphics2D)img.getGraphics();
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                float c = (float) data[i][j];
                //g.setColor(new Color(c,c,c));
                g.fillRect(i, j, 1, 1);
                //data[i][j] = r.nextDouble();
            }
        }*/
        int[] tempArr = new int[WIDTH * HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tempArr[0] = 100;
                raster1.setPixel(i, j, tempArr);
            }
        }
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        image.setData(raster1);

        JFrame frame = new JFrame("Image test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.clearRect(0, 0, getWidth(), getHeight());
                g2d.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                // Or _BICUBIC
                g2d.scale(2, 2);
                g2d.drawImage(image, 0, 0, this);
            }
        };
        panel.setPreferredSize(new Dimension(WIDTH * 2, HEIGHT * 2));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        load();
        System.out.println("i have " + Arrays.deepToString(images.get(0)) + " and " + labels[0]);
        showImg(images.get(0));
    }
}
