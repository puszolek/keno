package generator_Kochanska;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 4554638798087721878L;
	JFrame message = new JFrame();

	public MenuBar(int n, int k) {
		
		JMenu menu = new JMenu("Menu");
		JMenu menuStats = new JMenu("Statystyka");
		this.add(menu);
		this.add(menuStats);
		
		//elementy menu wraz z obsluga zdarzen
		JMenuItem menuItemAbout = new JMenuItem("O programie");
		menuItemAbout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JOptionPane.showMessageDialog(message, "Generator liczb pseudolosowych KENO. \nPaula Kochañska, 2015");
		    }
		});
		
		JMenuItem menuItemExit = new JMenuItem("WyjdŸ z programu");
		menuItemExit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});

        Simulate stats = new Simulate(n,k);
		
		JMenuItem menuItemStats = new JMenuItem("Symuluj");
		menuItemStats.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	stats.show(n,k);
		    }
		});
		
		//dodanie elementow do menu i ustawienie paska menu
		menu.add(menuItemAbout);
		menu.addSeparator();
		menu.add(menuItemExit);
		menuStats.add(menuItemStats);
	}
}
