package generator_Kochanska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class GeneratorMain extends JFrame {

	private static final long serialVersionUID = 1213037566539043913L;
	JFrame message = new JFrame();
	
	public GeneratorMain(){}
	
	public GeneratorMain(int pula, int liczbaLosowan) throws HeadlessException {
		
		//ustawianie parametrów wejœciowych
		int n = 70;
		int k = 20;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		
		//glowny panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(232,193,193));
		this.add(mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 0;
		a.gridy = 0;
		a.weightx = 1.0;
		a.gridwidth = 5;
		a.fill = GridBagConstraints.NONE;
		a.insets = new Insets(20,0,0,0);
		
		//tworzenie panelu info
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(247,247,247));
		this.add(infoPanel,BorderLayout.PAGE_END);
		JLabel infoLabel = new JLabel("Generator Keno, Paula Kochañska 2015");
		infoLabel.setFont(infoLabel.getFont().deriveFont(10.0f));
		infoPanel.add(infoLabel);
		
		JLabel titleLabel = new JLabel("Generator liczb pseudolowosych KENO");
		mainPanel.add(titleLabel,a);
		
		a.gridy = 1;
		
		//panel liczb
		JPanel numberPanel = new JPanel();
		numberPanel.setBackground(new Color(253,228,228));
		numberPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		numberPanel.setPreferredSize(new Dimension(350,100));
		mainPanel.add(numberPanel,a);
		
		//layout
		numberPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,10,10,10);
		c.weightx = 1.0;
		c.gridwidth = 1;
	
		//etykiety
		JLabel [] label = new JLabel[k];
		
		for (int j = 0; j < 2; j++)
		{
			c.gridy = j;
			for (int i = 0; i < 10; i++)
			{
				c.gridx = i;
				label[i+10*j] = new JLabel(" ");
				numberPanel.add(label[i+10*j],c);
			}
		}
		
		//przycisk losuj¹cy wraz z obs³ug¹ zdarzeñ
		a.gridwidth = 1;
		a.gridx = 0;
		a.gridy = 2;
		JButton button = new JButton("Losuj");
		mainPanel.add(button,a);
		
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
				int [] table = new int[k];
		    	
				//zerowanie tablicy
				for (int j = 0; j < k; j++)
				{
					table[j] = 0;
				}
				
				table = generate(n,k);
				
				//wpisanie tablicy do etykiet 
				for (int i = 0; i < k; i++)
				{
					label[i].setText(Integer.toString(table[i]));
				}
		    }
		});
		
		//przycisk sortujacy
		a.gridwidth = 1;
		a.gridx = 1;
		a.gridy = 2;
		JButton buttonSort = new JButton("Sortuj");
		mainPanel.add(buttonSort,a);
		
		buttonSort.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	int [] tmp = new int[k];
		    	for (int i = 0; i < k; i++)
		    	{
		    		tmp[i] = Integer.parseInt(label[i].getText());
		    	}
		    	
		    	//sortowanie tablicy tmp
		    	Arrays.sort(tmp);
				
				//wpisanie do labeli
				for (int i = 0; i < k; i++)
				{
					label[i].setText(Integer.toString(tmp[i]));
				}
		    }
		});
		
		//menu
		setJMenuBar(new MenuBar(n,k));
		
	}
	
	final public int [] generate (int n, int k)
	{
		int [] tableN = new int[n];
		int [] tableK = new int[k];
		
		//zerowanie tablic
		for (int i = 0; i < n; i++)
		{
			tableN[i] = 0;
			if (i < k)
			{
				tableK[i] = 0;
			}
		}
		
		//wypelnienie tablicy
		for (int i = 0; i < n; i++)
		{
			tableN[i] = i+1;
		}
		
		Random random = new Random();

		//algorytm losujacy
		for (int i = 0; i < k; i++)
		{
			int r = (int)(random.nextDouble()*n);
			tableK[i] = tableN[r];
			tableN[r] = tableN[n-1];
			n--;
		}
		return tableK;
	}
	
	public static void main(String[] args) {
		
		//ustawienie okienka
		GeneratorMain frame = new GeneratorMain(70,20); 
		frame.setTitle("Generator Keno");
	    frame.setResizable(false);
	    frame.setSize(400,300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}
