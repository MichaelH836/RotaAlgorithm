package algorithm;

import java.util.ArrayList;

public class RotaAlgorithm {

	
	static int[][] finalRota = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

	public static void main(String[] args) {
		ArrayList<int[][]> list = new ArrayList<>();
		int[][] mike = new int[][] { { 1, 1, 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0 } };
		list.add(mike);
		int[][] aine = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 } };
		list.add(aine);
		int[][] michMc = new int[][] { { 1, 1, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		list.add(michMc);
		int[][] brenda = new int[][] { { 0, 0, 1, 1, 0, 1, 1 }, { 0, 1, 1, 0, 1, 1, 0 } };
		list.add(brenda);
		int[][] michH = new int[][] { { 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		list.add(michH);
		int[][] roisin = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 1, 0, 1 } };
		list.add(roisin);
		int[][] john = new int[][] { { 0, 1, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 0, 1 } };
		list.add(john);
		int[][] meabh = new int[][] { { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 } };
		list.add(meabh);
		int[][] patrick = new int[][] { { 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		list.add(patrick);
		int[][] maura = new int[][] { { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 1, 0 } };
		list.add(maura);
		int[][] bronagh = new int[][] { { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 1, 0, 1 } };
		list.add(bronagh);

		ArrayList<Integer> days = new ArrayList<>();
		ArrayList<Integer> nights = new ArrayList<>();
		int[] totals = new int[list.size()];
		ArrayList<Integer> onlyDayList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			boolean doesNights = false;
			totals[i] = totalList(totals);
			for (int j = 0; j < 7; j++) {
				if (i == 0) {
					days.add(0);
					nights.add(0);
				}
				if (list.get(i)[1][j] != 0 && doesNights == false) {
					doesNights = true;
				}
				if (list.get(i)[0][j] == 1)
					days.set(j, days.get(j) + 1);
				if (list.get(i)[1][j] == 1)
					nights.set(j, nights.get(j) + 1);
			}
			if (!doesNights) {
				onlyDayList.add(i);
			}
		}
		boolean loop = true;
		if (days.contains(0)) System.exit(0);;
		System.out.println(days);
		System.out.println(nights);
		int[][] currentRota = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		while (true) {
			int minDay = minList(days);
			if (minDay == -1) break;
			System.out.println(minDay);
			int minNight = minList(nights);
			boolean assigned = false;
			if (!onlyDayList.isEmpty()) {
				for (int i = 0; i < onlyDayList.size(); i++) {
					if (list.get(onlyDayList.get(i))[0][minDay] == 1) {
						System.out.println("miguel");
						currentRota[0][minDay] = onlyDayList.get(i);
						assigned = true;
						days.set(minDay, -1);
						onlyDayList.remove(i);
						System.out.println(onlyDayList);
						break;
					}
				}
				if (!assigned) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i)[0][minDay] == 1) {
							currentRota[0][minDay] = i;
							days.set(minDay, -1);
							break;
						}
					}
				}
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i)[0][minDay] == 1) {
						currentRota[0][minDay] = onlyDayList.get(i);
						break;
					}
				}
			}
		}
		finalRota = currentRota;
		printList(finalRota);
		
	}

	private static int minList(ArrayList<Integer> list) {
		int minimumIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != -1) {
				minimumIndex = i;
				break;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != -1 && list.get(i) < list.get(minimumIndex))
				minimumIndex = i;
		}
		return minimumIndex;
	}

	private static int totalList(int[] list) {
		int total = 0;
		for (int i = 0; i < list.length; i++)
			total += list[i];
		return total;
	}
	
	private static void printList(int[][] list) {
		for (int i = 0; i < 2; i++) {
			String word = "";
			for (int num : list[i]) word += (num + ", ");
			System.out.println(word);
		}
	}
}