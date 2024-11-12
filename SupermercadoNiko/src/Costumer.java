import java.util.ArrayList;
import java.util.Random;

public class Costumer {
    String name;
    ArrayList<String> cart;


    public Costumer(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
        FillCart();

    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getCart() {
        return cart;
    }


    public void FillCart() {

        //This method create a cart with items random

        ArrayList<String> RandomCart = new ArrayList<>();

        RandomCart.add("Milk");
        RandomCart.add("Bread");
        RandomCart.add("Rice");
        RandomCart.add("Tomato");
        RandomCart.add("Onion");
        RandomCart.add("Pig Meat");
        RandomCart.add("Chicken Meat");

        Random random = new Random();


        for (int i = 0; i < 5; i++) {
            // Select random Products
            int randomIndex = random.nextInt(RandomCart.size());
            String randomProduct = RandomCart.get(randomIndex);
            cart.add(randomProduct);
        }

    }

    public void ShowCart() {
        System.out.println("Name Client :" + name);
        int Count = 0;
        for (String products : cart) {
            Count++;
            System.out.println("Product number " + Count + " : " + products);
        }
    }

}
