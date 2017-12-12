
import com.laowei.test.MyRunnable;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class testLink {


//    @Test
    public static  void main(String a[]){

        new Thread(new MyRunnable()).start();
        MyRunnable.queue.add("ssss");

    }



}
