package gui;


import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    int x;
    int y;

    int red = (int) (Math.random() * 256);
    int green = (int) (Math.random() * 256);
    int blue = (int) (Math.random() * 256);

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(new Color(red, green, blue));
        g.fillOval(x, y, 30, 30);

//        Image pic = new ImageIcon("moonlight.jpg").getImage();
//        g.drawImage(pic, 200, 200, this);
    }
}
