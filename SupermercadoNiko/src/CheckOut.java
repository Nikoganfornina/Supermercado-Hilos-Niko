import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CheckOut {
    private ArrayList<Costumer> customers;
    private Semaphore semaphore;

    public CheckOut() {
        customers = new ArrayList<>();
        semaphore = new Semaphore(1);
        addCustomer(new Costumer("Ivan"));
        addCustomer(new Costumer("Victor"));
        addCustomer(new Costumer("Niko"));
        addCustomer(new Costumer("Rodri"));
        addCustomer(new Costumer("Alberto"));
    }

    public void addCustomer(Costumer customer) {
        customers.add(customer);
    }

    public void startProcessing() {
        for (int i = 0; i < 2; i++) {
            Thread customerThread = new Thread(() -> {
                processNextCustomer();
            });
            customerThread.start();
        }
    }

    private void processNextCustomer() {
        try {
            semaphore.acquire();

            if (!customers.isEmpty()) {
                Costumer customer = customers.remove(0);
                processclients(customer);
            }

            semaphore.release();  // Libera el semáforo para que otro hilo pueda trabajar

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processclients(Costumer customer) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println("Processing customer: " + customer.getName());

        for (String product : customer.getCart()) {
            Thread.sleep(1000);  // Simula el tiempo de procesamiento de cada producto
            System.out.println("Processing product: " + product);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time to process the client: " + duration + " ms");
    }
}
