import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;  
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Suppdomain extends JFrame implements ActionListener {
    private File domainfile = new File("domain.txt");
	private Vector d=new Vector();
	
	  
	  private boolean suppr=true;;
	    public boolean issucces=false;
private Vector nb=new Vector();
	  // Composantes graphiques
	  private JLabel lblNAS = new JLabel("Cocher les laboratoires que vous voulez supprimer:");
	 //private JTextArea resultats = new JTextArea(20, 20);
	  private JPanel resultats = new JPanel();
	  //private JScrollPane scroll = new JScrollPane(resultats);
	  private JButton btnSupprimer = new JButton("Supprimer");
	  private JButton btnOk = new JButton("Terminé");
      public JCheckBox[] suppcheckbox=new JCheckBox[100];
      
	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new BorderLayout(10, 10));
	  //JScrollPane scroll = new JScrollPane(resultats);




	  // Constructeur .
     public Suppdomain() {
    	    panInfos.add(lblNAS);
    		panMain.setBounds(0,50,200,300);
    		resultats.setBounds(0,50,300,470);
    	    d=displaydomain();
    	    for(int i=0;i<d.size();i++) {
    	    	suppcheckbox[i]=new JCheckBox((String)d.get(i));
    	    	suppcheckbox[i].addActionListener(this);
    	    	suppcheckbox[i].setBackground(Color.white);
    	    	 suppcheckbox[i].setPreferredSize(new Dimension(200,20));
    	       
    	    	resultats.add(suppcheckbox[i]);
    	    }
    	    
    	   
    		
     	    btnSupprimer.addActionListener(this);
    	    btnSupprimer.setEnabled(false);
    	    if(suppr)
    	      panBtn.add(btnSupprimer);
    	    btnOk.addActionListener(this);
    	    panBtn.add(btnOk);

    	    panMain.add(panInfos, BorderLayout.NORTH);
    	    panMain.add(resultats, BorderLayout.CENTER);
    	    panMain.add(panBtn, BorderLayout.SOUTH);

    	    getContentPane().add(panMain);
    	    setTitle("Supprimer laboratoire");
    	    setSize(400, 470);
		    this.setLocationRelativeTo(null);

    	    setVisible(true);
    	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     }

	  // Méthode actionPerformed
	  public void actionPerformed(ActionEvent e)
	  {   int som=0;
		  for(int i=0;i<d.size();i++) {
			  if(suppcheckbox[i].isSelected()) { issucces=true;break;		  
		  }
            else {
				  som++;
			  }
		  }
		  if(som==d.size()) issucces=false;
	      if(issucces) btnSupprimer.setEnabled(true);
	      else  btnSupprimer.setEnabled(false);
	   if(e.getSource() == btnSupprimer) // Confirmation avant de supprimer
	    {
	      int confirm;
	      confirm = JOptionPane.showConfirmDialog(this,
	          "are you sure you want to delete this domain ?", "delete",
	          JOptionPane.YES_NO_OPTION);
	      if(confirm == JOptionPane.YES_OPTION)
	      {  btnSupprimer.setEnabled(false);
	    	  for(int i=0;i<d.size();i++) {
				  if(suppcheckbox[i].isSelected()) { 
					  String s=(String)nb.get(i)+","+suppcheckbox[i].getText()+",0";
                       System.out.println(s);
					  removeLineFromFile(domainfile,s); //supprimerle laboratoire du fichier
				  }
	        
	      }
	    	  dispose();
			  new Suppdomain();
	    }
	      else {
	    	  dispose();
			  new Suppdomain();
	      }
	    }
	    else if(e.getSource() == btnOk)
	    {
	      dispose();
	    }
	   // Fin de la méthode actionPerformed
	  }
	  public Vector displaydomain() {
		    Scanner s = null;
		    try {
		        s = new Scanner(domainfile);
		        //String []d=new String[100];
		        //Loop through file line by line
		        while (s.hasNextLine()) {
		        	 String[] account = s.nextLine().split(",");
		        	 if(account[2].equals("0")) {
		               d.addElement(account[1]);
		               nb.addElement(account[0]);
		             
		        }}
		        s.close();
		        //Close the scanner
		       
		        return d;
		     
		    } catch (FileNotFoundException e) {
		        //Display failed to search file dialog box.
		        JOptionPane.showMessageDialog(null, "Failed to get domain values!", "File Not Found", JOptionPane.WARNING_MESSAGE);
		        return null;
		    }
		}

	 
	  public void removeLineFromFile(File file, String lineToRemove) {

		    try {

		      File inFile = file;

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
		        	  String[] dom = line.split(",");
		        	
		        	  dom[2]="-1";
		        	  line=dom[0]+","+dom[1]+","+dom[2];
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
