package com.main.java.invoice.project.form;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
{
    private Image image;

    public Panel()
    {
        image = new ImageIcon("C:\\\\Program Files\\\\Invoice\\\\image\\\\background.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
    }
}