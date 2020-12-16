package com.randomlink.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @author CAI Pengfei
 * @email caipengfei_ecpkn@outlook.com
 * @date 2020/12/16 上午10:12
 * @description This task searches files for a given keyword
 */
public class SearchTask implements Runnable {

    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        try{
            boolean done = false;
            while (!done){
                File file = queue.take();
                if(file == FileEnumerationTask.DUMMY){
                    queue.put(file);
                    done = true;
                }else{
                    search(file);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){

        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file file to search
     * @throws IOException
     */
    public void search(File file) throws IOException {
        Scanner in = new Scanner(new FileInputStream(file));
        int lineNumber = 0;
        while(in.hasNextLine()){
            lineNumber++;
            String line = in.nextLine();
            if(line.contains(keyword)){
                System.out.printf("%s [line: %d]: %s%n", file.getPath(), lineNumber, line);
            }
        }
        in.close();
    }
}
