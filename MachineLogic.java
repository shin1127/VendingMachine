package 自販機;

import java.util.ArrayList;
import java.util.Scanner;

public class MachineLogic {


	// 自販機の数字操作メソッド
	public int inputNumber(int pattern, int productSize, Scanner sc) {
		int inputNumber = 0;
		while(true) {
			try {
				sc = new Scanner(System.in);
				inputNumber = sc.nextInt();
			}catch (Exception e) {
				System.out.println("数字以外が入力されました。");

			}
			// お金を投入する場合
			if (pattern == 0) {
				if(inputNumber % 10 == 0  && inputNumber > 0) {
					break;
				}
			}
			// 商品番号を選択する場合
			if (pattern == 1) {
				if (inputNumber > 0 && inputNumber <= productSize) {
					break;
				}
			}
			// 続けて購入するか選択する場合
			if (pattern == 2) {
				if (inputNumber == 1 || inputNumber == 2) {
					break;
				}
			}
			System.out.print("無効な入力です。\nもう一度入力してください\n>>");
			sc = new Scanner(System.in);
		}
		return inputNumber;
	}

	// 商品リストを表示する
	public void displayProduct(ArrayList<Drink> drinklist) {
		for(Drink drink : drinklist) {
			System.out.println(drink.getNumber() + "." + drink.getName()
					+ drink.getPrice() + "円");
		}
	}

	// 商品価格の最小値を求める
	public int searchMin(ArrayList<Drink> drinklist, int minPrice) {

		minPrice = drinklist.get(0).getPrice();

		for(Drink drink : drinklist) {
			minPrice = Math.min(minPrice, drink.getPrice());
		}
		return minPrice;
	}

	// 残金が商品最安値を上回るか判定
	public boolean buyAbility(int currentMoney, int minPrice) {
		boolean buyAbility = currentMoney >= minPrice;
		return buyAbility;
	}

	// 選択した番号の商品が購入可能か判定し、残高更新を含む購入処理を行う
	public int buyProduct(int currentMoney, ArrayList<Drink> drinklist, int choiceNumber) {
		int price = drinklist.get(choiceNumber).getPrice();
		String productName = drinklist.get(choiceNumber).getName();
		if (currentMoney >= price) {
			currentMoney -= price;
			System.out.println(productName + "を購入しました。");
		}
		else {
			System.out.println("残金が足りません。他の商品を選んでください。");
		}
		return currentMoney;
	}
}
