package com.mmandava.mallik;

public class peoples {
	
	private String  fName;
	private String lName;
	private int Age;
	
	public peoples(String fName, String lName, int age) {
		super();
		this.fName = fName;
		this.lName = lName;
		Age = age;
	}
	@Override
	public String toString() {
		return "peoples [Age=" + Age + ", fName=" + fName + ", lName=" + lName + "]";
		
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
}
