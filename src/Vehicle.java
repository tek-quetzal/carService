
public abstract class Vehicle {
    private String brand;
    private String model;
    private String vinCode;


    public Vehicle(){
        brand = "notFound";
        model = "notFound";
        vinCode = "notFound";
    }


    public Vehicle(String brand,String model,String vinCode){
        this.brand = brand;
        this.model = model;
        if (vinCode.length() != 17){
            throw new InvalidVinException("Ошибка создания заказа: Неверный VIN-номер,он должен быть длиною в 17 символов\n");
        }
        this.vinCode = vinCode;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVinCode(String vinCode) {
        if (vinCode.length() != 17){
            throw new InvalidVinException("Неверный VIN-номер,он должен быть длиною в 17 символов");
        }
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
