import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CheckOut {
    private ArrayList<Costumer> customers;
    private Semaphore semaphore1, semaphore2; // Dos semáforos, uno para cada hilo

    public CheckOut() {
        customers = new ArrayList<>();
        semaphore1 = new Semaphore(1); // Solo un hilo puede entrar en el semáforo en un momento
        semaphore2 = new Semaphore(1); // El segundo semáforo para el segundo hilo

        // Crear los clientes
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
        // Crear dos hilos, uno para cada semáforo
        Thread thread1 = new Thread(() -> processCustomers(1));
        Thread thread2 = new Thread(() -> processCustomers(2));

        // Iniciar los hilos
        thread1.start();
        thread2.start();
    }

    private void processCustomers(int threadId) {
        try {
            while (!customers.isEmpty()) {
                // Controlar el acceso de los hilos con semáforos para que solo trabajen dos hilos a la vez
                if (threadId == 1) {
                    semaphore1.acquire();  // Hilo 1 adquiere su semáforo
                    if (!customers.isEmpty()) {
                        Costumer customer = customers.remove(0);
                        processclients(customer, threadId);
                    }
                    semaphore2.release();  // Libera el semáforo para el hilo 2
                } else {
                    semaphore2.acquire();  // Hilo 2 adquiere su semáforo
                    if (!customers.isEmpty()) {
                        Costumer customer = customers.remove(0);
                        processclients(customer, threadId);
                    }
                    semaphore1.release();  // Libera el semáforo para el hilo 1
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public synchronized void processclients(Costumer customer, int threadId) {
        long startTime = System.currentTimeMillis();

        // Obtener el nombre del hilo actual para imprimirlo
        String threadName = Thread.currentThread().getName();

        System.out.println("Thread " + threadName + " processing customer: " + customer.getName());

        // Procesar cada producto del carrito del cliente
        for (String product : customer.getCart()) {
            // Imprimir lo que está procesando cada hilo
            System.out.println("Thread " + threadName + " processing product: " + product);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Thread " + threadName + " took " + duration + " ms to process customer: " + customer.getName());
    }
}
