package com.parser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.nodes.Document;

public class WordCount extends JScrollPane {

	String text;
	HashMap<String, Integer> counter;
	JPanel panel;
	ArrayList<Entry<String, Integer>> sorted;
	
	public WordCount(Document document){
	
		counter = new HashMap<String, Integer>();
		text = document.body().text();
		countWords();
		sorted = sortByValues(counter);
		panel = new JPanel(new GridLayout(counter.size(),2));
		
		for(Entry<String, Integer> entry : sorted){
			JLabel wrd = new JLabel(entry.getKey());
			JLabel val = new JLabel(entry.getValue().toString());
			panel.add(wrd);
			panel.add(val);
		}
		setViewportView(panel);
		setPreferredSize(new Dimension(350,350));
	}

	private ArrayList<Entry<String, Integer>> sortByValues(HashMap<String, Integer> map) {

		ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Comparator<Entry<String, Integer>> byMapValues = new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> left, Entry<String, Integer> right){
				return right.getValue().compareTo(left.getValue());
			}
		};
		Collections.sort(list, byMapValues);
		return list;
	}

	private void countWords() {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(text);
		while(st.hasMoreTokens()){
			String word = st.nextToken();
			int count = counter.containsKey(word) ? counter.get(word) : 0;
			counter.put(word, count + 1);
		}
	}
	
}
