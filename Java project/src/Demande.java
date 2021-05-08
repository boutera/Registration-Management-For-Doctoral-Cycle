import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Demande extends JFrame implements ActionListener {
      private String cin;


	  // Composantes graphiques
	  private JLabel lblFct = new JLabel();
	  private JTextArea resultats = new JTextArea();
	  private JScrollPane scroll = new JScrollPane(resultats);
	  private JButton accepter = new JButton("Accepter");

	  private JButton btnOk = new JButton("Terminé");
	  private JButton supprimer=new JButton("Supprimer");
	  private Vector suppv=new Vector();

	  private Vector d=new Vector();
	  private Vector vect=new Vector();
	  
	   private File f = new File("accounts.txt");

	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

	  public Demande(String cin)
	  {
        this.cin=cin;
        lblFct = new JLabel("demande'inscription de condidat de cin  :"+cin);
	    panInfos.add(lblFct);
	    resultats.setFont(new Font("",Font.BOLD,13));

	    panBtn.add(accepter);
	    accepter.addActionListener(this);
	    supprimer.addActionListener(this);
	    panBtn.add(supprimer);
	    btnOk.addActionListener(this);
	    panBtn.add(btnOk);

	    resultats.setEditable(false);

	    panMain.add(panInfos, BorderLayout.NORTH);
	    panMain.add(scroll, BorderLayout.CENTER);
	    panMain.add(panBtn, BorderLayout.SOUTH);

	      resultats.update(resultats.getGraphics());  //actualiser les resultats graphique lors de l'ajout de text
	    	vect=searchAccount(cin);   //chercher le(s) compte(s) ayant comme cin (cin) (mais en général seulement un compte +cin unique)
	    	for(int i=0;i<vect.size();i++) {
	    		Account a=(Account)vect.get(i);    
	    		afficheResult(a);  //la méthode affiche les résultats de recherche 
	    	}
	    getContentPane().add(panMain);
	    setTitle("Voir la condidature de doctorant");
	    setSize(350,350);
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
	     //accepter la demande
	     else if(e.getSource()==accepter) {
	    	 for(int i=0;i<suppv.size();i++) {
				  updateFile(1,"accounts.txt",(String)suppv.get(i));  //update file met la 7ieme composant da la ligne en "1"(account[6]="1")
	    	}
	    	 dispose();
	    	 new Gestionadmin();  //actualiser

	     }
	     //supprimer la demande ou la refuser 
	     else if(e.getSource()==supprimer) {
	    	 int confirm;
		      confirm = JOptionPane.showConfirmDialog(this,
		          "are you sure you want to delete this account ?", "delete",
		          JOptionPane.YES_NO_OPTION);
		      if(confirm == JOptionPane.YES_OPTION) {
	    	for(int i=0;i<suppv.size();i++) {
	    		updateFile(-1,"accounts.txt",(String)suppv.get(i)); 
	    	}
	    	 dispose();
	    	 new Gestionadmin();
		      }
		      else {
		    	  dispose();
		      }
	    }
	  } // Fin de la méthode actionPerformed

	 //the same method as always
	  public Vector searchAccount( String ncin ) {
	        Scanner s = null;
	        Vector v=new Vector();
	        String st;
	       // v=null;
	        try {
	            s = new Scanner(f);
	            
	            while (s.hasNextLine()) {
	                String[] account = s.nextLine().split(",");
	                st=account[0]+","+account[1]+","+account[2]+","+account[3]+","+account[4]+","+account[5]+","+account[6]+","+account[7]+","+account[8];

	                if(account[6].equals("0") && account[3].equals(ncin)) {
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
	  //the same(deja vu)
	  private void afficheResult(Account account)
	  {
		  
	    String reponse;
	    reponse =  "\n"+
	    "Nom:  " +account.getName() + "\n"+"Email:  "
	            + account.getEmail()+"\nCIN:  "+account.getCin()+ "\nNote du License:  "+account.getnotelicense()+"\nNote du Master:  "+account.getnotemaster()+ "\n"+"Laboratoire:  "+account.getDomain() +"\n"+ "Thématique:  "+account.getSujet()
	        ;
	    resultats.append(reponse);
	    resultats.update(resultats.getGraphics());
	  }
	  //the same
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
	  
	  
	  //this method  takes as another argument (int i) which may be equal to 1 or 0 or -1 
	//0 ==> demande non encore traitée
      //1 ==>demande accepté
      //-1==>demande refusé	  
	  //so it updates the file and sets the 7th composent to (i) to the chosen line of the file
	  public void updateFile(int i,String file, String lineToRemove) {
		  String st;
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
		        else {
		        	  String[] account = line.split(",");
		        	  if(i==1)
		        	  account[6]="1";
		        	  else 
		        		  account[6]="-1";
		        	  line=account[0]+","+account[1]+","+account[2]+","+account[3]+","+account[4]+","+account[5]+","+account[6]+","+account[7]+","+account[8];

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
