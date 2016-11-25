package ru.khabtech.egomaniack;
import java.io.Serializable;

public class Profile implements Serializable{

	private String name;
	private String surname;
	private int simbols;
	
	
	public String getName() {
		return name;
	}
	public int getSimbols() {
		simbols = name.length() + surname.length();
		return simbols;
	}
	public void setSimbols(int simbols) {
		this.simbols = simbols;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSirname() {
		return surname;
	}
	public void setSurname(String sirname) {
		this.surname = sirname;
	}
	
	
}
