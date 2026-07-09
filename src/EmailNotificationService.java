public class EmailNotificationService implements NotificationService {

    @Override
    public void notifyClient(Order order,String message){
        System.out.printf("[Email] На почту отправлено письмо для %s: %s",order.getName(),message);
    }


}
