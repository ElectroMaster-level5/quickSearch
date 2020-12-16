package com.randomlink;

import com.randomlink.task.FileEnumerationTask;
import com.randomlink.task.SearchTask;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * main
 */
public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. /home/exia/project/src): ");
        String directory = in.nextLine();
        System.out.println("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();
//        String directory = "/home/exia/workspace/learn/redis/src";
//        String keyword = "addReply(";

        final int FILE_QUEUE_SIZE = 10;
        final int ENUMERATE_FILES_POOL_SIZE = 1;
        final int SEARCH_POOL_SIZE = 100;

        int totalPoolSize = ENUMERATE_FILES_POOL_SIZE + SEARCH_POOL_SIZE;
        ExecutorService executor = new ThreadPoolExecutor(totalPoolSize, totalPoolSize,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        for(int i=0; i<ENUMERATE_FILES_POOL_SIZE;i++){
            executor.submit(new FileEnumerationTask(queue, new File(directory)));
        }

        for (int i = 0; i < SEARCH_POOL_SIZE; i++) {
            executor.submit(new SearchTask(queue, keyword));
        }
        executor.shutdown();
    }
}
