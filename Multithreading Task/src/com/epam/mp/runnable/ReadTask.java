package com.epam.mp.runnable;

import com.epam.mp.entity.User;
import com.epam.mp.main.Main;

public class ReadTask implements Runnable {

	private User user;
	private int count;

	public ReadTask(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		count++;
		if (count > 2) {
			user = Main.generateNewUser();
		}
		Main.logList.add(Thread.currentThread().getName() + " *READ THREAD "
				+ count + "* is reading the user with id " + user.getId()
				+ " ;firstname " + user.getFirstName() + " ;lastname "
				+ user.getLastName() + "\r\n");
	}

}