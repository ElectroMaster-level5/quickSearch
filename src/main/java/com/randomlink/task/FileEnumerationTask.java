package com.randomlink.task;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author CAI Pengfei
 * @email caipengfei_ecpkn@outlook.com
 * @date 2020/12/16 上午10:00
 * @description This task is enumerates all files in a directory
 * and its subdirectories, then put it in the queue
 *
 */
public class FileEnumerationTask implements Runnable {
    private BlockingQueue<File> queue;
    private File startingDirectory;
    public static File DUMMY = new File("");

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {

        }

    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories
     * @param directory the directory to start
     * @throws InterruptedException
     */
    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }
}
