package fr.lurcat.cryptinfo.GUI.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lurcat.cryptinfo.GUI.Fenetre;

public class Listeners implements ActionListener {

	private Fenetre fen;

	public Listeners(Fenetre fenetre) {
		this.fen = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == fen.home) calledHome();
		else if(e.getSource() == fen.gui) calledGui();
		else if(e.getSource() == fen.code) calledCode();
		else if(e.getSource() == fen.codeCes) calledCesar();
		else if(e.getSource() == fen.codeRan) calledRandom();
		fen.refresh();
	}
	
	private void calledHome() {
		fen.cesW = 80;
		fen.ranW = 80;
		fen.page = 0;
	}

	private void calledGui(){
		
	}
	
	private void calledCode(){
		
	}
	
	private void calledCesar(){
		fen.cesW = 75;
		fen.ranW = 80;
		fen.page = 1;
	}
	
	private void calledRandom(){
		fen.cesW = 80;
		fen.ranW = 75;
		fen.page = 2;
	}

}
