package BoardLogic;

import javax.swing.JPanel;

public class BoardHelper {
	protected static int convertIndex(String position) {
		int index = 0;
			
		switch(position) {
	
			case "00": index = 1;
				break;
			case "01": index = 2;
				break;
			case "02": index = 3;
				break;
			case "03": index = 4;
				break;
			case "04": index = 5;
				break;
			case "05": index = 6;
				break;
			case "06": index = 7;
				break;
			case "10": index = 9;
				break;
			case "11": index = 10;
				break;
			case "12": index = 11;
				break;
			case "13": index = 12;
				break;
			case "14": index = 13;
				break;
			case "15": index = 14;
				break;
			case "16": index = 15;
				break;
			case "20": index = 17;
				break;
			case "21": index = 18;
				break;
			case "22": index = 19;
				break;
			case "23": index = 20;
				break;
			case "24": index = 21;
				break;
			case "25": index = 22;
				break;
			case "26": index = 23;
				break;
			case "30": index = 25;
				break;
			case "31": index = 26;
				break;
			case "32": index = 27;
				break;
			case "33": index = 28;
				break;
			case "34": index = 29;
				break;
			case "35": index = 30;
				break;
			case "36": index = 31;
				break;
			case "40": index = 33;
				break;
			case "41": index = 34;
				break;
			case "42": index = 35;
				break;
			case "43": index = 36;
				break;
			case "44": index = 37;
				break;
			case "45": index = 38;
				break;
			case "46": index = 39;
				break;
			case "50": index = 41;
				break;
			case "51": index = 42;
				break;
			case "52": index = 43;
				break;
			case "53": index = 44;
				break;
			case "54": index = 45;
				break;
			case "55": index = 46;
				break;
			case "56": index = 47;
				break;
			case "60": index = 49;
				break;
			case "61": index = 50;
				break;
			case "62": index = 51;
				break;
			case "63": index = 52;
				break;
			case "64": index = 53;
				break;
			case "65": index = 54;
				break;
			case "66": index = 55;
				break;
			default:
		}
		
		return index;
	}

public static String switchTurn(JPanel congoBoard, String turn) {
		
//		System.out.println("Turn before change: " + turn);

		if(turn == "W") {
			turn = "B";


		}
		else if(turn == "B") {
			turn = "W";

		}
		return turn;
//		System.out.println("Turn after change: " + turn);
	}
protected static int findRow(int parentLocationY) {
	return parentLocationY/70;
}
protected static int findColumn(int parentLocationX) {
	return parentLocationX/70;
}
 
}