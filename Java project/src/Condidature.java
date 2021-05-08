import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Condidature extends JFrame implements ActionListener {
	private JLabel lblFct = new JLabel("Votre condidature:");
	  private JTextArea resultats = new JTextArea();
	  private JScrollPane scroll = new JScrollPane(resultats);
	  private JButton btnOk = new JButton("Terminé");
     Account ac=new Account();
	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

	  public Condidature(Account ac)
	  {
		  //ac is the private current account that is passed  within the class PanelBuilder==>Gestiondoctorant after logging
		 this.ac=ac;
		    resultats.setFont(new Font("",Font.BOLD,13));

	    panInfos.add(lblFct);
	  

	    btnOk.addActionListener(this);
	    panBtn.add(btnOk);

	    resultats.setEditable(false);

	    panMain.add(panInfos, BorderLayout.NORTH);
	    panMain.add(scroll, BorderLayout.CENTER);
	    panMain.add(panBtn, BorderLayout.SOUTH);

	    resultats.setText("Recherche des resultats..");
	      resultats.update(resultats.getGraphics());
	      afficheResult(ac);
	    getContentPane().add(panMain);
	    setTitle("Votre condidature");
	    setSize(400,380);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  }

	  // Méthode actionPerformed
	  public void actionPerformed(ActionEvent e)
	  {
	    
	     if(e.getSource() == btnOk)
	    {
	      dispose();
	    }
	  } // Fin de la méthode actionPerformed

	 
	 //the same
	  private void afficheResult(Account account)
	  {
	    String reponse;
	    reponse =  "\n" +"--------------------------------------------\n"+
	    		"Nom:  " +account.getName() + "\n"+"Email:  "
	            + account.getEmail()+"\nCIN:  "+account.getCin()+ "\nNote du License:  "+account.getnotelicense()+"\nNote du Master:  "
	    		+account.getnotemaster()+ "\n"+"Laboratoire:  "+account.getDomain() +"\n"+ "Thématique:  "+account.getSujet()
	        ;		 
	    resultats.append(reponse);
	    resultats.update(resultats.getGraphics());
	  }
}
