package fr.lurcat.cryptinfo.GUI.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.lurcat.cryptinfo.cryptage.CodeKey;
import fr.lurcat.cryptinfo.cryptage.Cryptage;

@SuppressWarnings("serial")
public class JCreateKeyDialog extends JDialog implements ItemListener, ActionListener{
	
	public JCreateKeyDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(createContentPane());
		this.setVisible(true);
	}

	private JButton save, reset;
	private JPanel createContentPane() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		
		save = new JButton("sauvegarder");
		save.setPreferredSize(new Dimension(200, 80));
		reset = new JButton("réinitialiser");
		
		save.addActionListener(this);
		reset.addActionListener(this);
		
		pan.add(createKeyGrid(), BorderLayout.NORTH);
		pan.add(save, BorderLayout.WEST);
		pan.add(reset, BorderLayout.CENTER);
		
		return pan;
	}
	
	@SuppressWarnings("rawtypes")
	private JComboBox[] trads = new JComboBox[26];
	@SuppressWarnings("rawtypes")
	private Map<JComboBox, String> items = new HashMap<>();
	@SuppressWarnings("rawtypes")
	private JPanel createKeyGrid(){
		JPanel pan = new JPanel();
		pan.setBackground(Color.LIGHT_GRAY);
		
		pan.setLayout(new GridLayout(6, 5));
		
		int i = 0;
		for(char c : Cryptage.ALPHABET){
			
			JPanel j = new JPanel();
			JComboBox trad = getAbcCombo();
			items.put(trad, "0");
			
			j.add(new JLabel(c + ": "));
			j.add(trad);
			j.setBorder(BorderFactory.createEtchedBorder());
			trads[i] = trad; 
			pan.add(j);
			
			i++;
		}
		
		pan.setPreferredSize(new Dimension(400, 220));
		
		return pan;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getAbcCombo(){
		JComboBox combo = new JComboBox<>();
		combo.addItem("0");
		for(char c : Cryptage.ALPHABET){
			combo.addItem(String.valueOf(c));
		}
		combo.addItemListener(this);
		return combo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(((String)e.getItem()).equals("0")) return;
		
		if(!items.get(e.getSource()).equalsIgnoreCase("0"))
			for(JComboBox combo : trads){
				if(combo != e.getSource()) 
					combo.addItem(items.get(e.getSource())); }
		
		for(JComboBox combo : trads){
			if(combo != e.getSource()) 
				combo.removeItem(e.getItem());
		}
		
		items.replace((JComboBox)e.getSource(), (String)e.getItem());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save) save();
		else if (e.getSource() == reset) reset();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void reset() {
		for(JComboBox box : trads){
			box.removeAllItems();
			box.addItem("0");
			for(char c : Cryptage.ALPHABET){
				box.addItem(String.valueOf(c));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void save(){
		Map<Character, Character> key = new HashMap<>();
		int i = 0;
		for(JComboBox box : trads){
			if(((String)box.getSelectedItem()).equals("0")) return;
			char[] str = ((String)box.getSelectedItem()).toCharArray();
			key.put(Cryptage.ALPHABET.get(i), str[0]);		
			i++;
		}
		
		JFileChooser dialogue = new JFileChooser();
		FileFilter txtFilter = new FileNameExtensionFilter("clés (.txt)", "txt");
		dialogue.setFileFilter(txtFilter);
		dialogue.setSelectedFile(new File("clé.txt"));
		dialogue.setAcceptAllFileFilterUsed(false);
		dialogue.showOpenDialog(null);
		
		CodeKey.CreateFile(key, dialogue.getSelectedFile().getAbsolutePath());
	}
	
	

}
