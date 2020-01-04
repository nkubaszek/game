/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import Main.GamePanel;


public class Window extends JFrame{
    JButton b1;
    JButton b2;
    Window(String nazwa){
        super(nazwa);
//metoda super wywołuje konstruktor nadklasy



        setLayout(new FlowLayout());
        b1=new JButton("Przycisk 1.");
        b1.setBackground(Color.BLACK);
        b2=new JButton("Przycisk 2.");
        add(b1);
        add(b2);


    }
    public void update(){}
    public void init(){}
    public void draw(java.awt.Graphics2D g){}}



