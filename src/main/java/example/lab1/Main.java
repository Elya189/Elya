package example.lab1;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(Lab1.from10to2(293));
        System.out.println(Lab1.from2to10(Lab1.from10to2(293)));
    }
}
