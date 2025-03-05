package activities;

public class Car {

	String color;
	String transmission;
	int make;
	int tyres = 4;
	int doors = 4;
	
	Car(String color, String transmission, int make){
		this.color=color;
		this.transmission=transmission;
		this.make=make;
		
	}
	
	public void displayCharacteristics(){
		System.out.println("Color of the car is : "+this.color);
		System.out.println("Transmission type is : "+this.transmission);
		System.out.println("Making year of the car is : "+this.make);
		System.out.println("Number of doors : "+this.doors);
		System.out.println("Number of Tyres : "+this.tyres);
		
	}
	public void accelarate() {
		System.out.println("Car is moving forward");
	}
	
	public void brake() {
		System.out.println("Car has been stopped");
	}
}
