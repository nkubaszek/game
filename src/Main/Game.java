/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.JFrame;

public class Game {


	public static void main(String[] args) {

		JFrame window = new JFrame("Recykling");

		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);//nie mozna zmieic rozmiaru okna
		window.pack();
		window.setVisible(true);

	}

}
    
         
       
        
    
        
        
       
    
    
    

