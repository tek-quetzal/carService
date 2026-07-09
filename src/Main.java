

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Main{
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        NotificationService smsService = new SmsNotificationService();
        NotificationService emailService = new EmailNotificationService();

        PassengerCar car1 = new PassengerCar("Kia", "Rio", "KNAGD243X9A123456");
        PassengerCar car2 = new PassengerCar("Volkswagen", "Golf", "WVWZZZ1KZ6W987654");

        Truck truck1 = new Truck("Volvo", "FH16", "YV2RT40A1BA111222");
        Truck truck2 = new Truck("Scania", "R500", "YS2R4X200MA333444");

        orders.add(new Order("Матвей", 100.0,car1));
        orders.add(new Order("Александр",250.0,car2));
        orders.add(new Order("Дмитрий",1000.0,truck1));
        orders.add(new Order("Елена",1500.0,truck2));

        orders.get(0).changeStatus(OrderStatus.IN_PROGRESS,smsService);
        orders.get(0).changeStatus(OrderStatus.COMPLETED,emailService);


        Collections.sort(orders,new Comparator<Order>(){
            @Override
            public int compare(Order o1,Order o2){
                double price1 = o1.getCar().calculateFinalPrice(o1.getPrice());
                double price2 = o2.getCar().calculateFinalPrice(o2.getPrice());
                return Double.compare(price1,price2);
            }
        });



        for(Order order: orders){
            order.printOrderInvoice();
        }

        System.out.printf("Общая выручка сервиса: %.2f%n",ServiceAnalytics.countSalary(orders));

    }
}