import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Pythagorean
{
	public static void main(String userName) throws Exception
	{
		double a = 0, b = 0, c;
		final DecimalFormat decimalFormat = new DecimalFormat("#.##");
		FileHandler myFile = new FileHandler("Pythagorean.txt");
		
		JOptionPane.showMessageDialog(null, "Hello " + userName + "\nWe will be solving the Pythagorean theorem formula based on your input.");
		
		a = AskForInput("a");
		b = AskForInput("b");
		c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		
		String data[] = {Double.toString(a), Double.toString(b), Double.toString(c)};
		
		myFile.WriteToFile(data, false);
		
		JOptionPane.showMessageDialog(null, decimalFormat.format(c) + " = \u221A" + a + "\u00B2 + " + b + "\u00B2");
	}
	
	public static double AskForInput(String var)
	{
		String userInput;
		double output = 0.0;
		boolean exitLoop = false;
		
		do
		{
			userInput = JOptionPane.showInputDialog(null, "Please enter a value for " + var);
			
			while(userInput == null || userInput.isEmpty())
				userInput = JOptionPane.showInputDialog(null, "Invalid input.\nPlease enter a value for " + var);
			try
			{
				exitLoop = true;
				output = Double.parseDouble(userInput);
				if(output <= 0)
				{
					JOptionPane.showMessageDialog(null, "The value of '" + var + "' cannot be 0 or negative!");
					exitLoop = false;
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Invalid input please try again.");
				exitLoop = false;
			}
		} while(!exitLoop);
		
		return output;
	}
}
