import javax.swing.*;

public class MAIN 
{
	private static FrameBuilder frame=new FrameBuilder();  // principal window that contains login+register form
	private static JFrame LoginFrame=frame.GetLoginFrame(); //login window
	public static PanelBuilder Panels=new PanelBuilder();    //login
	public static Account a=new Account();
	public static void main(String[] args) {
          Panels.focusinit();	//the login panel is selected automatically(email)
           }
	
	protected void Successful()
	{
		LoginFrame.dispose();
		System.out.println("Logged-in");
	}
	
}
