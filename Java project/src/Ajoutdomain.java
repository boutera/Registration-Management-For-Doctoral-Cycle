import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;


public class Ajoutdomain extends JFrame implements ActionListener {

	private File domain = new File("domain.txt");
	  //File with the words of invalid passwords
	  //private JTextField txtdomain = new JTextField(10);
	 

	  // JButton
	  private JButton btnOk = new JButton("Ajouter");
	  private JButton btnCancel = new JButton("Annuler");

	  // JPanel


	  // Contraintes

	  
	  private JLabel lblFct = new JLabel("Entrer le Larboatoire:");
	  private JTextField txtNAS =new JTextField();
	  private JLabel thema1 = new JLabel("Entrer le thematique 1:");
	  private JTextField txtthema1 = new JTextField();
	  
	  
	  private JLabel thema2 = new JLabel("Entrer le thématique 2 :");
	  private JTextField txtthema2 = new JTextField();
	  private JLabel thema3 = new JLabel("Entrer le thématique 3 :");
	  private JTextField txtthema3 = new JTextField();
	  private JLabel thema4 = new JLabel("Entrer le thématique 4 :");
	  private JTextField txtthema4 = new JTextField();
	  private JLabel thema5 = new JLabel("Entrer le thématique 5 :");
	  private JTextField txtthema5 = new JTextField();
	  //private JButton btnSearch = new JButton("Rechercher");
	 // private JTextArea resultats = new JTextArea();
	  //private JScrollPane scroll = new JScrollPane(resultats);
	  private GridBagConstraints constraints = new GridBagConstraints();

	  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  private JPanel panMain = new JPanel(new GridBagLayout());

	  // Constructeur
	  public Ajoutdomain()
	  {    
		  //les dimenstions et propriètés d'objet constraints
		  constraints.fill = GridBagConstraints.HORIZONTAL;
	        constraints.insets = new Insets(5, 5, 5, 5);
		     constraints.gridx = 0;
		    constraints.gridy = 0;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(lblFct, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 0;
		    constraints.weightx = 3;
		    constraints.gridwidth = 1;
		    panMain.add(txtNAS, constraints);

		    //on se déplace avec la dimension constante  (1,1)  pour les axes (x,y)
		    
		    constraints.gridx = 0;
		    constraints.gridy = 1;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(thema1, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 1;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(txtthema1, constraints);
		    
		    constraints.gridx = 0;
		    constraints.gridy = 2;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(thema2, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 2;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(txtthema2, constraints);

		    constraints.gridx = 0;
		    constraints.gridy = 3;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(thema3, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 3;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(txtthema3, constraints);
		    
		    constraints.gridx = 0;
		    constraints.gridy = 4;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(thema4, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 4;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(txtthema4, constraints);
		    
		    constraints.gridx = 0;
		    constraints.gridy = 5;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(thema5, constraints);

		    constraints.gridx = 1;
		    constraints.gridy = 5;
		    constraints.weightx = 1;
		    constraints.gridwidth = 1;
		    panMain.add(txtthema5, constraints);
		    
		    btnOk.addActionListener(this);
		    panBtn.add(btnOk);
		    btnCancel.addActionListener(this);
		    panBtn.add(btnCancel);

		    constraints.gridx = 0;
		    constraints.gridy = 6;
		    constraints.weightx = 1;
		    constraints.gridwidth = 2;
		    panMain.add(panBtn, constraints);


		    getContentPane().add(panMain);
		    setTitle("Ajouter un Laboratoire et ses thématiques");
		    setSize(420,320);
		    this.setLocationRelativeTo(null);//centre de l'ecran

		    setVisible(true);
		    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  }

	  // Méthode actionPerformed
	  public void actionPerformed(ActionEvent e)
	  {
		 //if le bouton ajouter est clické et le champ d"input n'est pas vide
	    if(e.getSource() == btnOk && txtNAS.getText().length()>0)
	    	
	    {  int nb,c;
	    if(countlines().equals(null)) {nb=0;c=1;}
	    else {
	    	 nb=Integer.parseInt(countlines());
	    	 c=nb+1;//le numero de la ligne ou on vas ajouter les nouvelles lignes
	    }
	    	if(txtthema1.getText().length()==0 && txtthema2.getText().length()==0 && txtthema3.getText().length()==0 && txtthema4.getText().length()==0 && txtthema5.getText().length()==0 )
	            JOptionPane.showMessageDialog(null, "Vous devez ajouter au moins une thématique", "Empty Fields", JOptionPane.INFORMATION_MESSAGE);
	    	else {
	    		PrintWriter pw = null;
	    	    try {
	    	        //Write the new added domain information to the file
	    	        pw = new PrintWriter(new FileWriter(domain, true));
	    	        pw.println(c+","+txtNAS.getText()+","+"0");
	    	        pw.close();
	    	        //add the new1 added (thematique) to the file "domain.txt"
	    	        if(txtthema1.getText().length()!=0 ) {
	    	        	  pw = new PrintWriter(new FileWriter(domain, true));
	  	    	        pw.println((c+1)+","+txtthema1.getText()+","+c);
	  	    	        pw.close();
	    	        }//ainsi de suite jusque 5
	    	        if(txtthema2.getText().length()!=0 ) {
	    	        	  pw = new PrintWriter(new FileWriter(domain, true));
	  	    	        pw.println((c+2)+","+txtthema2.getText()+","+c);
	  	    	        pw.close();
	    	        }
	    	        if(txtthema3.getText().length()!=0 ) {
	    	        	  pw = new PrintWriter(new FileWriter(domain, true));
	  	    	        pw.println((c+3)+","+txtthema3.getText()+","+c);
	  	    	        pw.close();
	    	        }
	    	        if(txtthema4.getText().length()!=0 ) {
	    	        	  pw = new PrintWriter(new FileWriter(domain, true));
	  	    	        pw.println((c+4)+","+txtthema4.getText()+","+c);
	  	    	        pw.close();
	    	        }
	    	        if(txtthema5.getText().length()!=0 ) {
	    	        	  pw = new PrintWriter(new FileWriter(domain, true));
	  	    	        pw.println((c+5)+","+txtthema5.getText()+","+c);
	  	    	        pw.close();
	    	        }
	    	        //Display laboratory created dialog box.
	    	        JOptionPane.showMessageDialog(null, "laboratory added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	        
	    	    } catch (IOException f) {
	    	        //Display the failed to store account dialog box.
	    	        JOptionPane.showMessageDialog(null, "Failed to Store laboratory in the File!", "Failed to Write to File", JOptionPane.WARNING_MESSAGE);
	    	    }
	      dispose();
	    	}
	    }
	    else if(e.getSource() == btnCancel)
	      dispose();
	  } // Fin de la méthode actionPerformed

	
//this method returns the number of lines in the file 'domain.txt" in a string form	  
public String countlines( ) {
    Scanner s = null;
   // v=null;
    try {
        s = new Scanner(domain);
        String i=null;

        while (s.hasNextLine()) {
        	 String[] account = s.nextLine().split(",");

        	i=account[0];

            } 
        
        s.close();

        return i;
    } catch (FileNotFoundException e) {
        //Display failed to search file dialog box.
        JOptionPane.showMessageDialog(null, "Failed to Search domain from the File!", "File Not Found", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}
}
