package com.laowei.test;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// 使用该 Runnable , 必须先启动该线程
public class MyRunnable implements Runnable {


//    Date

    // 链表阻塞队列
    public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);

    public void run() {

        while (true){
            try {

                String msg = queue.take();
                for (int i = 0; i < 3; i++) {

                    TimeUnit.SECONDS.sleep(3);
                    if (i==1){
                        System.out.println(msg +"["+i+"]");
                        break;

                    }else {
                        System.out.println("queue count is " + queue.size());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("error:" + e.getLocalizedMessage());
            }
        }

    }

    private  void dealMsg(String msg){
        try {

            //http qingqiu



        }catch (Exception e ){

        }
    }
}
