package com.mygdx.monopoly;

import java.util.ArrayList;

/**
 * Class designed to group properties based on color
 * Mainly used for station and utility properties to calculate rent
 */
public class PropertyGroup {

	private ArrayList<Property> properties = new ArrayList<Property>();
	private String name;
	
	public PropertyGroup(Property one, Property two, String name) {
		properties.add(one);
		properties.add(two);
		this.name = name;
	}
	
	public PropertyGroup(Property one, Property two, Property three, String name) {
		properties.add(one);
		properties.add(two);
		properties.add(three);
		this.name = name;
	}
	
	public PropertyGroup(Property one, Property two, Property three, Property four, String name) {
		properties.add(one);
		properties.add(two);
		properties.add(three);
		properties.add(four);
		this.name = name;
	}

	public int checkOwned(Player owner) {
		int n = 0;
		for (int i = 0; i < properties.size(); i++) {
			if (properties.get(i).getOwner() == owner)
				n++;
		}
		return n;
	}
	
	public boolean checkAllOwned(Player owner) {
		for (int i = 0; i < properties.size(); i++) {
			if (properties.get(i).getOwner() != owner)
				return false;
		}
		return true;
	}
}
