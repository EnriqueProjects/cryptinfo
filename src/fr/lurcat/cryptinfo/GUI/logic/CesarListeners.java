package fr.lurcat.cryptinfo.GUI.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import fr.lurcat.cryptinfo.GUI.pages.TranslatePaneV2;
import fr.lurcat.cryptinfo.cryptage.Cryptage;

public class CesarListeners extends AbstractListener {
	
	private TranslatePaneV2 pan;
	
	public CesarListeners(TranslatePaneV2 panV2) {
		this.pan = panV2;
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
		if(e.getSource() == pan.keyPlus) keyIncr(true);
		else if(e.getSource() == pan.keyMoins) keyIncr(false);
		else if(e.getSource() == pan.check) translate();
		else if(e.getSource() == pan.reset) resetText();
	}
	
	private void keyIncr(boolean add){
		String keystr = pan.key.getText();
		int key = 0;
		
		try{ key = Integer.valueOf(keystr);			
		}catch(Exception e){}
		
		if(add) key = (key+1)%26;
		else key = (key-1<0)? 25 : key-1;
		
		pan.key.setText(String.valueOf(key));
		translate();
	}
	
	private void resetText(){
		pan.message.setText("");
	}
	
	private void translate(){
		if(state.equalsIgnoreCase("crypter")){
			String keystr = pan.key.getText();
			int key = 0;
			
			try{ key = Integer.valueOf(keystr);			
			}catch(Exception e){}
			
			String trad = Cryptage.translate(pan.message.getText(), key);
			pan.trad.setText(trad);
		} else {
			String keystr = pan.key.getText();
			int key = 0;
			
			try{ key = Integer.valueOf(keystr);			
			}catch(Exception e){}
			
			int keyN = 26-(key%26);
			
			String trad = Cryptage.translate(pan.message.getText(), keyN);
			pan.trad.setText(trad);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == pan.key && e.getKeyCode() == KeyEvent.VK_ENTER){
			translate();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar())){
			e.consume();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}

}
