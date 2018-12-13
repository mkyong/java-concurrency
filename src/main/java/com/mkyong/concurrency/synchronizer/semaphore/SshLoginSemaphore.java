package com.mkyong.concurrency.synchronizer.semaphore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SshLoginSemaphore {

    private final Semaphore mutex;

    // only 1 user is allow
    public SshLoginSemaphore() {
        this.mutex = new Semaphore(1);
    }

    private void ssh(String user) throws InterruptedException {

        mutex.acquire();
        System.out.println(getCurrentDateTime() + " : " + user + " mutex.acquire()");

        Thread.sleep(2000);

        mutex.release();
        System.out.println(getCurrentDateTime() + " : " + user + " mutex.release()");

    }

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        SshLoginSemaphore task = new SshLoginSemaphore();

        executor.submit(() -> {
            try {
                task.ssh("mkyong");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                task.ssh("yflow");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                task.ssh("zilap");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();

    }

    private static String getCurrentDateTime() {
        return sdf.format(new Date());
    }

}
