package 自販機;

public class Drink {
	private String name;
	private int number;
	private int price;
	private IsAlcohol isAlcohol;

	public final String getName() {
		return this.name;
	}
	public final int getNumber() {
		return this.number;
	}
	public final int getPrice() {
		return this.price;
	}
	public final IsAlcohol getIsAlcohol(){
		return this.isAlcohol;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Drink(final String name, final int price, final IsAlcohol isAlcohol) {
		this.name = name;
		this.price = price;
		this.isAlcohol = isAlcohol;
    }
}

