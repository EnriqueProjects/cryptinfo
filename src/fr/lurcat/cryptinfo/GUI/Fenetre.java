package fr.lurcat.cryptinfo.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import fr.lurcat.cryptinfo.GUI.logic.Listeners;
import fr.lurcat.cryptinfo.GUI.pages.TranslatePaneV2;




import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{
	
	private ActionListener listener = new Listeners(this);
	
	public Fenetre(){
		init();
	}
	
	private void init(){
		this.setTitle("Cryptinfo");
		this.setSize(900, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		refresh();
	}
	
	public void refresh(){
		globalCont = new JPanel(); 
		globalNoTitle = new JPanel();
		menuLeft = new JPanel();
		globalRight = new JPanel(); 
		menuRight = new JPanel();
		this.setContentPane(createGlobalNoTitle());
		this.setVisible(true);
	}
	
	/*	Organisation du logiciel
	 *  	- Creation de tous les JPanel un par un
	 *  	- retour des JPanel par les méthodes
	 */		
	
	private JPanel globalCont, globalNoTitle, menuLeft, globalRight, menuRight;

	@SuppressWarnings("unused")
	private JPanel createGlobalCont() {
		globalCont.setLayout(new BorderLayout());
		
		JPanel img = new ImagePane("title.jpg");
		img.setPreferredSize(new Dimension(900, 100));
		
		globalCont.add(img, BorderLayout.NORTH);
		globalCont.add(createGlobalNoTitle(), BorderLayout.CENTER);
		
		return globalCont;
	}
	
	private JPanel createGlobalNoTitle(){
		globalNoTitle.setLayout(new BorderLayout());
		
		globalNoTitle.add(createMenuLeft(), BorderLayout.WEST);
		globalNoTitle.add(createGlobalRight(), BorderLayout.CENTER);
		
		return globalNoTitle;
	}
	
	public ButtonPane home, gui, code;
	
	private JPanel createMenuLeft(){
		menuLeft.setPreferredSize(new Dimension(100, 500));
		menuLeft.setLayout(new BorderLayout());
		
		home = new ButtonPane("home.png");
		home.setPreferredSize(new Dimension(100, 80));
		gui = new ButtonPane("gui.jpg");
		code = new ButtonPane("code.jpg");
		code.setPreferredSize(new Dimension(100, 210));
		
		home.addActionListener(listener);
		gui.addActionListener(listener);
		code.addActionListener(listener);
		
		menuLeft.add(home, BorderLayout.NORTH);
		menuLeft.add(gui, BorderLayout.CENTER);
		menuLeft.add(code, BorderLayout.SOUTH);
		
		return menuLeft;
	}
	
	public int page = 0;

	private JPanel createGlobalRight(){
		globalRight.setLayout(new BorderLayout());
		
		globalRight.add(createMenuRight(), BorderLayout.NORTH);
		if(page==0) globalRight.add(createHomePage(), BorderLayout.CENTER);
		else if(page==1) globalRight.add(createCesarPage(), BorderLayout.CENTER);
		else globalRight.add(createRanPage(), BorderLayout.CENTER);
		
		return globalRight;
	}
	
	public ButtonPane codeCes, codeRan;
	public int cesW = 80, ranW = 80;

	private JPanel createMenuRight(){
		menuRight.setPreferredSize(new Dimension(800, 80));
		menuRight.setLayout(new GridLayout(1, 2));
		
		JPanel panL = new JPanel(), panR = new JPanel();
		codeCes = new ButtonPane("codeces.jpg");
		codeRan = new ButtonPane("coderan.jpg");
		
		panL.setBackground(Color.CYAN);
		panR.setBackground(Color.CYAN);
		codeCes.setPreferredSize(new Dimension(400, cesW));
		codeRan.setPreferredSize(new Dimension(400, ranW));
		
		panL.setLayout(new BorderLayout());
		panL.add(codeCes, BorderLayout.NORTH);
		panR.setLayout(new BorderLayout());
		panR.add(codeRan, BorderLayout.NORTH);
		
		codeCes.addActionListener(listener);
		codeRan.addActionListener(listener);
		
		menuRight.add(panL);
		menuRight.add(panR);
		
		return menuRight;		
	}
	
	/*	Création des différents aspects de la zone de traduction
	 * 		- HomePage (page 0)
	 * 		- CesarPage (page 1)
	 * 		- RanPage (page 2)
	 */
	
	private JPanel HomePage = new ImagePane("homePage.jpg"), CesarPage = new TranslatePaneV2(1, this), RanPage = new TranslatePaneV2(2, this);
	
	private JPanel createHomePage(){
		HomePage.setBackground(Color.darkGray);
		return HomePage;
	}
	
	private JPanel createCesarPage(){
		return CesarPage;
	}

	private JPanel createRanPage(){
		return RanPage;
	}

}
