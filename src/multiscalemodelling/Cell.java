/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiscalemodelling;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author hacio
 */
public class Cell {

    Color color;
    int id;
    boolean visited;

    public Cell() {
        color = Color.WHITE;
        id = idColor(color);
        visited = false;
    }

    public static Color randomColor() {
        Random rand = new Random();
        //float r = rand.nextFloat();// /2f+0.3F;
        //float g = rand.nextFloat();
        //float b = rand.nextFloat();
        float h = rand.nextFloat();
        float s = rand.nextFloat();
        float b = rand.nextFloat();
        h = rand.nextFloat();
        s = rand.nextFloat();
        b = rand.nextFloat() * (1f - 0.3f) + 0.3f;
        Color randomColor = new Color(Color.HSBtoRGB(h, s, b));
        return randomColor;
    }

    public static int idColor(Color color) {
        return color.getRGB();
    }
    
    public static Color energyColor(int H){
        float h;
        float s;
        float b;
        h = 0.675f;
        s = H*0.1f;
        b = 0.5f;
        Color energyColor = new Color(Color.HSBtoRGB(h, s, b));        
        return energyColor;
    }
}
