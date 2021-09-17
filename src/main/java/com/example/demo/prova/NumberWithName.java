package com.example.demo.prova;

public class NumberWithName {

	private Integer n;
	private String name;
	
	public NumberWithName(Integer n, String name) {
		this.n = n;
		this.name = name;
	}
	
	public NumberWithName() {
		super();
	}
	
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "NumberWithName [n=" + n + ", name=" + name + "]";
	}
	
	
	
	
	
	
}
