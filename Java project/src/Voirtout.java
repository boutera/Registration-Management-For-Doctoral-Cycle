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
import javax.swing.JTextField;

public class Voirtout extends JFrame implements ActionListener {

	  // Composantes graphiques
	  private JLabel lblFct = new JLabel("Tous les doctorants :");
	  private JTextArea resultats = new JTextArea();
	  private JScrollPane scroll = new JScrollPane(resultats);
	  private JButton btnOk = new JButton("Terminé");
	  private Vector d=new Vector();
	  private Vector vect=new Vector();
	   private File f = new File("accounts.txt");

	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

	  public Voirtout()
	  {
		  
		 
		    resultats.setFont(new Font("",Font.BOLD,13));

	    panInfos.add(lblFct);
	  

	    btnOk.addActionListener(this);
	    panBtn.add(btnOk);

	    resultats.setEditable(false);

	    panMain.add(panInfos, BorderLayout.NORTH);
	    panMain.add(scroll, BorderLayout.CENTER);
	    panMain.add(panBtn, BorderLayout.SOUTH);

	    resultats.setText("Recherche de tous les doctorants...");
	      resultats.update(resultats.getGraphics());
	    	vect=searchAccount();
	    	for(int i=0;i<vect.size();i++) {
	    		Account a=(Account)vect.get(i);
	    		afficheResult(a);
	    	}
	    getContentPane().add(panMain);
	    setTitle("Affichages de tous les doctorants");
	    setSize(450,500);
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

	 
	  public Vector searchAccount( ) {
	        Scanner s = null;
	        Vector v=new Vector();
	       // v=null;
	        try {
	            s = new Scanner(f);
	            while (s.hasNextLine()) {
	                String[] account = s.nextLine().split(",");
	                if(account[6].equals("1")) {
	                	Account ac=new Account(account[0],account[1],account[2],account[3],account[4],account[5],account[7],account[8]) ;
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
