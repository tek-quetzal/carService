
import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final String username = "postgres";
    private static final String password = "Asd375333045048";
    private static final String url = "jdbc:postgresql://localhost:5432/car_service";
    private static final String sql = "SELECT o.id_order,o.clientname,o.base_price,o.status,v.brand,v.model,v.vincode,v.vehicle_type FROM orders o JOIN vehicles v ON o.id_vehicle = v.id;";


    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet res = statement.executeQuery()) {
            while (res.next()) {
                try {
                    Vehicle car = switch (res.getString("vehicle_type")) {
                        case "Truck" ->
                                new Truck(res.getString("brand"), res.getString("model"), res.getString("vincode"));
                        case "PassengerCar" ->
                                new PassengerCar(res.getString("brand"), res.getString("model"), res.getString("vincode"));
                        default -> throw new IllegalArgumentException("Данный тип транспорта не обслуживается");
                    };
                    OrderStatus status = OrderStatus.valueOf(res.getString("status"));
                    Order order = new Order(res.getInt("id_order"), res.getString("clientname"), res.getDouble("base_price"), status, car);
                    orders.add(order);
                }
                catch (IllegalStatusTransitionException|IllegalArgumentException|InvalidVinException e){
                    System.out.printf("Ошибка заказа №%d!%n",res.getInt("id_order"));
                    System.out.printf(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
