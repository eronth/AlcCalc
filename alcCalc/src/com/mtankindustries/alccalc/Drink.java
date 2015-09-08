package com.mtankindustries.alccalc;

import java.io.Serializable;
import java.util.LinkedList;

public class Drink implements Serializable {
	private static final long serialVersionUID = -8796991257326593544L;
	private LinkedList<Ingredient> ingredient = new LinkedList<Ingredient>();
	private LinkedList<Integer> volume = new LinkedList<Integer>();//new Ingredient[1];
	private String drinkName = "";
	
	public void addIngredient(Ingredient ingr, Integer volume) {
		//Add ingredient to final list.
		ingredient.add(ingr);
		this.volume.add(volume);
	}
	
	public void removeIngredient(Ingredient ingr, int i) {
		//Remove ingredient from list.
		ingredient.remove(i);
		volume.remove(i);
	}
	
	public Double getPercent() {
		int totalVolume = 0;
		int alcoholVolume = 0;
		for (int i = 0; i<ingredient.size(); i++) {
			totalVolume+=volume.get(i);
			alcoholVolume+=volume.get(i)*ingredient.get(i).getPercent();
		}
		return (double)(alcoholVolume)/(double)totalVolume;
	}
	
	public Integer getVolume() {
		int totalVolume = 0;
		for (int v : volume) {
			totalVolume += v;
		}
		return totalVolume;
	}
	
	//Getters & setters
	public void setname(String name) {
		this.drinkName = name;
	}
	public String getName() {
		return drinkName;
	}
}
