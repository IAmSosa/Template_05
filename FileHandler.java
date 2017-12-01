import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileHandler
{
	private static File myFile;
	private static PrintWriter outputFile = null;
	private static String fileName = null;

	public FileHandler(String string)
	{
		fileName = string;
		myFile = new File(fileName);
	}

	public void WriteToFile(String data, boolean append) throws Exception
	{
		if(append)
		{
			FileWriter fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
		}
		else
			outputFile = new PrintWriter(myFile);
		outputFile.println(data);
		outputFile.close();
	}
	
	public void WriteToFile(String[] data, boolean append) throws Exception
	{
		if(append)
		{
			FileWriter fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
		}
		else
			outputFile = new PrintWriter(myFile);
		for(int i = 0; i < data.length; i++)
			outputFile.println(data[i]);
		outputFile.close();
	}
	
	public void DisplayDataFromFile() throws FileNotFoundException
	{
		Scanner reader = new Scanner(myFile);
		String data = "";
		while (reader.hasNextLine())
		{
			data += reader.nextLine() + "\n";
		}
		JOptionPane.showMessageDialog(null, data);
	}
}
