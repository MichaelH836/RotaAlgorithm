package algorithm;

public class Person {

	private int id;
	private String name;
	private boolean doesNights = false;
	private int[][] availability;
	private int max = 1;
	private int timesOn;
	private int[][] preferredDays;

	private static int nextId = 0;

	public Person(String name, int[][] availability, int[][] preferredTimes) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		this.preferredDays = preferredTimes;
		if (this.availability.length > 1) {
			for (int i : this.availability[1])
				if (i == 1) {
					this.doesNights = true;
					max = 2;
				}
		}
	}

	public Person(String name, int[][] availability, int maxTimes, int[][] preferredTimes) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		this.max = maxTimes;
		this.preferredDays = preferredTimes;
		if (this.availability.length > 1) {
			for (int i : this.availability[1])
				if (i == 1) {
					this.doesNights = true;
					max = 2;
				}
		}
	}
	
	public Person(String name, int[][] availability) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		this.preferredDays = null;
		if (this.availability.length > 1) {
			for (int i : this.availability[1])
				if (i == 1) {
					this.doesNights = true;
					max = 2;
				}
		}
	}

	public Person(String name, int[][] availability, int maxTimes) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		this.max = maxTimes;
		this.preferredDays = null;
		if (this.availability.length > 1) {
			for (int i : this.availability[1])
				if (i == 1) {
					this.doesNights = true;
					max = 2;
				}
		}
	}
	
	public int[][] getPreferredDays() {
		return this.preferredDays;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getTimesOn() {
		return this.timesOn;
	}

	public int getMax() {
		return this.max;
	}

	public void addTime() {
		this.timesOn++;
	}

	public boolean isMax() {
		return this.max <= this.getTimesOn();
	}

	public int[][] getAvailability() {
		return this.availability;
	}

	public int[] getDays() {
		return this.getAvailability()[0];
	}

	public int[] getNights() {
		return this.getAvailability()[1];
	}

	public boolean doesNights() {
		return this.doesNights;
	}

}
