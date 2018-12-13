package com.mkyong.concurrency.queue.crawler;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

// Producer
// Crawl file system and put the filename in BlockingQueue.
public class FileCrawlerProducer implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File file;
    private final File POISON;
    private final int N_POISON_PILL_PER_PRODUCER;

    @Override
    public void run() {

        try {
            crawl(file);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " - FileCrawlerProducer is done, try poison all the consumers!");
                    // poison all threads
                    for (int i = 0; i < N_POISON_PILL_PER_PRODUCER; i++) {
                        System.out.println(Thread.currentThread().getName() + " - puts poison pill!");
                        fileQueue.put(POISON);
                    }
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public FileCrawlerProducer(BlockingQueue<File> fileQueue,
                               FileFilter fileFilter, File file,
                               File POISON, int n_POISON_PILL_PER_PRODUCER) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.file = file;
        this.POISON = POISON;
        N_POISON_PILL_PER_PRODUCER = n_POISON_PILL_PER_PRODUCER;
    }

    private void crawl(File root) throws InterruptedException {

        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!isIndexed(entry)) {
                    System.out.println("[FileCrawlerProducer] - Found..."
                            + entry.getAbsoluteFile());
                    fileQueue.put(entry);
                }
            }
        }

    }

    private boolean isIndexed(File f) {
        return false;
    }

}
