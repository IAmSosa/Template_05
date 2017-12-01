import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Party {
	private static double userBudget;
	private static double totalCost;
	private static final double chairPrice = 8.50;
	private static final double spoonPrice = .50;
	private static final double knifePrice = .50;
	private static final double forkPrice  = .50;	
	private static FileHandler myFile = new FileHandler("PartyReceipt.txt");
	// forks, spoons, knifes, chairs > per visitor using drop down > item for party
	public static void main(String userName) throws Exception
	{
		JOptionPane.showMessageDialog(null, "Hello " + userName + "\nWe will be asking you for party rental information.");
		userBudget = GetBudget();
		
		GetItems();
		
		myFile.DisplayDataFromFile();
		//JOptionPane.showMessageDialog(null, "Budget: " + userBudget + "\nTotal cost: " + totalCost);
	}
	
	public static double GetBudget() throws Exception
	{	
		double budget = 0;
		String input;
		boolean exitLoop = false;
		
		do
		{		
			input = JOptionPane.showInputDialog(null, "What is your budget?");
			
			if(input == null)
			{
				JOptionPane.showMessageDialog(null, "You clicked cancel, exiting the program.");
				System.exit(0);
			}
			while (input.isEmpty())
				input = JOptionPane.showInputDialog(null, "Invalid input\nWhat is your budget?");
			
			try 
			{
				exitLoop = true;
				budget = Double.parseDouble(input);
				if(budget <= 0)
				{
					JOptionPane.showMessageDialog(null, "Your budget cannot be 0 or negative!");
					exitLoop = false;
				}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Invalid input please try again.");			
				exitLoop = false;
			}
		}
		while(!exitLoop);
		myFile.WriteToFile("Budget: $" + budget, false);
		return budget;
	}
	
	public static void GetItems() throws Exception
	{
		String selectedItem = null;
		Object[] array = { "Fork", "Spoon", "Chair", "Knife", "End Programm" };
		
			try
			{
				selectedItem = (String) JOptionPane.showInputDialog(null, "Select an Item!\nBudget: $" + userBudget + "\nCurrent total: $" + (totalCost > 0 ? (userBudget - totalCost):0), 
					"Make a Selection", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
				
				switch (selectedItem)
				{
					case "Fork":				
						totalCost += GetItemCost(selectedItem, forkPrice);
						break;
					case "Spoon":
						totalCost += GetItemCost(selectedItem, spoonPrice);
						break;
					case "Chair":
						totalCost += GetItemCost(selectedItem, chairPrice);
						break;
					case "Knife":
						totalCost += GetItemCost(selectedItem, knifePrice);
						break;
					case "End Programm":
						return;
					default:
						break;
				}
			}
			catch(Exception e)
			{
				return;
			}
			GetItems();
	}
	
	
	public static double GetItemCost(String item, double cost) throws Exception
	{
		int quantity = 0;		
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 1; i * cost <= userBudget - totalCost; i++)
			list.add(i);
		if(list.isEmpty())
			JOptionPane.showMessageDialog(null, "Insufficient funds.");
		Object[] array = list.toArray();
		
		try
		{
			quantity = (int) JOptionPane.showInputDialog(null, "How many " + item + "(s) do you want to rent at $" + cost + " each?", 
			"Make a Selection", JOptionPane.PLAIN_MESSAGE, null, array, array[0]);
			myFile.WriteToFile(item + ": " + quantity + " @ $" + cost, true);	
		}
		catch(Exception e)
		{
		}		
		return quantity * cost;
	}	
}
