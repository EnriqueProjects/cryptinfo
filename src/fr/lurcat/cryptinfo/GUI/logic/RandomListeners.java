package fr.lurcat.cryptinfo.GUI.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.lurcat.cryptinfo.GUI.pages.JCreateKeyDialog;
import fr.lurcat.cryptinfo.GUI.pages.TranslatePaneV2;
import fr.lurcat.cryptinfo.cryptage.CodeKey;
import fr.lurcat.cryptinfo.cryptage.RanCryptage;

public class RandomListeners extends AbstractListener{
	
	private TranslatePaneV2 pan;
	
	private Map<Character, Character> key = CodeKey.getDefaultKey();

	public RandomListeners(TranslatePaneV2 translatePaneV2) {
		this.pan = translatePaneV2;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(((String)e.getItem()).equalsIgnoreCase(state)) return;
		state = (String) e.getItem();
		pan.create();
		pan.fen.refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pan.keyOpen) openKey();
		else if(e.getSource() == pan.reset) resetText();
		else if(e.getSource() == pan.keyAdd) createKey();
		else if(e.getSource() == pan.check) translate();		
	}

	@SuppressWarnings("unused")
	private void createKey() {
		JCreateKeyDialog keyCreate = new JCreateKeyDialog(pan.fen, "Créer une clé", true);
	}

	private void translate() {
		if(state.equalsIgnoreCase("crypter")){
			String trad = RanCryptage.translate(pan.message.getText(), key);
			pan.trad.setText(trad.replace("null", " "));
		}else if(state.equalsIgnoreCase("décrypter")){
			String trad = RanCryptage.decode(pan.message.getText(), key);
			pan.trad.setText(trad.replace("null", " "));
		}else if(state.equalsIgnoreCase("hacker")){
			String trad = "";
			pan.check.setEnabled(false);
			pan.reset.setEnabled(false);
			pan.trad.setText(trad.replace("null", " "));
		}
	}

	private void resetText() {
		pan.message.setText("");
	}

	private void openKey() {
		try { UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName()); 
		} catch(Exception e) { e.printStackTrace(); }
		
		JFileChooser dialogue = new JFileChooser();
		FileFilter txtFilter = new FileNameExtensionFilter("clés (.txt)", "txt");
		dialogue.setFileFilter(txtFilter);
		dialogue.setAcceptAllFileFilterUsed(false);
		dialogue.showOpenDialog(null);
		
		try{
			key = CodeKey.readFile(dialogue.getSelectedFile().getAbsolutePath());
			pan.keyChose.setText(dialogue.getSelectedFile().getName());
		} catch(Exception e){ pan.keyChose.setText("Invalide !"); }
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
