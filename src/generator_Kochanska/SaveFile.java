package generator_Kochanska;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SaveFile {

	JFrame message = new JFrame();

	public SaveFile(int iteration, int n, int [] data){
			
		//okienko wyboru pliku
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Wybierz plik");
	    chooser.showDialog(null, "Wybierz");
		
	    BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(chooser.getSelectedFile()),Charset.forName("ASCII")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(message, "ERROR!");
		}
		
		//zapisanie danych statystycznych
		try {
			writer.write("iteracje     ");
			writer.write(String.valueOf(iteration));
			writer.newLine();
			writer.write("indeks     wystapienia");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(message, "ERROR!");
		}
		
		//zapisanie indeksow i powtorzen 
		for (int i = 0; i < n; i++)
		{
			try {
				writer.newLine();
				if (i+1 < 10)
					writer.write((i+1)+"              ");
				else 
					writer.write((i+1)+"             ");
				writer.write(String.valueOf(data[i]));
				
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(message, "ERROR!");
			}
		}	
					
		//zamkniecie 
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(message, "ERROR!");
		}
	}
}
