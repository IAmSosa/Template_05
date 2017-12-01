import javax.swing.JOptionPane;

public class Username
{
	public static String GetUsername() throws Exception
	{
		FileHandler myFile = new FileHandler("username.txt");
		String userName;
		
		userName = JOptionPane.showInputDialog(null, "Please enter your name.");
		
		if(userName == null)
			System.exit(0);	
		
		while(userName.isEmpty())
		{
			userName = JOptionPane.showInputDialog(null, "Invalid input.\nPlease enter your name.");
			if(userName == null)
				System.exit(0);
		}
		
		myFile.WriteToFile(userName, false);
		return userName;
	}
}