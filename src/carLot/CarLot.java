package carLot;

import java.util.ArrayList;
import java.util.Collections;

public class CarLot {
	private ArrayList<Car> inventory = new ArrayList<Car>();
	private int numberOfCars = 0;
	private int capacity = 0;

	public CarLot() {
		this(100);
	}

	public CarLot(int capacity) {
		this.capacity = capacity;
		this.inventory = new ArrayList<Car>();
		this.inventory.ensureCapacity(capacity);
	}

	public void addCar(String id, int mileage, int mpg, double cost, double salesPrice) {
		if (numberOfCars < capacity) {
			this.inventory.add(numberOfCars, new Car(id, mileage, mpg, cost, salesPrice));
			numberOfCars++;
		}
	}

	// updated getInventory() to return copy of this.inventory as ArrayList<Car>
	// using .forEach, internal iterator
	public ArrayList<Car> getInventory() {
		ArrayList<Car> allCars = new ArrayList<Car>();
		this.inventory.forEach(e -> allCars.add(e));
		return allCars;
	}
	
	public ArrayList<Car> returnSoldCars() {
		ArrayList<Car> soldCars = new ArrayList<Car>();
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			if (aCar.isSold() != false) {
				soldCars.add(this.inventory.get(i));
			}
		}
		return soldCars;	
	}

	public Car findCarByIdentifier(String identifier) {
		for (Car car : this.inventory) {
			if (car.getId().equals(identifier)) {
				return car;
			}
		}
		return null;
	}

	public void sellCar(String identifier, double priceSold) throws IllegalArgumentException {
		Car aCar = this.findCarByIdentifier(identifier);
		if (aCar != null) {
			aCar.sellCar(priceSold);
		} else {
			throw new IllegalArgumentException("No car with identifier " + identifier);
		}
	}

	public ArrayList<Car> getCarsInOrderOfEntry() {
		return this.inventory;
	}

	public ArrayList<Car> getCarsSortedByMPG() {
		ArrayList<Car> allCars = new ArrayList<>();
		for (int i = 0; i < numberOfCars; i++) {
			allCars.add(this.inventory.get(i));
		}
		Collections.sort(allCars, (Car c1, Car c2) -> c2.compareMPG(c1));
		return allCars;
	}

	public Car getCarWithBestMPG() {
		Car rtn = null;
		int bestMpg = -1;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			if (aCar.getMpg() > bestMpg) {
				bestMpg = aCar.getMpg();
				rtn = aCar;
			}
		}
		return rtn;
	}

	public Car getCarWithHighestMileage() {
		Car rtn = null;
		int highestMileage = -1;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			if (aCar.getMileage() > highestMileage) {
				highestMileage = aCar.getMileage();
				rtn = aCar;
			}
		}
		return rtn;
	}

	public double getAverageMpg() {
		double totalMpg = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			totalMpg += aCar.getMpg();
		}
		return totalMpg / this.numberOfCars;
	}

	public double getTotalProfit() {
		double profit = 0D;
		for (int i = 0; i < numberOfCars; i++) {
			Car aCar = this.inventory.get(i);
			profit += (aCar.isSold() ? aCar.getProfit() : 0);
		}
		return profit;
	}

}
