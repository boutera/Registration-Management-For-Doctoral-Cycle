import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FrameBuilder {

static Button button=new Button();  //button
ImageIcon j1=new ImageIcon("images/a.png");
JLabel img=new JLabel(j1);

public static PanelBuilder panels =new PanelBuilder();
public static JPanel SignupPanel=panels.GetSignup();
public static JPanel LoginPanel=panels.GetLogin();
final JFrame login=new JFrame("LOGIN TO CONTINUE");


public JFrame GetLoginFrame()
{  // this is the principal window of the login-register form
	LoginPanel.setVisible(true);
	Dimension screensize=new Dimension(900,600);
	int y=screensize.height;    //get the position y
	int x=screensize.width;
	img.setBounds(0, 0, 900, 600);
 
	login.setUndecorated(true);
	login.setBackground(new Color(0.1f, 0.1f, 0.1f,0.9f));
	JButton close=button.GetClose();  //this button closes the window
	close.setBounds(870, 3, 20, 20);
	//login.setBounds(x/4-30, y/4-20,713, 384+20);
     login.setSize(900,600);
	login.setLayout(null);
	login.setLocationRelativeTo(null);
	login.add(close);
	login.getContentPane().setBackground(Color.blue);
	login.add(SignupPanel);
	SignupPanel.setVisible(false);    //hide the signupPanel and lets  the login
	login.add(LoginPanel);
	login.add(img);
 	ActionListener closing=new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			login.dispose();   //dispose it means close the window 
			}
	};
	close.addActionListener(closing);
	login.setVisible(true);
	return login;
}
public void SignupShow()  
{   
	SignupPanel.setVisible(true); //montrer
	LoginPanel.setVisible(false);//cacher
}
public void SignupHide()  
{
	SignupPanel.setVisible(false); 
	LoginPanel.setVisible(true);
}

	
}
