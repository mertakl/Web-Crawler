package com.parser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteParser extends JTabbedPane{
	
	Document document;
	JScrollPane spane;
	
	WebsiteParser(){
	
		try {
			document = Jsoup.connect("http://www.facebook.com/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getLinks();
		addTab("Links", spane);
		addTab("Images", new ImageGrabber(document));
		addTab("Word Count", new WordCount(document));
	}

	public void getLinks(){
		Elements links = document.getElementsByTag("a");
		JPanel linkPanel = new JPanel();
		linkPanel.setLayout(new GridLayout(links.size(), 1));
		
		for(Element link : links){
			String  l = link.attr("href");
			if(l.length() > 0){
				if(l.length() < 4)
					l = document.baseUri() + l.substring(1);
				else if(!l.substring(0, 4).equals("http"))
					l = document.baseUri() + l.substring(1);
				
				SwingLink label = new SwingLink(link.text(),l);
				linkPanel.add(label);
	
			}
		}
		spane = new JScrollPane(linkPanel);
		spane.setPreferredSize(new Dimension(350,350));
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Website Parser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WebsiteParser wp = new WebsiteParser();
		frame.add(wp);
		frame.setVisible(true);
		frame.setSize(400, 400);
	}

}
