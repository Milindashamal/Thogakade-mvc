package lk.ijse.thogakade.to;


public class Item {
    private String code;
    private String description;
    private double unitPrice;
    private int QtyOnHand;

    public Item() {
    }

    public Item(String code, String description, double unitPrice, int QtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.QtyOnHand = QtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int QtyOnHand) {
        this.QtyOnHand = QtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}
