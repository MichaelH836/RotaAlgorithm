package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class RotaAlgorithm {

	private Person[][] finalRota = new Person[][] { { null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null } };
	
	public RotaAlgorithm(ArrayList<Person> employees, int noOfDays) {
		ArrayList<Person> list = employees;
		Scanner scanner = new Scanner(System.in);

		boolean[][] dayPreferences = new boolean[2][noOfDays];
		ArrayList<Person> hasPreferredDays = new ArrayList<>();

		ArrayList<Integer> days = new ArrayList<>();
		ArrayList<Integer> nights = new ArrayList<>();
		int[] totals = new int[list.size()];
		ArrayList<Person> onlyDayList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			boolean doesNights = false;
			totals[i] = totalList(totals);
			int[][] prefDays = list.get(i).getPreferredDays();
			if (prefDays != null && prefDays.length != 0) {
				for (int k = 0; k < prefDays.length; k++) {
					for (int j = 0; j < prefDays[k].length; j++) {
						dayPreferences[k][prefDays[k][j]] = true;
						hasPreferredDays.add(list.get(i));
					}
				}
			}
			for (int j = 0; j < noOfDays; j++) {
				if (i == 0) {
					days.add(0);
					nights.add(0);
				}
				if (list.get(i).getDays()[j] == 1)
					days.set(j, days.get(j) + 1);
				if (list.get(i).getNights()[j] == 1)
					nights.set(j, nights.get(j) + 1);
			}
			if (!list.get(i).doesNights())
				onlyDayList.add(list.get(i));
		}
		boolean loop = true;
		if (days.contains(0))
			System.exit(0);
		Person[][] currentRota = new Person[2][noOfDays];
		while (true) {
			int minDay = minList(days);
			if (minDay == -1)
				break;
			int assigned = -1;
			if (dayPreferences[0][minDay]) {
				for (Person p : hasPreferredDays) {
					if (p.getDays()[minDay] == 1 && !p.isMax()) {
						assigned = p.getId();
						currentRota[0][minDay] = p;
						p.addTime();
						days.set(minDay, -1);
						break;
					}
				}
			} else if (!onlyDayList.isEmpty()) {
				for (int i = 0; i < onlyDayList.size(); i++) {
					Person p = onlyDayList.get(i);
					if (p.getDays()[minDay] == 1 && !p.isMax()) {
						assigned = p.getId();
						currentRota[0][minDay] = p;
						p.addTime();
						days.set(minDay, -1);
						onlyDayList.remove(i);
						break;
					}
				}
				if (assigned == -1) {
					for (int i = 0; i < list.size(); i++) {
						Person p = list.get(i);
						if (p.getDays()[minDay] == 1 && !p.isMax()) {
							currentRota[0][minDay] = p;
							p.addTime();
							days.set(minDay, -1);
							break;
						}
					}
				}
			} else {
				Person minPerson = null;
				int minTimes = -1;
				for (int i = 0; i < list.size(); i++) {
					Person p = list.get(i);
					if (p.getDays()[minDay] == 1 && !p.isMax()) {
						if (p.getTimesOn() < minTimes || minTimes == -1) {
							minTimes = p.getTimesOn();
							minPerson = p;
						}
					}
				}
				if (minPerson != null) {
					currentRota[0][minDay] = minPerson;
					days.set(minDay, -1);
				}
			}
		}
		for (int i = 0; i < noOfDays; i++) {
			int minNight = minList(nights);
			int minTimes = -1;
			Person minPerson = null;
			int assigned = -1;
			if (dayPreferences[1][minNight]) {
				for (Person p : hasPreferredDays) {
					if (p.getNights()[minNight] == 1 && !p.isMax()) {
						assigned = p.getId();
						currentRota[1][minNight] = p;
						p.addTime();
						nights.set(minNight, -1);
						break;
					}
				}
			} else {
				for (int j = 0; j < list.size(); j++) {
					Person p = list.get(j);
					if (p.getNights()[minNight] == 1 && !p.isMax()) {
						if (p.getTimesOn() < minTimes || minTimes == -1) {
							minTimes = p.getTimesOn();
							minPerson = p;
						}
					}
				}
				if (minPerson != null) {
					currentRota[1][minNight] = minPerson;
					minPerson.addTime();
					nights.set(minNight, -1);
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

	private static void printList(Person[][] list) {
		for (int i = 0; i < 2; i++) {
			String word = "";
			for (Person per : list[i])
				if (per == null)
					word += "null, ";
				else
					word += (per.getName() + ", ");
			System.out.println(word);
		}
	}
}