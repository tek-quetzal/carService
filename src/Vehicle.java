
public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected String vinCode;


    public Vehicle(){
        brand = "notFound";
        model = "notFound";
        vinCode = "notFound";
    }


    public Vehicle(String brand,String model,String vinCode){
        this.brand = brand;
        this.model = model;
        this.vinCode = vinCode;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }



    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVinCode() {
        return vinCode;
    }




    public abstract double calculateFinalPrice(double basePrice);
}
