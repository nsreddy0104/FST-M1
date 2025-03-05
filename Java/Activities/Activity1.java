package activities;

public class Activity1 {

	public static void main(String[] args) {
		
		Car car= new Car("Red","Manual",2020);
		
		car.displayCharacteristics();
		car.accelarate();
		car.brake();
	}
}
