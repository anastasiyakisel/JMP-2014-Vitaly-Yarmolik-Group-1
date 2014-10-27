package com.epam.mp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.epam.mp.entity.User;
import com.epam.mp.runnable.CancelTask;
import com.epam.mp.runnable.ReadTask;
import com.epam.mp.runnable.WriteTask;

public class Main {

	private static final int WRITE_THREADS_COUNT = 10;
	private static final int READ_THREADS_COUNT = 100;

	private static ScheduledExecutorService writeSchExService = Executors.newScheduledThreadPool(10);
	private static ScheduledExecutorService readSchExService = Executors.newScheduledThreadPool(100);
	private static ScheduledExecutorService cancelExService = Executors.newScheduledThreadPool(110);

	private static ConcurrentHashMap<String, User> userMap;

	public static List<String> logList = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		userMap = fillHashMap();

		List<Runnable> writeTaskList = createWriteTasks(userMap);
		List<Runnable> readTaskList = createReadTasks(userMap);

		for (int k = 0; k < READ_THREADS_COUNT; k++) {
			int count = 0;
			if (k % 10 == 0) {
				ScheduledFuture<?> schWriteFuture = writeSchExService
						.scheduleWithFixedDelay(writeTaskList.get(count), 500,
								1000, TimeUnit.MILLISECONDS);
				cancelExService.schedule(new CancelTask(schWriteFuture), 10,
						TimeUnit.SECONDS);
				count++;
			}
			ScheduledFuture<?> schReadFuture = readSchExService
					.scheduleWithFixedDelay(readTaskList.get(k), 1000, 700,
							TimeUnit.MILLISECONDS);
			cancelExService.schedule(new CancelTask(schReadFuture), 10,
					TimeUnit.SECONDS);
		}

		Thread.currentThread().sleep(10000);
		System.out.print(logList);
	}

	private static ConcurrentHashMap<String, User> fillHashMap() {
		ConcurrentHashMap<String, User> map = new ConcurrentHashMap<String, User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setFirstName("Firstname " + User.counter);
			user.setLastName("Lastname " + User.counter);
			map.put(user.getId(), user);
		}

		return map;
	}

	// creating initial set of write tasks
	private static ArrayList<Runnable> createWriteTasks(
			ConcurrentHashMap<String, User> userMap) {
		ArrayList<Runnable> writeTaskList = new ArrayList<>();
		for (int i = 0; i < WRITE_THREADS_COUNT; i++) {
			Integer userId = i;
			User user = userMap.get(userId.toString());
			WriteTask writeTask = new WriteTask(user);
			writeTaskList.add(writeTask);
		}
		return writeTaskList;
	}

	// creating initial set of read tasks
	private static ArrayList<Runnable> createReadTasks(
			ConcurrentHashMap<String, User> userMap) {
		Integer counter = 1;
		ArrayList<Runnable> readTaskList = new ArrayList<Runnable>();
		for (int i = 0; i < READ_THREADS_COUNT; i++) {
			Integer userId = null;
			if (counter % 10 == 0) {
				userId = counter = 10;
			} else {
				userId = counter % 10;
			}
			counter++;
			User user = userMap.get(userId.toString());
			Runnable readTask = new ReadTask(user);
			readTaskList.add(readTask);
		}
		return readTaskList;
	}

	public static User generateNewUser() {
		Random rand = new Random();
		Integer userId = rand.nextInt(userMap.size()) + 1;
		User user = userMap.get(userId.toString());
		return user;
	}
}
