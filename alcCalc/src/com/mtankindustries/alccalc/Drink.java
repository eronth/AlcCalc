package com.mtankindustries.alccalc;

import java.io.Serializable;
import java.util.LinkedList;

public class Drink implements Serializable {
	private static final long serialVersionUID = -8796991257326593544L;
	private LinkedList<Ingredient> ingr = new LinkedList<Ingredient>();//new Ingredient[1];
	private String name = "";
	String nic = "A faggot";
	String tim = "nic++";
	private double totalPercent = 0;
	private int totalParts = 0;
	
	
	public void addIngredient(Ingredient _ingr) {
		//Modify percent and part amounts.
		totalParts +=_ingr.getParts();
		totalPercent +=_ingr.getPercent()*_ingr.getParts();
		//Add ingredient to final list.
		ingr.add(_ingr);
	}
	
	public void subtractIngredient(Ingredient _ingr, int i) {
		//Modify percent and part amounts.
		totalParts-=_ingr.getParts();
		totalPercent-=_ingr.getPercent()*_ingr.getParts();
		//Remove ingredient from list.
		ingr.remove(i);
	}
	
	public void editIngredient(Ingredient newIngr, int i) {
		subtractIngredient(ingr.get(i), i);
		ingr.set(i, newIngr);
		addIngredient(ingr.get(i));
	}
	
	public void calcTotals() {
		for( int i = 0; i < ingr.size(); i++) {
			totalParts += ingr.get(i).getParts();
			totalPercent += ingr.get(i).getParts()*ingr.get(i).getPercent();
		}
	}
	
	
	
	//Getters & setters
	public void setname(String _name) {
		name = _name;
	}
	public String getName() {
		return name;
	}
	public double getPercent(){
		return totalPercent;
	}
	public double getParts() {
		return totalParts;
	}
}
