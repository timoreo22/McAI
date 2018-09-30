package fr.timoreo.mcia.test;

import org.junit.jupiter.api.Test;

import java.util.List;

public class IATest {
    public static int[] labels;
    public static List<int[][]> images;

    @Test
    public static void test() {
        load();
    }

    public static void load() {
        ClassLoader classLoader = IATest.class.getClassLoader();
        labels = MnistReader.getLabels(classLoader.getResource("train/label/train-labels-idx1-ubyte").getFile());
        images = MnistReader.getImages(classLoader.getResource("train/images/train-images-idx3-ubyte").getFile());
    }
}
