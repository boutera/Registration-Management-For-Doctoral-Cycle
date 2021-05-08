import java.awt.*;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.*;



	public class PanelBuilder extends PanelBuilder2{
		  private  Account current;
	//get the default dimension of the actual JFrame 
	public Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	int x = (int) screensize.getWidth();
	int y = (int) screensize.getHeight();
	public static JTextField emailtf=new JTextField();   //filed of email
	public JLabel text=new JLabel("LOGIN");      
	public JLabel usnm=new JLabel("Email");
	 public JLabel pass=new JLabel("Password");
	public  JPanel panel = new JPanel();
	final JPasswordField passtf=new JPasswordField();   //field of password
	 
	public JPanel GetLogin()
	{
	
		JLabel l1=new JLabel(new ImageIcon("images/tpb.jpg"));
		JLabel l2=new JLabel(new ImageIcon("images/tpb.jpg"));
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Button buttons=new Button();
		//the login button
		final JButton login=buttons.GetLogin();
		panel.setLayout(null);
		JButton settings=buttons.GetSet();
		//the sign up button
		settings.setBounds(x/10-90, y/3-20+20, 77, 35);
		emailtf.setBorder(null);
		login.setBounds(x/10+10, y/3-20+20,77,35);
		passtf.setBorder(null);
		emailtf.setFont(new Font("", Font.PLAIN, 16));
		emailtf.setOpaque(false);
		emailtf.setForeground(Color.white);
		passtf.setForeground(Color.white);
		
		l1.setBounds(x/50, 90, 220, 30);
		l1.setLayout(null);
		emailtf.setBounds(0, 2, 220, 28);
		l1.add(emailtf);
		panel.add(l1);
		
		l2.setBounds(x/50, 170, 220, 30);
		l2.setLayout(null);
		passtf.setOpaque(false);
		passtf.setBounds(0, 2, 220, 28);
		l2.add(passtf);
		panel.add(l2);
		text.setFont(new Font("", Font.BOLD, 20));
		text.setBounds(x/10-30, 10, 100, 30);
		text.setForeground(Color.white);
		 usnm.setFont(new Font("", Font.BOLD, 13));
		 usnm.setBounds(x/50, 60, 100, 30);
		 usnm.setForeground(Color.white);
		 pass.setFont(new Font("", Font.BOLD, 13));
		 pass.setBounds(x/50, 140, 100, 30);
		 pass.setForeground(Color.white);
		
		panel.add(usnm);
		panel.add(pass);
		panel.add(text);
		panel.add(login);
		panel.setBorder(blackline);
		panel.setBackground(new Color(1.0f, 0.2f, 0.2f, 0.6f));
		panel.add(settings);
		panel.setBounds(500, 100, 300, 400);
		emailtf.setText("Email");
		passtf.setText("password");
		
		
	////////////////key for enter///////////////
		emailtf.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
			if(e.getKeyCode()==KeyEvent.VK_ENTER)	
			passtf.requestFocusInWindow(); 
			}
		});
		///////////select all text on focus/////////////
		emailtf.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		    	emailtf.selectAll();
		    }
		});
		passtf.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        passtf.selectAll();
		    }
		});
		
		passtf.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
			if(e.getKeyCode()==KeyEvent.VK_ENTER)	
			{
				current = searchAccount(emailtf.getText(), encryption(passtf.getText()));
			
			//if current is the admin go to admin manager interface
			if (current.getEmail().equals("admin@gmail.com") && current.getencryptedPass().equals(encryption("admin")) ){
                JOptionPane.showMessageDialog(null, "Welcome Mr Admin", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Gestionadmin();                   // gst.getAccessibleContext();
                loged();
             
			}
			else if(current!=null) {
			     //else if the current account is else than null and admin go to doctorant manager interface
				new Gestiondoctorant(current);
			}
			}
			}
		});
		
		
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				//set the current account
				current = searchAccount(emailtf.getText(), encryption(passtf.getText()));
				
				//if current is the admin go to admin manager interface
				if (current.getEmail().equals("admin@gmail.com") && current.getencryptedPass().equals(encryption("admin")) ){
                    JOptionPane.showMessageDialog(null, "Welcome Mr Admin", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Gestionadmin();       
                    // gst.getAccessibleContext();
                    loged();
                    
				}
				else if(current!=null) {
					//dispose();
				     //else if the current account is else than null and admin go to doctorant manager window
					new Gestiondoctorant(current);
                    loged();
                   

				}
			}
		};
		login.addActionListener(al);
		passtf.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				current = searchAccount(emailtf.getText(), encryption(passtf.getText()));
				if(current!=null) {
					loged();
				}
			}
			}
		});
		////////al for signup/////////////
		ActionListener al3=new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				FrameBuilder fb=new FrameBuilder();
				fb.SignupShow();
			}
		};
		settings.addActionListener(al3);
		
		return  panel;
	}
	
	//this method focus the cursor on the login form  on the email input and selects all
	public void focusinit()
	{
		emailtf.setText("Email");
		emailtf.requestFocusInWindow();
		emailtf.selectAll();
		emailtf.requestFocusInWindow();
	}
	private void loged()
	{
		String user=emailtf.getText();
		String passw=String.valueOf(passtf.getPassword());
		
			MAIN obj=new MAIN();
			obj.Successful();
		
	}

	
	  public Account searchAccount(String user, String pass) {
	        Scanner s = null;
	        try {
	            s = new Scanner(f);
	            //Loop through file line by line
	            while (s.hasNextLine()) {
	                //Split the line into array
	                String[] account = s.nextLine().split(",");
	                //Check if the username and the password match
	                if (account[1].equals(user) && account[2].equals(pass)) {
	                    //Reset the fields
	                	emailtf.setText("");
	                    passtf.setText("");
	                    //Close the scanner
	                    s.close();
	                    //Display user found dialog box.
	                    JOptionPane.showMessageDialog(null, "User Found!", "Success", JOptionPane.INFORMATION_MESSAGE);
	                    //The account found from the file.
	                    return new Account(account[0], account[1], account[2], account[3],account[4],account[5],account[7],account[8]);
	                }
	            }
	            //Close the scanner
	            s.close();

	            if(emailtf.getText().length()==0 || passtf.getText().length()==0)
	            {
		            JOptionPane.showMessageDialog(null, "empty fields!", "Error", JOptionPane.ERROR_MESSAGE);
		            return null;
	            }
	            else {

	            //Display user not found dialog box.
	            JOptionPane.showMessageDialog(null, "User not Found!", "Error", JOptionPane.ERROR_MESSAGE);
	            return null;
	            }
	        } catch (FileNotFoundException e) {
	            //Display failed to search file dialog box.
	            JOptionPane.showMessageDialog(null, "Failed to Search Account from the File!", "File Not Found", JOptionPane.WARNING_MESSAGE);
	            return null;
	        }
	    }
	
}
