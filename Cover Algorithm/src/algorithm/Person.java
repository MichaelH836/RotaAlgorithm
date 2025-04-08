package algorithm;

public class Person {

	private int id;
	private String name;
	private boolean doesNights = false;
	private int[][] availability;
	private int max = 1;
	private int timesOn;

	private static int nextId = 0;

	public Person(String name, int[][] availability) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		
		for (int i : this.availability[1])
			if (i == 1) {
				this.doesNights = true;
				max = 2;
			}
	}
	
	public Person(String name, int[][] availability, int maxDays) {
		this.name = name;
		this.availability = availability;
		this.id = nextId;
		this.nextId++;
		
		for (int i : this.availability[1])
			if (i == 1) {
				this.doesNights = true;
				max = 2;
			}
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
