package com.mtankindustries.alccalc;

public class Ingredient {
	private 
	String name = "";
	double percent = 0.0;
	int parts = 0;
	
	public
	Ingredient () {
		
	}
	Ingredient(String iName, double iPercent, int iParts) {
		setName(iName);
		setPercent(iPercent);
		setParts(iParts);
	}
	Ingredient(Ingredient ingr) {
		setName(ingr.getName());
		setPercent(ingr.getPercent());
		setParts(ingr.getParts());
	}
	
	
	
	//Getters
	String getName() {
		return name;
	}
	double getPercent() {
		return percent;
	}
	int getParts() {
		return parts;
	}
	//Setters
	void setName( String newName) {
		name = newName;
	}
	void setPercent( double newPercent ) {
		percent = newPercent;
	}
	void setParts( int newParts ) {
		parts = newParts;
	}
}
