package 自販機;

public class Drink {
	private String name;
	private int number;
	private int price;

	public String getName() {
		return this.name;
	}
	public int getNumber() {
		return this.number;
	}
	public int getPrice() {
		return this.price;
	}

	public Drink(String name, int number, int price) {
		this.name = name;
		this.number = number;
		this.price = price;
	}
