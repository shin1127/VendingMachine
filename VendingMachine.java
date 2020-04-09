package 自販機;

import static 自販機.IsAlcohol.*;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    public static void main(final String[] args) {


        ArrayList<Drink> drinklist = new ArrayList<>();  // 商品のクラスを入れる
        int minPrice = 0;  // 商品価格の最小値
	    int currentMoney = 0;  // 自販機内の残金
		int buyContinue = 0;  // 続けて購入してもらうかどうか決定
		int choiceType = 0;  // 購入時の商品番号の選択
		boolean buyAbilityForAge = false;  // 購入可能な年齢に達しているか判定
		Scanner sc = new Scanner(System.in);

		MachineLogic logic = new MachineLogic();


		// 商品を設定し、リストに追加する

		drinklist.add(new Drink("コーヒー", 130, SOFTDRINK));
		drinklist.add(new Drink("水",100, SOFTDRINK));
		drinklist.add(new Drink("コーラ", 160, SOFTDRINK));
		drinklist.add(new Drink("お茶", 120, SOFTDRINK));
		drinklist.add(new Drink("レッドブル", 200, SOFTDRINK));
		drinklist.add(new Drink("ビール", 200, ALCOHOL));

		// 商品番号を割り振る
		for (int i = 0; i < drinklist.size(); i++) {
			drinklist.get(i).setNumber(i + 1);
		}


		// 商品価格の最小値を決定

		minPrice = logic.searchMin(drinklist, minPrice);


		// ここから自販機の動作

		// お金を投入してもらう

		logic.machineSay(0);
		currentMoney = logic.inputNumber(0, 0, sc);


		// 初期値0か、「1.続けて購入」を選択する限り購入動作を続ける

		while (buyContinue == 0 || buyContinue == 1){

			// 投入金額が商品の最安値を下回れば終了
			if (currentMoney - minPrice < 0) {
				break;
			}

			// 飲み物のリストを表示する

			logic.displayProduct(drinklist);

			// 商品を選択してもらい購入処理をする 商品番号以外なら例外処理

			logic.machineSay(1);
			choiceType = logic.inputNumber(1, drinklist.size(), sc);

			// 選択した商品が購入可能か年齢から判定する

			buyAbilityForAge =
					logic.buyAbilityForAge(drinklist, choiceType - 1, sc);

			// 選択した商品に対して購入可能年齢に達していれば購入処理を試みる

			if (buyAbilityForAge == true) {

				// 選択した商品が購入可能か残金から判定し、残高を更新
				currentMoney = logic.buyProduct(currentMoney, drinklist, choiceType - 1);
			}
			else {
				System.out.println("年齢制限の為、選択した商品は購入できません。");
			}

			// 続けて購入できるか判定

			if (currentMoney - minPrice < 0) {

				// 続けて購入するかどうか決定してもらう

				logic.machineSay(2);
				buyContinue = logic.inputNumber(2, 0, sc);
			}
			// 残金不足のときの表示

		}  // ループ終了

		if (currentMoney - minPrice < 0) {
			System.out.println("残金が足りません。");
		}

		// 自販機動作終了時の表示


		System.out.println(
				"お釣り" + currentMoney + "円を返金します。"
				+ "ご利用ありがとうございました。");

		sc.close();
	}

}
