public class PassengerCar extends Vehicle {

    public PassengerCar(){
        super();
    }

    public PassengerCar(String brand,String model,String vinCode){
        super(brand,model,vinCode);
    }

    @Override
    public double calculateFinalPrice(double basePrice){
        return basePrice*0.95;
    }

}
