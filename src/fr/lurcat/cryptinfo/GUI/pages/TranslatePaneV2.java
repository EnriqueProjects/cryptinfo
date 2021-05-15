package fr.lurcat.cryptinfo.GUI.pages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import fr.lurcat.cryptinfo.GUI.ButtonPane;
import fr.lurcat.cryptinfo.GUI.Fenetre;
import fr.lurcat.cryptinfo.GUI.ImagePane;
import fr.lurcat.cryptinfo.GUI.logic.AbstractListener;
import fr.lurcat.cryptinfo.GUI.logic.CesarListeners;
import fr.lurcat.cryptinfo.GUI.logic.RandomListeners;

@SuppressWarnings("serial")
public class TranslatePaneV2 extends JPanel{
	
	private int page;
	public Fenetre fen;
	private AbstractListener listener;

	public TranslatePaneV2(int page, Fenetre fen) {
		super();
		this.page = page;
		this.fen = fen;
		listener = (page == 1)? new CesarListeners(this) : new RandomListeners(this);
		create();
	}

	private JPanel right, left, rightCentre, leftCentre;
	
	
	public void create() {
		this.setLayout(new GridLayout(1, 2));
		this.removeAll();
		if(page == 1){
			this.add(createLeft());
			this.add(createRight());
		}else if(page == 2){
			this.add(createRight());
			this.add(createLeft());
		}
	}

	private JPanel createLeft() {
		left = new JPanel(); 
		left.setLayout(new BorderLayout());
		
		left.add(createLeftCentre(), BorderLayout.CENTER);
		left.add(createVoidPan(400, 40), BorderLayout.NORTH);
		left.add(createVoidPan(400, 40), BorderLayout.SOUTH);
		left.add(createVoidPan(40, 340), BorderLayout.WEST);
		left.add(createVoidPan(40, 340), BorderLayout.EAST);
		return left;
	}	

	public JTextArea message= new JTextArea(), trad;
	
	private JPanel createLeftCentre() {
		leftCentre = new JPanel();		
		leftCentre.setLayout(new BorderLayout());
		leftCentre.setBackground(Color.darkGray);
		
		//message
		JScrollPane scrollMsg = new JScrollPane(message);
		scrollMsg.setBackground(Color.GRAY);
		message.setBackground(Color.LIGHT_GRAY);
		scrollMsg.setBorder(BorderFactory.createTitledBorder("Message à traduire"));
		message.setLineWrap(true);
		scrollMsg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollMsg.setPreferredSize(new Dimension(320, 120));
		
		leftCentre.add(scrollMsg, BorderLayout.NORTH);
		
		//fleche
		ImagePane img = new ImagePane("fleche.png");
		img.setBackground(Color.DARK_GRAY);
		
		leftCentre.add(img, BorderLayout.CENTER);
		
		//traduction
		trad = new JTextArea();
	    trad.setEditable(false);
	    trad.setLineWrap(true);
	    JScrollPane scrollTrad = new JScrollPane(trad);
	    scrollTrad.setBackground(Color.gray);
	    trad.setBackground(Color.LIGHT_GRAY);
	    scrollTrad.setBorder(BorderFactory.createTitledBorder("Traduction: "));
	    scrollTrad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollTrad.setPreferredSize(new Dimension(320, 120));
	    
	    leftCentre.add(scrollTrad, BorderLayout.SOUTH);
		return leftCentre;
	}
	
	private JPanel createVoidPan(int x, int y){
		JPanel pan = new JPanel();
		pan.setBackground(Color.DARK_GRAY);
		pan.setPreferredSize(new Dimension(x, y));
		return pan;
	}

	private JPanel createRight() {
		right = new JPanel();
		right.setBackground(Color.darkGray);
		right.setLayout(new BorderLayout());
		if(page == 1){
			right.add(createrightCentre(), BorderLayout.CENTER);
			
		} else if(page == 2){
			right.add(createRightRandom(), BorderLayout.CENTER);
			
		}
		right.add(createVoidPan(400, 40), BorderLayout.NORTH);
		right.add(createVoidPan(400, 40), BorderLayout.SOUTH);
		right.add(createVoidPan(40, 340), BorderLayout.WEST);
		right.add(createVoidPan(40, 340), BorderLayout.EAST);
		return right;
	}
	
	//méthodes pour le code random
	
	private JPanel rightCentreRandom;

	private JPanel createRightRandom() {
		rightCentreRandom = new JPanel();
		rightCentreRandom.setLayout(new BorderLayout());
		rightCentreRandom.setBackground(Color.darkGray);
		
		if(listener.getState().equalsIgnoreCase("hacker"))
			rightCentreRandom.add(createKeyHackRandom(), BorderLayout.CENTER);
		else rightCentreRandom.add(createKeyRandom(), BorderLayout.CENTER);
		
		rightCentreRandom.add(createCheckPan(), BorderLayout.SOUTH);
		rightCentreRandom.add(createTypePan(), BorderLayout.NORTH);
		
		return rightCentreRandom;
	}
	
	public JButton keyAdd, keyOpen;	
	public JTextArea keyChose= new JTextArea("  Selectionnez\n       une clé");
	private JPanel createKeyRandom() {
		JPanel keyPan = new JPanel();
		keyPan.setLayout(new BorderLayout());
		
		keyAdd = new ButtonPane("key-add.png");
		keyAdd.setPreferredSize(new Dimension(100, 100));
		keyAdd.addActionListener(listener);
		
		keyOpen = new ButtonPane("open.png");
		keyOpen.setPreferredSize(new Dimension(100, 100));
		keyOpen.addActionListener(listener);
		
		JPanel labPan = new JPanel();
		labPan.setBackground(Color.LIGHT_GRAY);
		labPan.setLayout(new BorderLayout());
		
		keyChose.setEditable(false);
		keyChose.setBackground(Color.LIGHT_GRAY);
		
		labPan.add(keyChose, BorderLayout.CENTER);
		labPan.add(createVoidPan(320, 30), BorderLayout.NORTH);
		labPan.add(createVoidPan(320, 30), BorderLayout.SOUTH);
		labPan.add(createVoidPan(15, 40), BorderLayout.EAST);
		labPan.add(createVoidPan(15, 40), BorderLayout.WEST);
		
		keyPan.add(createVoidPan(320, 40), BorderLayout.NORTH);
		keyPan.add(keyAdd, BorderLayout.WEST);
		keyPan.add(labPan, BorderLayout.CENTER);
		keyPan.add(keyOpen, BorderLayout.EAST);
		keyPan.add(createVoidPan(320, 40), BorderLayout.SOUTH);
		
		return keyPan;
	}
	
	public JProgressBar progress;
	private JPanel createKeyHackRandom(){
		JPanel keyPan = new JPanel();
		keyPan.setLayout(new BorderLayout());
		
		progress = new JProgressBar();
		progress.setMinimum(0);
		progress.setMaximum(500);
		progress.setStringPainted(true);
		
		keyPan.add(createVoidPan(320, 70), BorderLayout.NORTH);
		keyPan.add(progress, BorderLayout.CENTER);
		keyPan.add(createVoidPan(320, 70), BorderLayout.SOUTH);
		
		return keyPan;
	}
	
	//méthodes pour le code césar

	private JPanel createrightCentre() {
		rightCentre = new JPanel();
		rightCentre.setLayout(new BorderLayout());
		rightCentre.setBackground(Color.darkGray);
		
		rightCentre.add(createTypePan(), BorderLayout.NORTH);
		rightCentre.add(createKeyPan(), BorderLayout.CENTER);
		rightCentre.add(createCheckPan(), BorderLayout.SOUTH);
		return rightCentre;
	}
	
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel createTypePan() {
		JPanel typePan = new JPanel();
		combo = new JComboBox();
		combo.addItem("Crypter");
		combo.addItem("Décrypter");
		combo.addItem("Hacker");
		combo.setSelectedItem(listener.getState());
		combo.addItemListener(listener);
		
		typePan.add(new JLabel("Comment Traduire ?       "));
		typePan.add(combo);
		typePan.setBackground(Color.LIGHT_GRAY);
		return typePan;
	}
	
	public JButton keyPlus, keyMoins;
	public JTextField key;
	private JPanel createKeyPan() {
		JPanel keyPan = new JPanel();
		keyPan.setLayout(new BorderLayout());
		
		key = new JTextField("0");
		key.setPreferredSize(new Dimension(90, 30));
		key.setHorizontalAlignment(JTextField.CENTER);
		key.addKeyListener(listener);
		
		JLabel lab = new JLabel("Utiliser la clé: ");
		
		if(listener.getState().equalsIgnoreCase("crypter") || listener.getState().equalsIgnoreCase("décrypter")){
			JPanel keyPan2 = new JPanel();
			keyPan2.setBackground(Color.lightGray);
			keyPan2.add(lab);
			keyPan2.add(key);
			keyPan.add(keyPan2, BorderLayout.CENTER);
			
		} else if(listener.getState().equalsIgnoreCase("hacker")){
			key.setEditable(false);
			
			keyPlus = new JButton("augmenter la clé");
			keyMoins = new JButton("diminuer la clé");
			keyPlus.addActionListener(listener);
			keyMoins.addActionListener(listener);
			
			JPanel keyButtons = new JPanel();
			keyButtons.setBackground(Color.lightGray);
			keyButtons.add(keyMoins);
			keyButtons.add(keyPlus);
			
			JPanel keyPan3 = new JPanel();
			keyPan3.setBackground(Color.lightGray);
			keyPan3.add(lab);
			keyPan3.add(key);
			
			JPanel keyPanH = new JPanel();
			keyPanH.setBackground(Color.lightGray);
			keyPanH.setLayout(new BorderLayout());
			keyPanH.add(keyPan3, BorderLayout.CENTER);
			keyPanH.add(keyButtons, BorderLayout.SOUTH);
			
			keyPan.add(keyPanH, BorderLayout.CENTER);
		}
		
		keyPan.add(createVoidPan(320, 50), BorderLayout.NORTH);
		keyPan.add(createVoidPan(320, 50), BorderLayout.SOUTH);
		return keyPan;
	}
	
	public ButtonPane check, reset;
	private JPanel createCheckPan() {
		JPanel checkPan = new JPanel();
		checkPan.setLayout(new BorderLayout());
		checkPan.setBackground(Color.DARK_GRAY);
		check = new ButtonPane("check.png");
		reset = new ButtonPane("reset.png");
		check.addActionListener(listener);
		reset.addActionListener(listener);
		check.setPreferredSize(new Dimension(100, 100));
		reset.setPreferredSize(new Dimension(100, 100));
		checkPan.add(reset, BorderLayout.WEST);
		checkPan.add(check, BorderLayout.EAST);
		checkPan.setPreferredSize(new Dimension(320, 100));
		return checkPan;
	}

}
