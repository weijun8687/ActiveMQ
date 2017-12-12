import com.laowei.springmq.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestProduct extends BaseJunit4Test{

    @Autowired
    private Product product;

    @Test
    public void sendmessage() throws InterruptedException {

        while (true){
            Thread.sleep(3000);
            product.sendMessage("hahaha");
        }
    }
}
