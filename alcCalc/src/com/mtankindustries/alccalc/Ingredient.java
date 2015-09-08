package com.mtankindustries.alccalc;

public class Ingredient {
	private 
	String name = "";
	double percent = 0.0;
	
	public
	Ingredient () {
		
	}
	Ingredient(String name, double percent) {
		setName(name);
		setPercent(percent);
	}
	Ingredient(Ingredient ingr) {
		setName(ingr.getName());
		setPercent(ingr.getPercent());
	}
	
	//Getters
	String getName() {
		return name;
	}
	double getPercent() {
		return percent;
	}
	//Setters
	void setName( String newName) {
		name = newName;
	}
	void setPercent( double newPercent ) {
		percent = newPercent;
	}
	
	@Override
	public String toString() {
		return name+" ("+percent+"%)";
	}
}
