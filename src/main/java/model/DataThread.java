package model;

import java.io.Serializable;
import java.util.ArrayList;

public class DataThread extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;

	Model m;
	transient Thread t;
	public DataThread(Model m) {
		this.m = m;
		t = new Thread(this, "Thread");
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
