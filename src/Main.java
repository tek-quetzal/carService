
import java.util.*;


public class Main{
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        NotificationService smsService = new SmsNotificationService();
        NotificationService emailService = new EmailNotificationService();


        try {
            PassengerCar car1 = new PassengerCar("Kia", "Rio", "KNAGD243X9A1234");
            orders.add(new Order("Матвей", 100.0,car1));
        }
        catch (InvalidVinException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            PassengerCar car2 = new PassengerCar("Volkswagen", "Golf", "WVWZZZ1KZ6W987654");
            orders.add(new Order("Александр",-250.0,car2));
        }
        catch (InvalidVinException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }




        Truck truck1 = new Truck("Volvo", "FH16", "YV2RT40A1BA111222");
        System.out.print("Введите базовую цену для нового заказа: ");
        Scanner scan = new Scanner(System.in);
        while(true) {
            try {
                double price = scan.nextDouble();
                orders.add(new Order("Дмитрий",price,truck1));
                break;
            }
            catch(InputMismatchException e){
                scan.nextLine();
                System.out.print("Ошибка: Введено не число! Попробуйте ввести еще раз: ");
            }
            catch(IllegalArgumentException e){
                System.out.print(e.getMessage() + "Попробуйте ввести еще раз: ");
            }
        }



        Truck truck2 = new Truck("Scania", "R500", "YS2R4X200MA333444");

        orders.add(new Order("Елена",1500.0,truck2));

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