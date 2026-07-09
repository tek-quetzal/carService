public class Truck extends Vehicle {

        public Truck(){
            super();
        }

        public Truck(String brand,String model,String vinCode){
            super(brand,model,vinCode);
        }
        @Override
        public double calculateFinalPrice(double basePrice){
            return basePrice*1.4;
        }

}
