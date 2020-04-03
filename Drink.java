package 自販機;

public class Drink {
	private String name;
	private int number;
	private int price;
	private isAlcohol isAlcohol;

	public String getName() {
		return this.name;
	}
	public int getNumber() {
		return this.number;
	}
	public int getPrice() {
		return this.price;
	}
	public isAlcohol getIsAlcohol(){
		return this.isAlcohol;
	}

	public Drink(String name, int number, int price, isAlcohol isAlcohol) {
		this.name = name;
		this.number = number;
		this.price = price;
		this.isAlcohol = isAlcohol;
	}
}
