import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Thread checkout = new Thread();
        Thread checkout = new Thread();


        Costumer newCostumer1 = new Costumer("Ivan") ;
        Costumer newCostumer2 = new Costumer("Victor") ;
        Costumer newCostumer3 = new Costumer("Niko") ;
        Costumer newCostumer4 = new Costumer("Rodri") ;
        Costumer newCostumer5 = new Costumer("Alberto") ;
        //This is the Thread exercise about the supermarket
        //prueba de commit para martin

        newCostumer1.ShowCart();
        newCostumer2.ShowCart();
        newCostumer3.ShowCart();
        newCostumer4.ShowCart();
        newCostumer5.ShowCart();

    }
}