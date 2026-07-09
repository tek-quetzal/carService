public enum OrderStatus {

    ACCEPTED("Принят"),
    IN_PROGRESS("В работе"),
    COMPLETED("Готов к выдаче"),
    PAID("Оплачен");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
