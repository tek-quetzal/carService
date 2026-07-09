
public class Order {

    private static int orderCounter = 0;


    private int id;
    private String clientName;
    private double basePrice;
    private OrderStatus status = OrderStatus.ACCEPTED;
    private Vehicle car;


    public Order(String clientName,double basePrice, Vehicle car){
        this.clientName = clientName;
        setPrice(basePrice);
        this.id = ++orderCounter;
        this.car = car;
    }



    public void setPrice(double basePrice) {
        if (basePrice<0) {
            System.out.println("Цена не может быть отрицательной!");
            return;
        }
        this.basePrice = basePrice;
    }

    public void setName(String clientName) {
        this.clientName = clientName;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCar(Vehicle car) {
        this.car = car;
    }



    public int getId() {
        return id;
    }

    public double getPrice() {
        return basePrice;
    }

    public String getName() {
        return clientName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Vehicle getCar() {
        return car;
    }

    public void printInfo(){
        System.out.printf("Заказ №%d: Клиент %s,Машина %s %s, Итоговая цена: %.2f%n",id,clientName,car.getBrand(),
                car.getModel(),car.calculateFinalPrice(basePrice));
    }

    public void changeStatus(OrderStatus newStatus, NotificationService notifier) {
        this.status = newStatus;
        String message = "Статус вашего заказа №" + this.id + " изменился на: " + newStatus.getStatus() + "\n";
        notifier.notifyClient(this,message);
    }

}

