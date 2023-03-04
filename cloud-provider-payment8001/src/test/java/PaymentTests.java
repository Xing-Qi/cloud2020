import com.atguigu.springcloud.PaymentMain8001;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @author Oliver
 * @create 2023-03-01 17:58
 */
@SpringBootTest(classes = PaymentMain8001.class)
public class PaymentTests {
    @Resource
    private PaymentService paymentService;
//    @Test
    public void testCreate(){
        Payment payment = new Payment();
        payment.setSerial("atguigu002");
        paymentService.create(payment);
    }
}
