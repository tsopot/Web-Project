package hotelvirtual.model;

public class Apartment {

    public enum Type {
        Single, Double, Twin, Cabana, Deluxe;
    }

    public enum View {
        City, Sea;
    }

    private int id;
    private Type type;
    private boolean wifi;
    private boolean tvSet;
    private boolean airCondition;
    private View view;
    private int pricePerNight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isTvSet() {
        return tvSet;
    }

    public void setTvSet(boolean tvSet) {
        this.tvSet = tvSet;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
