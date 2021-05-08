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

public class Etat extends JFrame implements ActionListener {
	  private JButton btnOk = new JButton("Terminé");
	  private JPanel resultats = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private File f = new File("accounts.txt");

	  private JLabel lblFct = new JLabel("Votre état est :"); 
	  private JLabel etat = new JLabel(); 

	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));
	  private Account acc=new Account();
	  public Etat(Account a) {
		  this.acc=a;
		  //ac is the private current account that is passed  within the class PanelBuilder==>Gestiondoctorant after logging
		  
		  panInfos.add(lblFct);
		  if(getetat(acc)==1)
			  etat.setText("Accepté");
		  else if(getetat(acc)==0)
			  etat.setText("En cours de traitement..");

		  else if(getetat(acc)==-1)
			  etat.setText("refusé");
resultats.add(etat);
            etat.setFont(new Font("",Font.BOLD,15));
		    btnOk.addActionListener(this);
		    panBtn.add(btnOk);
		    panMain.add(panInfos, BorderLayout.NORTH);
		    panMain.add(resultats, BorderLayout.CENTER);
		    panMain.add(panBtn, BorderLayout.SOUTH);
		    getContentPane().add(panMain);
		    setTitle("Etat de condidature");
		    setSize(300,200);
		    setVisible(true);
		    this.setLocationRelativeTo(null);
		    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  }
	  public void actionPerformed(ActionEvent e)
	  {
	    
	     if(e.getSource() == btnOk)
	    {
	      dispose();
	    }
	  } 
	  public int getetat(Account a) {
		    Scanner s = null;
		    String  st;
		    String to;
	        try {
	            s = new Scanner(f);
	            while (s.hasNextLine()) {
	                String[] account = s.nextLine().split(",");                    
	            	 if(account[1].equals(a.getEmail()) && account[2].equals(a.getencryptedPass())) {
	            		
	            		 if(account[6].equals("0")) {
                             return 0;	                	//Account(String name, String email, String pass,String cin, String dom,String sjt) 
	                } 
	                else if(account[6].equals("1")) return 1;
	                else if(account[6].equals("-1")) return -1;
	            	 }
	   
	            
	            }
	            s.close();

	            return 7;
	            //Display user not found dialog box.
	        } catch (FileNotFoundException e) {
	            //Display failed to search file dialog box.
	            JOptionPane.showMessageDialog(null, "Failed to Search Account from the File!", "File Not Found", JOptionPane.WARNING_MESSAGE);
	            return -2;
	        }
	   }
}
