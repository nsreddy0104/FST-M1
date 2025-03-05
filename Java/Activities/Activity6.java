package activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Plane {
	private List<String> passangers;
	private int maxPassangers;
	private Date lastTakeOffTime;
	private Date lastLandingTime;
	
	public Plane(int maxPassangers) {
		this.maxPassangers=maxPassangers;
		this.passangers=new ArrayList<String>();
	}
	
	public void onboard(String passengerName) {
		if(passangers.size()<=maxPassangers) {
			this.passangers.add(passengerName);
		}else {
			System.out.println("Plane is full");
		}
	}

	public void setTakeOff() {
		this.lastTakeOffTime=new Date();
	}
	
	public Date getTakeOffTime() {
		return this.lastTakeOffTime;
	}
	
	public void setLand(){
		this.lastLandingTime=new Date();
		this.passangers.clear();
	}
	public Date getLastTimeLanded() {
		return this.lastLandingTime;
	}
	
	public List<String> getPassengers(){
		return this.passangers;
	}
}

public class Activity6{
	public static void main(String[] args) throws InterruptedException {
		Plane b747 = new Plane(10);
		b747.onboard("ABC");
		b747.onboard("ABCD");
		b747.onboard("ABCDE");
		
		b747.setTakeOff();
		System.out.println("Plane Took off at "+b747.getTakeOffTime());
		System.out.println("Passengers List :"+b747.getPassengers());
		
		System.out.println("Flying");
		Thread.sleep(3000);
		
		b747.setLand();
		System.out.println("Plane Landed : "+b747.getLastTimeLanded());
		System.out.println("Passengers List :"+b747.getPassengers());
	}
}
