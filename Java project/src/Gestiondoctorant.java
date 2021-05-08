import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Gestiondoctorant extends JFrame implements ActionListener {

	  private JMenuBar menubar = new JMenuBar();

	  private JMenu fichier = new JMenu("Fichier");
	  private JMenuItem charger = new JMenuItem("Actualiser");
	  private JMenuItem quit = new JMenuItem("Se déconnecter");

	  private JMenu gestion = new JMenu("Domain");
	  private JMenuItem condidat = new JMenuItem("voir ma condidature");
	  private JMenuItem etat = new JMenuItem("voir mon état");

	


	  private JMenu recherche = new JMenu();
	  //private JMenuItem demande = new JMenuItem("Voir toutes les demandes");
	  private JMenuItem demande[]=new JMenuItem[100];

	  private JMenu aide = new JMenu("Aide");
	  private JMenuItem about = new JMenuItem("À propos...");
	  private Account doctorant=new Account();

		//File with the information for each created account
	  private File f = new File("accounts.txt");
	  //File with the words of invalid passwords
	  private File bp = new File("dictbadpass.txt");

	  ImageIcon j1=new ImageIcon("images/book1.jpg");
	  JLabel img=new JLabel(j1);
	  
	  ImageIcon j2=new ImageIcon("images/good.png");
	  JLabel w=new JLabel(j2);
	  public Gestiondoctorant(Account account)
	  { 
		this.doctorant=account;
	    charger.addActionListener(this);
	    fichier.add(charger);
	    fichier.addSeparator();
	    quit.addActionListener(this);
	    fichier.add(quit);

	    condidat.addActionListener(this);
	    gestion.add(condidat);
	    etat.addActionListener(this);
	    gestion.add(etat);

	    JLabel hello=new JLabel("Bienvenue "+account.getName()+" dans votre espace");
	    hello.setBounds(20,0,600,50);
	    hello.setOpaque(false);
	    hello.setForeground(Color.white);
	    hello.setFont(new Font("",Font.BOLD,20));
	    w.add(hello, BorderLayout.CENTER);
	    about.addActionListener(this);
	    aide.add(about);
		  img.setBounds(0, 0, 900, 500);
		  w.setBounds(145, 220, 550, 60);

	    menubar.add(fichier);
	    menubar.add(gestion);
	    menubar.add(aide);
		 img.add(w,BorderLayout.CENTER);

	    
	    setJMenuBar(menubar);
	    getContentPane().setLayout(new BorderLayout(10, 10));
	    getContentPane().add(img, BorderLayout.CENTER);
	    setTitle("Gestion d'inscription de doctorants");
	    //setSize(500, 250);
	    pack();
	    setSize(900,600);
		this.setLocationRelativeTo(null);

	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	  } 

	  public void actionPerformed(ActionEvent e)
	  { 
		

	  if(e.getSource() == quit) {
		 dispose(); //quit and close the window
		 new FrameBuilder().GetLoginFrame();// display the login window
	  }
	  if(e.getSource() == condidat)
	    {  
	    	new Condidature(doctorant); //description de la condidature
	    }
	     if(e.getSource() == etat)
	         new Etat(doctorant);  //réponse sur la condidature
	     if(e.getSource()==charger) {
	    	 dispose();
	    	 new Gestiondoctorant(doctorant);
	     }
	    
	    
	     if(e.getSource() == about)
	      JOptionPane.showMessageDialog(this,
	                                    new JLabel(new ImageIcon("objectifs.jpg")),
	                                    "À propos...",
	                                    JOptionPane.INFORMATION_MESSAGE);
	  } // Fin de la méthode actionPerformed

	 
	  
}
