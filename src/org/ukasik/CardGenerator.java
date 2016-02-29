package org.ukasik;

import java.util.Random;

public class CardGenerator {
    
    public static boolean ShowFirstCard;
    private static Random Generator = new Random();
    private static int GeneratedCard;
    
    public static int AdjustCard()
    {
        GeneratedCard = Generator.nextInt(12);
        return GeneratedCard;
    }
    
}
