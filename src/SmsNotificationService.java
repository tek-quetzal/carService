public class SmsNotificationService implements NotificationService {

    @Override
    public void notifyClient(Order order,String message){
        System.out.printf("[SMS] Клиент %s: %s",order.getName(),message);
    }

}
