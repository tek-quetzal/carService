
import com.sun.jdi.InvalidTypeException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        List<Order> orders = DatabaseManager.getOrders();
        if (orders.isEmpty()){
            System.out.println("Список заказов пуст,работы нет");
            System.exit(0);
        }
        else System.out.println("Количество заказов: " + orders.size());;

        NotificationService smsService = new SmsNotificationService();
        NotificationService emailService = new EmailNotificationService();

        orders.get(0).changeStatus(OrderStatus.IN_PROGRESS,smsService);
        orders.get(0).changeStatus(OrderStatus.COMPLETED,emailService);


        try{
            orders.get(0).changeStatus(OrderStatus.ACCEPTED,emailService);
        }
        catch(IllegalStatusTransitionException e){
            System.out.println(e.getMessage());
        }



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