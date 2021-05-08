import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;
public class PanelBuilder2 {
	//File with the information for each created account
    protected File f = new File("accounts.txt");
    //File with the words of invalid passwords
    protected File bp = new File("dictbadpass.txt");
    //fichier contenant  les laboratoireset leurs thématiques
    private File domainfile = new File("domain.txt");
    //list des thématiques
    private JComboBox listsujet = new JComboBox	();
    //le tableux des labo
	private  String DOMAIN[]=new String[0];
	//le tableaux des thématiques
	private  String sujet[]=new String[0];
	
	//objet de type class
	Button  buttons=new Button();

	private Vector d=new Vector();
	
public JPanel GetSignup()
{
	 displaydomain();
	 DOMAIN=new String[d.size()];
	 for(int i = 0; i < d.size(); i++)
	    {
	      DOMAIN[i] = (String)d.get(i);
	      
	    }
	 
	
	//le background des inputs 
	JLabel l1=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l2=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l3=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l4=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l5=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l6=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l7=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l8=new JLabel(new ImageIcon("images/tpb.jpg"));
	JLabel l9=new JLabel(new ImageIcon("images/tpb.jpg"));

   
	
	Border blackline = BorderFactory.createLineBorder(Color.black);
	JPanel panel=new JPanel();
	final JPanel panel2=new JPanel();
	//panel.setBorder(blackline);
	panel.setBounds(40, 22, 850, 550);
	panel.setBackground(new Color(0.1f, 0.1f, 0.1f,0.0f));
	JButton back=buttons.GetBack();
	panel.setLayout(null);
	back.setBounds(10, 0, 100, 50);
	
	JLabel newuser=new JLabel("SIGN UP");
	newuser.setFont(new Font("", Font.PLAIN, 24));
	newuser.setForeground(Color.white);
	newuser.setBounds(10, 10, 200,30 );
	
//champ nom complet
	
	JTextField Nametf =new JTextField();
	Nametf.setBounds(0,0,200,30);
	Nametf.setBorder(null);
	Nametf.setOpaque(false);;

	Nametf.setForeground(Color.WHITE);
	JLabel Name=new JLabel("Full Name :");
	Name.setBounds(20, 45, 80, 30);
	Name.setFont(new Font("", Font.BOLD, 13));
	Name.setForeground(Color.white);
	l3.add(Nametf);
	l3.setBounds(20, 70, 200, 30);
	
	//champ email
	JTextField emailtf =new JTextField();
	emailtf.setBounds(0,0,200,30);
	emailtf.setBorder(null);
	emailtf.setOpaque(false);;

	emailtf.setForeground(Color.WHITE);
	JLabel email=new JLabel("Email :");
	email.setBounds(20,105, 80, 30);
	email.setFont(new Font("", Font.BOLD, 13));
	email.setForeground(Color.white);
	l4.add(emailtf);
	l4.setBounds(20, 130, 200, 30);

	
	JButton save=buttons.GetSave();
	save.setBounds(300, 380, 83, 42);
	save.setBorder(null);
	save.setBackground(new Color(1.0f, 0.2f, 0.2f, 0.6f));

	panel2.add(save);
	

	//champ de password
	final JPasswordField Passtf=new JPasswordField();

	Passtf.setBounds(0, 0, 200, 30);
	Passtf.setBorder(null);
	Passtf.setOpaque(false);;
	Passtf.setForeground(Color.white);
	JLabel Pass=new JLabel("Password :");
	Pass.setBounds(20, 165, 80, 30);
	Pass.setFont(new Font("", Font.BOLD, 13));
	Pass.setForeground(Color.white);
	l2.add(Passtf);
	l2.setBounds(20, 190, 200, 30);
	
	//champ de password confirm
	final JPasswordField Passctf=new JPasswordField();
	JLabel Passc=new JLabel("Confirm Password :");
	Passctf.setBorder(null);
	Passctf.setOpaque(false);;

	Passc.setBounds(20, 225, 140, 30);
	Passc.setForeground(Color.white);
	Passc.setFont(new Font("", Font.BOLD, 13));
	Passctf.setBounds(0, 0, 200, 30);
	Passctf.setForeground(Color.white);

	l1.add(Passctf);
	l1.setBounds(20, 250, 200, 30);
	
	//champ de la note de license 
	 JTextField cintf = new JTextField(new LimiteDoc(7, false), "", 7);
	cintf.setBounds(0,0,200,30);
	cintf.setBorder(null);
	cintf.setOpaque(false);;

	cintf.setForeground(Color.WHITE);
	JLabel cin=new JLabel("CIN :");
	cin.setBounds(20, 285, 80, 30);
	cin.setFont(new Font("", Font.BOLD, 13));
	cin.setForeground(Color.white);
	l5.add(cintf);
	l5.setBounds(20, 310, 200, 30);
	
	
	
	//champ de la note de master
	JTextField mastertf =new JTextField();
	mastertf.setBounds(0,0,200,30);
	mastertf.setBorder(null);
	mastertf.setOpaque(false);;

	mastertf.setForeground(Color.WHITE);
	JLabel master=new JLabel("Note de Master :");
	master.setBounds(500, 105, 200, 30);
	master.setFont(new Font("", Font.BOLD, 13));
	master.setForeground(Color.white);
	l8.add(mastertf);
	l8.setBounds(500, 130, 200, 30);
	//champ de liste des laboratoires
	     JComboBox listdomain = new JComboBox(DOMAIN);
	     listdomain.setBounds(0,0,200,30);
	     listdomain.setBorder(null);
	     listdomain.setOpaque(false);;

	     listdomain.setForeground(Color.WHITE);
	     listdomain.setBackground(new Color(172, 50, 50));
		JLabel namedomain=new JLabel("Laboratoire :");
		namedomain.setBounds(500,165, 200, 30);
		namedomain.setFont(new Font("", Font.BOLD, 13));
		namedomain.setForeground(Color.white);
		l6.add(listdomain);
		l6.setBounds(500, 190, 200, 30);
	
//	105 130	-------------------------
Vector initial=new Vector();
String st=displayl();
initial=displaysujet(st);
sujet=new String[initial.size()];
for(int i = 0; i < initial.size(); i++)
  {
	
    sujet[i] = (String)initial.get(i);


  }
        //champ de la liste des thématiques proposées
	      listsujet = new JComboBox(sujet);
	     listsujet.setBounds(0,0,200,30);

	     listsujet.setBorder(null);
	     listsujet.setOpaque(false);;

	     listsujet.setForeground(Color.WHITE);
	     listsujet.setBackground(new Color(172, 50, 50));
		JLabel namesujet=new JLabel("Thématique :");
		namesujet.setBounds(500,225, 200, 30);
		namesujet.setFont(new Font("", Font.BOLD, 13));
		namesujet.setForeground(Color.white);
		l7.add(listsujet);
		l7.setBounds(500, 250, 200, 30);

		
		//champ de note de license
		JTextField licensetf =new JTextField();
		licensetf.setBounds(0,0,200,30);
		licensetf.setBorder(null);
		licensetf.setOpaque(false);;

		licensetf.setForeground(Color.WHITE);
		JLabel license=new JLabel("Note de License :");
		license.setBounds(500, 45, 200, 30);
		license.setFont(new Font("", Font.BOLD, 13));
		license.setForeground(Color.white);
		l9.add(licensetf);
		l9.setBounds(500, 70, 200, 30);
		
	panel2.setBackground(new Color(172, 50, 50));
	
	panel2.setBounds(10, 60, 800, 530);
	panel2.setBorder(blackline);
	panel2.setLayout(null);
	panel2.add(newuser);
	panel2.add(Name);
	panel2.add(l3);
	panel2.add(email);
	panel2.add(l4);
	panel2.add(Pass);
	panel2.add(l2);
	panel2.add(Passc);
	panel2.add(l1);
	panel2.add(cin);
	panel2.add(l5);
	panel2.add(namedomain);
	panel2.add(l6);
	panel2.add(namesujet);
	panel2.add(l7);
	panel2.add(master);
	panel2.add(l8);
	panel2.add(license);
	panel2.add(l9);
	
	
	panel.add(panel2);

	panel.add(back);
	panel2.setBackground(new Color(1.0f, 0.2f, 0.2f, 0.6f));

	panel2.setVisible(true);
	;
	
	ActionListener Backing=new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			FrameBuilder f1=new FrameBuilder();
			//hide the signup panel and show the login panel 
			f1.SignupHide();
		}	
	};
	back.addActionListener(Backing);
	
	
	ActionListener saving=new ActionListener() {
		public void actionPerformed(ActionEvent ae)
		{
			 if (validation(email.getText(), f) == true) {
		            //validat password
		            if (validation(Passtf.getText(), bp) == true) {
		                //Validate if fields are filled
		                if (!Nametf.getText().isEmpty() && !Passtf.getText().isEmpty() && !Passctf.getText().isEmpty()  && !emailtf.getText().isEmpty()  && !cintf.getText().isEmpty() && !mastertf.getText().isEmpty()  && !licensetf.getText().isEmpty()) {
		                	//verify if the confirmed password matches the first entered 
		                	if(Passtf.getText().equals(Passctf.getText()))
		                	{
			                    //Create an account with the new information

		                    Account create = new Account(Nametf.getText(), emailtf.getText(), encryption(Passtf.getText()) ,cintf.getText(),DOMAIN[listdomain.getSelectedIndex()],sujet[listsujet.getSelectedIndex()],licensetf.getText(),mastertf.getText());
		                    //Save the account to the file
		                    saveAccount(create);
		                	}
		                	else {
			                    JOptionPane.showMessageDialog(null, "confirmed password doesn't match", "error password", JOptionPane.ERROR_MESSAGE);

		                	}
		                } else {
		                    //Display the empty fields dialog box.
		                    JOptionPane.showMessageDialog(null, "Empty Fields Found", "Empty Fields", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
		}
	};
	save.addActionListener(saving);
	
    ActionListener displaythema=new ActionListener() {
    	public void actionPerformed(ActionEvent ae) {
              listsujet.removeAllItems(); //supprimer tout pour entrer les nouveaux items
              String o = (String)listdomain.getSelectedItem();
              o=getidparnom(o);
              Vector ve=new Vector(displaysujet(o));            
         	 sujet=new String[ve.size()];
              for(int i = 0; i < ve.size(); i++)
      	    {
      	      sujet[i] = (String)ve.get(i);

              listsujet.addItem(sujet[i]); //ajouter cet item crée r la liste des JComboBox listsujet
      	    }
    	}
    };
	listdomain.addActionListener(displaythema);

    
	return panel;
}
//verifies if the password entered doesn't belongs to the list of bad words "dictbadpass.txt"
public boolean validation(String field, File file) {
	
    Scanner s = null;
    String bad = null;
    try {
        s = new Scanner(file);
        //Loop through the file line by line
        while (s.hasNextLine()) {
            //Get bad username for username validation
            if (file == f) {
                //Split the line into array
                String[] account = s.nextLine().split(",");
                //Obtain the username
                bad = account[2];
            } //Get bad password for password validation
            else {
                //Obtian the bad password
                bad = s.nextLine();
            }
            //Check if the field equals the line in the field
            if (field.equals(bad)) {
                //Close the scanner
                s.close();
                //Display that the user must check credentials when there is a match dialog box.
                JOptionPane.showMessageDialog(null, "this password is too known,try another", "Error", JOptionPane.ERROR_MESSAGE);
                //Validation failed
                return false;
            }
        }
        //Close the scanner
        s.close();
        //Validation passed
        return true;
    } catch (FileNotFoundException e) {
        //Display failed to validate from the file dialog box.
        JOptionPane.showMessageDialog(null, "Failed to Validate from the File", "File Not Found", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
//this method encrypts the password using md5 
public String encryption(String pass) {
    //java helper class to perform encryption
    MessageDigest md = null;
    try {
        md = MessageDigest.getInstance("SHA-256");
        //give the helper function the password
        md.update(pass.getBytes());
        //perform the encryption
        byte byteData[] = md.digest();
        //To express the byte data as a hexadecimal number (the normal way)
        String sb1 = "";
        //Encrypt the password
        for (int i = 0; i < byteData.length; ++i) {
            sb1 += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb1;
    } catch (NoSuchAlgorithmException ex) {
        //Display the failed to encrypt password dialog box.
        JOptionPane.showMessageDialog(null, "Failed to Encrypt Password", "Encryption Failure", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
//save the account into a file "accounts.txt"
public void saveAccount(Account p) {
    PrintWriter pw = null;
    try {
        //Write the account's information to the file
        pw = new PrintWriter(new FileWriter(f, true));
        pw.println(p.getName() + "," + p.getEmail() + "," + p.getencryptedPass() +"," + p.getCin()+","+p.getDomain()+","+p.getSujet()+",0"+","+p.getnotelicense()+","+p.getnotemaster());
        pw.close();
        //Reset the text fields
        
        //Display account created dialog box.
        JOptionPane.showMessageDialog(null, "Account Created", "Success", JOptionPane.INFORMATION_MESSAGE);
        FrameBuilder f1=new FrameBuilder();
		f1.SignupHide();
    } catch (IOException e) {
        //Display the failed to store account dialog box.
        JOptionPane.showMessageDialog(null, "Failed to Store Account in the File!", "Failed to Write to File", JOptionPane.WARNING_MESSAGE);
    }
}
//this function returns a vector of all laboratories . 
public Vector displaydomain() {
    Scanner s = null;
    try {
        s = new Scanner(domainfile);
        //String []d=new String[100];
        //Loop through file line by line

        while (s.hasNextLine()) {
            String[] labo = s.nextLine().split(",");

        	if(labo[2].equals("0"))          
               d.addElement(labo[1]);       	
    	
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
//this method takes an id wich means the id of domainfile's line  as an argument to get the description(name) of laboratory
//of the line chosed
public Vector displaysujet(String id) {
    Scanner s = null;
    Vector vec=new Vector();
    try {
        s = new Scanner(domainfile);
        
        while (s.hasNextLine()) {
            String[] labo = s.nextLine().split(",");
        	if(labo[2].equals(id))          
               vec.addElement(labo[1]);       	
    	
        }
        s.close();
        //Close the scanner
       
        return vec;
     
    } catch (FileNotFoundException e) {
        //Display failed to search file dialog box.
        JOptionPane.showMessageDialog(null, "Failed to get domain values!", "File Not Found", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}
//method for displaying the first line of the file "domain.txt 
public String displayl() {
    Scanner s = null;
    String st;
    try {
        s = new Scanner(domainfile);
        st=s.nextLine().split(",")[0]; 
        s.close();
       return st;
     
    } catch (FileNotFoundException e) {
        return null;
    }
}
//this method is avalaible for gettinh the id of a line by name
public String getidparnom(String nom) {
	Scanner s = null;
    try {
        s = new Scanner(domainfile);
        //String []d=new String[100];
        //Loop through file line by line

        while (s.hasNextLine()) {
            String[] labo = s.nextLine().split(",");

        	if(labo[1].equals(nom))          
    	   return labo[0];
        }
        s.close();
       
     return null;
    } catch (FileNotFoundException e) {
        //Display failed to search file dialog box.
        JOptionPane.showMessageDialog(null, "Failed to get domain values!", "File Not Found", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}

}
