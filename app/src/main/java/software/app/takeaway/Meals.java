package software.app.takeaway;

import android.support.annotation.NonNull;

public class Meals implements Comparable<Meals> {
    private String name;        //品名
    private int price;          //價錢
    private String species;     //種類
    private String username;

    public Meals(String name, int price, String species) {
        this("", name, price, species);
    }

    public Meals(String username, String name, int price, String species) {
        this.username = username;
        this.name = name;
        this.price = price;
        this.species = species;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public int compareTo(@NonNull Meals o) {
        return this.species.compareTo(o.species);
    }
}
