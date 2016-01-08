package generator_Kochanska;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParsePosition;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Simulate { 
	
	JFrame message = new JFrame();
	
	//przechodnia tablica
	int [] data;
	
	public Simulate(int n, int k) {
		data = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			 data[i] = 0;
		}
	}
	
	//funkcja symuluj¹ca 'iteration' losowañ
	void simulate(int n, int k, int iteration, int []tab)
	{
		int [] tableN = new int[n];
		int [] tableK = new int[k];
		int [] tmp = new int[n];
		
		//zerowanie tablic
		for (int i = 0; i < n; i++)
		{
			tableN[i] = 0;
			tmp[i] = 0;
			
			if (i < k)
				tableK[i] = 0;
		}
		
		//zliczanie ile razy wystapila dana liczba
		for (int i = 0; i < iteration; i++)
		{
			for (int j = 0; j < n; j++)
			{
				tmp[j] = tableN[j];
			}
			tableK = tab;
		}
		
		//wypisywanie zliczen
		for (int j = 0; j < n; j++)
		{
			System.out.println(tableN[j]);
		}
	}
	
	//funkcja pokazuj¹ca okienko
	void show(int n, int k)
	{
		//stworzenie okienka
		JFrame frame = new JFrame();
	    frame.setSize(300,200);
	    frame.setTitle("Generator Keno - symulacja");
	    frame.setBackground(new Color(247,247,247));
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
		frame.setLayout(new GridBagLayout());
		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 0;
		a.weightx = 1.0;
		a.gridwidth = 5;
		a.fill = GridBagConstraints.NONE;
		a.insets = new Insets(0,0,0,0);
		
		//etykiety informacji o symulacji
		a.gridy = 0;
		frame.add(new JLabel("Wpisz iloœæ iteracji i naciœnij przycisk,"),a);
		
		a.gridy = 1;
		frame.add(new JLabel("aby zapisaæ do pliku."),a);
		
		//pole tekstowe do symulacji i obsluga zdarzenia
	    JTextField simulateText = new JTextField(10);
	    simulateText.setText("np. 1");
	    simulateText.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	        	simulateText.setText("");
	        }
	      });
	    
		a.gridy = 2;
		a.insets = new Insets(10,0,0,0);
		frame.add(simulateText,a);
		
		//deklaracja obiektu generatora
		GeneratorMain object = new GeneratorMain();
	    
	    //przycisk do symulacji
		JButton simulateButton = new JButton("Symuluj");
		simulateButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	//sprawdzenie, czy wprowadzone dane s¹ poprawne
			    if (isNumeric(simulateText.getText()) == false)
			    	JOptionPane.showMessageDialog(message, "B³êdne dane! WprowadŸ liczbê naturaln¹.");
			    
				int iteration = Integer.parseInt(simulateText.getText());
				int tmp[] = new int[k];
			    
				for (int i = 0; i < iteration; i++)
				{
					//wywo³anie generatora na obiekcie
					tmp = object.generate(n,k);
					
					//zwiekszanie o jeden miejsca o indeksie tmp[j]-1 aby zliczyæ wyst¹pienia
					for (int j = 0; j < k; j++)
					{
						data[tmp[j]-1]+=1;
					}
				}
		    	new SaveFile(iteration, n, data);
		    	frame.dispose();
		    }
		});
		
		a.gridy = 3;
		frame.add(simulateButton,a);
		
	}
	
	//funkcja sprawdzaj¹ca, czy dany string jest liczb¹
	public static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0); //pozycja do analizy o indeksie 0
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex(); //porównanie, czy na koñcu stringa jest stworzona wczeœniej pozycja
	}
}
