import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gestionadmin extends JFrame implements ActionListener
{


  private JMenuBar menubar = new JMenuBar();

  private JMenu fichier = new JMenu("Fichier");
  private JMenuItem charger = new JMenuItem("Actualiser");
  private JMenuItem quit = new JMenuItem("Se déconnecter");

  private JMenu gestion = new JMenu("Laboratoire");
  private JMenuItem ajout = new JMenuItem("Ajouter un laboratoire");
  private JMenuItem suppression = new JMenuItem("Supprimer un laboratoire");

  private JMenu doctorants = new JMenu("Doctorants");
  private JMenuItem cartn = new JMenuItem("Voir doctorant par CIN");
  private JMenuItem dom = new JMenuItem("Voir doctorant par laboratoire");
  private JMenuItem  tout = new JMenuItem("Voir tous les doctorants");
  private Vector vect=new Vector();

  private JPanel pan = new JPanel();

  private JMenu recherche = new JMenu();
  //private JMenuItem demande = new JMenuItem("Voir toutes les demandes");
  private JMenuItem demande[]=new JMenuItem[100];

  private JMenu aide = new JMenu("Aide");
  private JMenuItem about = new JMenuItem("À propos...");

  private JLabel image = new JLabel(new ImageIcon("images/ehtpgestion.jpg"));

  private File f = new File("accounts.txt");
  //File with the words of invalid passwords
  private File bp = new File("dictbadpass.txt");
  
  
  ImageIcon j1=new ImageIcon("images/book1.jpg");
  JLabel img=new JLabel(j1);
  
  ImageIcon j=new ImageIcon("images/welcome.jpg");
  JLabel w=new JLabel(j);
  
  public Gestionadmin()
  { 
	 
    charger.addActionListener(this);
    fichier.add(charger);
    fichier.addSeparator();
    quit.addActionListener(this);
    fichier.add(quit);

    ajout.addActionListener(this);
    gestion.add(ajout);
    suppression.addActionListener(this);
    gestion.add(suppression);

    cartn.addActionListener(this);
    doctorants.add(cartn);
    dom.addActionListener(this);
    doctorants.add(dom);
    tout.addActionListener(this);
    doctorants.add(tout);
    
    vect=getdemande();
    recherche = new JMenu("Demandes d'inscription ("+vect.size()+")");
	for(int i=0;i<vect.size();i++) {
		Account a=(Account)vect.get(i);
		demande[i]=new JMenuItem(a.getEmail());
		demande[i].setPreferredSize(new Dimension(162,20));
		    demande[i].addActionListener(this);
		    recherche.add(demande[i]);		    
	}
    
	 pan.setSize(900,550);
	  img.setBounds(0, 0, 900, 500);

	  w.setBounds(145, 220, 600, 60);
	 
	 img.add(w,BorderLayout.CENTER);
	
    about.addActionListener(this);
    aide.add(about);

    menubar.add(fichier);
    menubar.add(gestion);
    menubar.add(doctorants);
    menubar.add(recherche);
    menubar.add(aide);

    setJMenuBar(menubar); //menu principal
    
    pan.setLayout(new BorderLayout(10, 10));
    pan.add(img, BorderLayout.CENTER);   //image de fond
    this.setContentPane(pan);
    setTitle("Gestion d'inscription de doctorants");
    pack();
    setSize(900,600);
	this.setLocationRelativeTo(null);
    
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  } 

  public void actionPerformed(ActionEvent e)
  { 
	

  if(e.getSource() == quit) {
	 dispose();     //close this window
	 new FrameBuilder().GetLoginFrame();  //retourner au login window
  }
  if(e.getSource() == ajout)  //ajouter un laboratoire  +thématiques
    {
    	new Ajoutdomain();
    }
     if(e.getSource() == suppression)  //supprimer le laboratoire+thematiques
         new Suppdomain();
     if(e.getSource()==charger) {   //actualiser
    	 dispose();
    	 new Gestionadmin();
     }
    if(e.getSource() == cartn)    //voir le doctorant par cin deja acceptées
    	new VoirdoctorantCIN();
     if(e.getSource() == dom)  //voir le doctorant par laboratoire deja acceptées
         new Voirdoctorant();

     if(e.getSource() == tout)  //voir tous les doctorants deja acceptés
    	  new Voirtout();
     for(int i=0;i<vect.size();i++) {
     if(e.getSource()==demande[i])   //affiche le window des demandes 
    	 new Demande(((Account)vect.get(i)).getCin());
     } 
     if(e.getSource() == about)   //aide 
      JOptionPane.showMessageDialog(this,
                                   "A propos..",
                                    "À propos...",
                                    JOptionPane.INFORMATION_MESSAGE);
  } // Fin de la méthode actionPerformed

  //retourner l'ensemble des nouvelles demandes non encore taitées  d'inscription des condidats 
   public Vector getdemande() {
	    Scanner s = null;
        Vector v=new Vector();
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] account = s.nextLine().split(",");
                //account[6] correspond à l'état de demande
                //0 ==> non encore traitée
                //1 ==>accepté
                //-1==> refusé
                if(account[6].equals("0")) {
                	Account ac=new Account(account[0],account[1],account[2],account[3],account[4],account[5],account[7],account[8]) ;
                	//ajouter le compye crée au vecteur v
                	v.addElement(ac);
                	//Account(String name, String email, String pass,String cin, String dom,String sjt) 
                } 
            }
            s.close();

            return v;
            //Display user not found dialog box.
        } catch (FileNotFoundException e) {
            //Display failed to search file dialog box.
            JOptionPane.showMessageDialog(null, "Failed to Search Account from the File!", "File Not Found", JOptionPane.WARNING_MESSAGE);
            return null;
        }
   }
  
}