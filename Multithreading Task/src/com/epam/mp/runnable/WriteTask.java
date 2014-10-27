package com.epam.mp.runnable;

import com.epam.mp.entity.User;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.main.Main;

public class WriteTask implements Runnable {

	private User user;
	private int count;

	public WriteTask(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		count++;
		if (count > 2) {
			user = Main.generateNewUser();
		}
		String firstname = "Firstname " + NumberGenerator.generateNumber();
		String lastname = "Lastname " + NumberGenerator.generateNumber();
		this.user.setFirstName(firstname);
		this.user.setLastName(lastname);
		Main.logList.add(Thread.currentThread().getName() + " *WRITE THREAD "
				+ count
				+ "* is changing firstname and lastname of the user with id "
				+ user.getId() + " to " + user.getFirstName() + " and "
				+ user.getLastName() + "\r\n");
	}

}
