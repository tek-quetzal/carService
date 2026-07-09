
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

    public void printOrderInvoice(){
        Invoice check = new Invoice();
        check.printReceipt();
    }

    public void changeStatus(OrderStatus newStatus, NotificationService notifier) {
        this.status = newStatus;
        String message = "Статус вашего заказа №" + this.id + " изменился на: " + newStatus.getStatus() + "\n";
        notifier.notifyClient(this,message);
    }


    public class Invoice{

        public void printReceipt(){
            int lenBorder = car.getVinCode().length() + car.getBrand().length() + car.getModel().length() + 15;
            String border = "=".repeat(lenBorder);
            System.out.println(border);
            System.out.println("ООО \"Матвей-Автосервис\"");
            System.out.printf("ЧЕК ДЛЯ ЗАКАЗА №%d%n",id);
            System.out.printf("Клиент: %s%n",clientName);
            System.out.printf("Автомобиль: %s %s (VIN: %s)%n",car.getBrand(),car.getModel(),car.getVinCode());
            System.out.printf("Статус: %s%n",status.getStatus());
            System.out.println("-".repeat(lenBorder));
            System.out.printf("ИТОГО К ОПЛАТЕ: %.2f руб.%n",car.calculateFinalPrice(basePrice));
            System.out.println(border + "\n\n");
        }



    }


}

