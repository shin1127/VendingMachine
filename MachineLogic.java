package 自販機;

import static 自販機.IsAlcohol.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MachineLogic {

	public void machineSay(int pattern) {

		final String pattern0 = "お金を投入してください";
		final String pattern1 = "購入したい商品の番号を入力してください";
		final String pattern2 = "続けて購入しますか？\n1.続けて購入する 2.お釣りを出す";

		String SayWord;

		switch (pattern) {
		case 0:
			SayWord = pattern0;
			break;
		case 1:
			SayWord = pattern1;
			break;
		default:
			SayWord = pattern2;
		}

		System.out.println(SayWord);
	}


	// 数字を入力してもらうメソッド
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
				if (inputNumber % 10 == 0  && inputNumber > 0) {
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
			// sc = new Scanner(System.in);
			sc.nextLine();  // 入力された内容をすべて読み込む
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

		for (Drink drink : drinklist) {
			minPrice = Math.min(minPrice, drink.getPrice());
		}
		return minPrice;
	}

	// 選択した番号の商品が購入可能か判定し、残高更新を含む購入処理を行う
	public int buyProduct(int currentMoney, ArrayList<Drink> drinklist, int choiceNumber) {

		int price = drinklist.get(choiceNumber).getPrice();
		String productName = drinklist.get(choiceNumber).getName();
		if (currentMoney >= price) {
			currentMoney -= price;
			System.out.println(productName + "を購入しました。");
		}else {
			System.out.println("残金が足りません。他の商品を選んでください。");
		}
		return currentMoney;
	}


	// アルコール飲料が選択された時、年齢確認を行い20歳未満なら購入処理をスキップする
	public boolean buyAbilityForAge(ArrayList<Drink> drinklist, int choiceType, Scanner sc) {
		int inputNumber = 0;
		IsAlcohol isAlcohol = drinklist.get(choiceType).getIsAlcohol();
		if (isAlcohol == ALCOHOL) {
			System.out.println(
					"選択した商品はアルコール飲料です。\n20歳以上ですか？"
					+ "1.はい 2.いいえ");

			while (true) {
				try {
					sc = new Scanner(System.in);
					inputNumber = sc.nextInt();
				}catch (Exception e) {
				System.out.println("数字以外が入力されました。");
				}
				if (inputNumber == 1 || inputNumber == 2) {
					break;
				}
				System.out.println("無効な入力です。"
						+ "\nもう一度入力してください>> ");
				sc = new Scanner(System.in);

			}
			// 20歳以上の"1"が選択された
			if (inputNumber == 1) {
				return true;
			}
			// 20歳未満の"2"が選択された
			else {
				return false;
			}
		}
		// ソフトドリンクが選択された時の処理
		else {
			return true;
		}
	}

}
