package 自販機;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
	public static void main(String[] args) {


		ArrayList<Drink> drinklist = new ArrayList<>();  // 商品のクラスを入れる
		int minPrice = 0;  // 商品価格の最小値
		int currentMoney = 0;  // 自販機内の残金
		boolean buyAbility = false; // 続けて購入可能かを判定
		int buyContinue = 0;
		int choiceType = 0;  // 購入時の商品番号の選択
		Scanner sc = new Scanner(System.in);


		//商品を設定し、リストに追加する

        Drink coffee = new Drink("コーヒー", 1, 130);
        drinklist.add(coffee);

        Drink water = new Drink("水", 2, 100);
        drinklist.add(water);

        Drink coke = new Drink("コーラ", 3, 160);
        drinklist.add(coke);

        Drink tea = new Drink("お茶", 4, 120);
        drinklist.add(tea);

        Drink redbull = new Drink("レッドブル", 5, 200);
        drinklist.add(redbull);


		// 商品価格の最小値を決定

		minPrice = searchMin(drinklist, minPrice);


		// ここから自販機の動作

		// お金を投入する

		System.out.println("お金を投入してください");
		currentMoney = inputNumber(0, 0, sc);


		// 投入金額が商品の最安値を上回っているか判定

		buyAbility = buyAbility(currentMoney, minPrice);

		// 初期値0か、「1.続けて購入」を選択する限り購入動作を続ける

		while(buyContinue == 0 || buyContinue == 1){

			// 投入金額が商品の最安値を上回ってなければ終了
			if(buyAbility == false) {
				break;
			}

			// 飲み物のリストを表示する

			displayProduct(drinklist);

			// 商品を選択し購入 商品番号以外なら例外処理

			System.out.println("購入したい商品の番号を入力してください");
			choiceType = inputNumber(1, drinklist.size(), sc);

			// 選択した商品が購入可能か判定し、残高を更新

			currentMoney = buyProduct(currentMoney, drinklist, choiceType - 1);

			// 続けて購入できるか判定

			buyAbility = buyAbility(currentMoney, minPrice);

			if (buyAbility == true) {

				// 続けて購入するか選択 1,2以外なら例外処理

				System.out.println("続けて購入しますか？\n1.続けて購入する 2.お釣りを出す");

				buyContinue = inputNumber(2, 0, sc);
			}
			// 残金不足のときの表示

		}  // ループ終了

		if (buyAbility == false) {
			System.out.println("残金が足りません。");
		}

		// 自販機動作終了時の表示

		System.out.println("お釣り" + currentMoney + "円を返金します。ご利用ありがとうございました。");

		sc.close();

	}

	// 自販機の数字操作メソッド
	public static int inputNumber(int pattern, int productSize, Scanner sc) {
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
	public static void displayProduct(ArrayList<Drink> drinklist) {
		for(Drink drink : drinklist) {
			System.out.println(drink.getNumber() + "." + drink.getName()
					+ drink.getPrice() + "円");
		}
	}

	// 商品価格の最小値を求める
	public static int searchMin(ArrayList<Drink> drinklist, int minPrice) {

		minPrice = drinklist.get(0).getPrice();

		for(Drink drink : drinklist) {
			minPrice = Math.min(minPrice, drink.getPrice());
		}
		return minPrice;
	}

	// 残金が商品最安値を上回るか判定
	public static boolean buyAbility(int currentMoney, int minPrice) {
		boolean buyAbility = currentMoney >= minPrice;
		return buyAbility;
	}

	// 選択した番号の商品が購入可能か判定し、残高更新を含む購入処理を行う
	public static int buyProduct(int currentMoney, ArrayList<Drink> drinklist, int choiceNumber) {
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
