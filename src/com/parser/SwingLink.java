package com.parser;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;

public class SwingLink extends JLabel{

	String text;
	URI uri;
	
	public SwingLink(String textIn, String uriIn) {
 
		try {
			uri = new URI(uriIn);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		text = textIn;
		setText(text, true);
		setToolTipText(uri.toString());
		addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent event){
				open(uri);
			}
			public void mouseEntered(MouseEvent event){
				setText(text, false);
			}
			public void mouseExited(MouseEvent event){
				setText(text, true);
			}
		});
	   }
		private void setText(String text, boolean ul){
			
			String link = ul ? "<u>"+ text +"<u>" : text;
			setText("<html><span style=\"color:#000099;\">"+link+"</span></html>");

		}
		
		private void open(URI uri){
		if(Desktop.isDesktopSupported()){
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
       }
	}
	
	

