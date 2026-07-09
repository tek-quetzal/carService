
import java.util.List;

public class ServiceAnalytics {

    public static double countSalary(List<Order> orders){
        double salary = 0;
        for(Order order : orders){
            salary+=order.getCar().calculateFinalPrice(order.getPrice());
        }
        return salary;
    }


}
