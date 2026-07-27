
public class Order {


    private int id;
    private String clientName;
    private double basePrice;
    private OrderStatus status;
    private Vehicle car;



    public Order(){
        this.clientName = "NotFound";
        this.basePrice = 0;
        this.id = -1;
        this.car = new Truck();
    }



    public Order(int id, String clientName,double basePrice,OrderStatus status, Vehicle car){
        this.clientName = clientName;
        setPrice(basePrice);
        this.id = id;
        this.car = car;
        this.status = status;
    }



    public void setPrice(double basePrice) {
        if (basePrice<0) {
            throw new IllegalArgumentException("Ошибка создания заказа: Цена не может быть отрицательной!\n");
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
        if ((this.status == OrderStatus.PAID || this.status == OrderStatus.COMPLETED) && (newStatus == OrderStatus.IN_PROGRESS || newStatus == OrderStatus.ACCEPTED)){
            throw new IllegalStatusTransitionException("Ошибка: Запрещено откатывать статус выполненного или оплаченного заказа!\n");
        }
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

