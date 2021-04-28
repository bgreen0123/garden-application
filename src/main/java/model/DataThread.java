package model;

import java.util.ArrayList;

public class DataThread implements Runnable{
	Model m;
	Thread t;
	public DataThread(Model m) {
		this.m = m;
		t = new Thread(this, "Thread");
		t.start();
	}
	@Override
	public void run() {
		System.out.println("START THREAD");
		ArrayList<Plant> herb = m.getData().getHerbacious();
		ArrayList<Plant> wood = m.getData().getWoody();
		
		herb.forEach(p -> p.makeImageView());
		wood.forEach(p -> p.makeImageView());
		System.out.println("END THREAD");
	}

}
