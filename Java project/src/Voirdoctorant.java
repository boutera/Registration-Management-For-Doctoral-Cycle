import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
import javax.swing.*;
public class Voirdoctorant extends JFrame implements ActionListener{
	 private  String DOMAIN[] = new String[100];

	  // Composantes graphiques
	  private JLabel lblFct = new JLabel("domaine :");
	  private JComboBox listdomain ;
	  private JButton btnSearch = new JButton("Rechercher");
	  private JTextArea resultats = new JTextArea();
	  //J1EditorPane resultats = new JEditorPane("text/html", "");
	  private JScrollPane scroll = new JScrollPane(resultats);
	  private JButton btnOk = new JButton("Terminé");
	  private JButton supprimer=new JButton("Supprimer");
	  private Vector d=new Vector();
	  private Vector vect=new Vector();
	  private Vector suppv=new Vector();
	   private File f = new File("accounts.txt");

	  private File domainfile = new File("domain.txt");

	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

	  public Voirdoctorant()
	  {
		    resultats.setFont(new Font("",Font.BOLD,13));

		  displaydomain();
			 DOMAIN=new String[d.size()];
			 for(int i = 0; i < d.size(); i++)
			    {
			      DOMAIN[i] = (String)d.get(i);
			      
			    }			
		listdomain = new JComboBox(DOMAIN);
	    panInfos.add(lblFct);
	    panInfos.add(listdomain);
	    btnSearch.addActionListener(this);
	    panInfos.add(btnSearch);
	    
	    supprimer.addActionListener(this);
	    panBtn.add(supprimer);
	    btnOk.addActionListener(this);
	    panBtn.add(btnOk);

	    resultats.setEditable(false);

	    panMain.add(panInfos, BorderLayout.NORTH);
	    panMain.add(scroll, BorderLayout.CENTER);
	    panMain.add(panBtn, BorderLayout.SOUTH);
	    supprimer.setEnabled(false);
	    getContentPane().add(panMain);
	    setTitle("Affichages des Doctorants Par Domaine");
	    setSize(450, 400);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  }

	  // Méthode actionPerformed
	  public void actionPerformed(ActionEvent e)
	  {
	    if(e.getSource() == btnSearch)
	    {
	    	resultats.setText("Recherche des doctorants ayant comme laboratoire :" + DOMAIN[listdomain.getSelectedIndex()] + "...");
	      resultats.update(resultats.getGraphics());
	    	vect=searchAccount(DOMAIN[listdomain.getSelectedIndex()]);
	    	for(int i=0;i<vect.size();i++) {
	    		Account a=(Account)vect.get(i);
	    		afficheResult(a);
	    	}
	    	if(suppv.size()==0) {
	    		supprimer.setEnabled(false);
	    		resultats.append("\nrien encore..");
	    	}
	    	else 
	    		supprimer.setEnabled(true);

	    }
	    else if(e.getSource() == btnOk)
	    {
	      dispose();
	    }
	    else if(e.getSource()==supprimer) {
	    	 int confirm;
		      confirm = JOptionPane.showConfirmDialog(this,
		          "are you sure you want to delete this account ?", "delete",
		          JOptionPane.YES_NO_OPTION);
		      if(confirm == JOptionPane.YES_OPTION) {
	    	for(int i=0;i<suppv.size();i++) {
				  removeLineFromFile("accounts.txt",(String)suppv.get(i)); 
				 
	    	}
	    	 dispose();
	    	  new Voirdoctorant();
	    	
		      }
		      else {
		    	  dispose();
		    	  new Voirdoctorant();

		      }
	    }
	  } // Fin de la méthode actionPerformed

	 
	  private void afficherResult(Vector empl)
	  {

	   
	  } // Fin
	  
	  //afficher toutes les laboratoires pour etre affiché dans le JComboBox listdomain
	  public Vector displaydomain() {
		    Scanner s = null;
		    try {
		        s = new Scanner(domainfile);
		        //String []d=new String[100];
		        //Loop through file line by line
		        while (s.hasNextLine()) {
		        	
		        	 String[] account = s.nextLine().split(",");
		        	 if(account[2].equals("0"))
		               d.addElement(account[1]);
		    	
		        }
		        s.close();
		        //Close the scanner
		       
		        return d;
		     
		    } catch (FileNotFoundException e) {
		        //Display failed to search file dialog box.
		        JOptionPane.showMessageDialog(null, "Failed to get domain values!", "File Not Found", JOptionPane.WARNING_MESSAGE);
		        return null;
		    }
		}
	  //chercher le compte du fichier "accounts.txt" ayant comme laboratoire (txdomain)
	  public Vector searchAccount(String txtdomain ) {
	        Scanner s = null;
	        Vector v=new Vector();
	        String st;
	       // v=null;
	        try {
	            s = new Scanner(f);
	            while (s.hasNextLine()) {
	                String[] account = s.nextLine().split(",");
	                st=account[0]+","+account[1]+","+account[2]+","+account[3]+","+account[4]+","+account[5]+","+account[6];
	                if (account[4].equals(txtdomain) && account[6].equals("1")) {
	                	suppv.addElement(st);

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
	  //afficher les resultats de recherche de compte
	  private void afficheResult(Account account)
	  {
	    String reponse;
	    reponse =  "\n" +"-----------------------------------------------\n"+
	    		"Nom:  " +account.getName() + "\n"+"Email:  "
	            + account.getEmail() +"\nCIN:  "+account.getCin()+ "\nNote du License:  "+account.getnotelicense()+"\nNote du Master:  "
	    		+account.getnotemaster()+ "\n"+"Laboratoire:  "+account.getDomain()+"\n"+ "Thématique:  "+account.getSujet()
	        ;	   
	    resultats.append(reponse);
	    resultats.update(resultats.getGraphics());
	  }
	  //supprimer la ligne du fichier selectionné
	  public void removeLineFromFile(String file, String lineToRemove) {

		    try {

		      File inFile = new File(file);

		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }

		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		      String line = null;

		      //Read from the original file and write to the new
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {

		        if (!line.trim().equals(lineToRemove)) {

		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();

		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }

		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");

		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  }
	  
}

