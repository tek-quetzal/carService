public class PassengerCar extends Vehicle {

    public PassengerCar(){
        super();
    }

    public PassengerCar(String brand,String name,String vinCode){
        super(brand,name,vinCode);
    }

    @Override
    public double calculateFinalPrice(double basePrice){
        return basePrice*0.95;
    }

}
