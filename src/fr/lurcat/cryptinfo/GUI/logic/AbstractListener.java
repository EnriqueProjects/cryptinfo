package fr.lurcat.cryptinfo.GUI.logic;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

public abstract class AbstractListener implements ItemListener, ActionListener, KeyListener{

	protected String state = "crypter";
	
	public String getState() {
		return state;
	}

}
