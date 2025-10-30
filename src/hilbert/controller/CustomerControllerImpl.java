package hilbert.controller;

import hilbert.dao.CustomerDaoImpl;
import hilbert.model.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CustomerControllerImpl {

    public static void  getAllCustomers() {
        CustomerDaoImpl cust = new CustomerDaoImpl();
        List<Customer> list =  cust.getAllCustomers();

        for(Customer c : list){
            System.out.println(c.getId() +") "+ c.getName() +" - "+ c.getCity() +" ["+ c.getEmail()+"]");
        }
    }

    public static void addCostumer(BufferedReader br) throws IOException {
        System.out.println("Input customers name");
        String name = br.readLine();
        System.out.println("Input customers email");
        String email = br.readLine();
        System.out.println("Input customers city");
        String city = br.readLine();
        CustomerDaoImpl cdi = new CustomerDaoImpl();
        cdi.addCustomer(new Customer(0, name,email, city));  //id is not used;
    }

    public static void updateCity(BufferedReader br) throws IOException {
        System.out.println("Choose customer nr");
        int id = Integer.parseInt(br.readLine());
        System.out.println("Type new city name");
        String newCity = br.readLine();
        CustomerDaoImpl cdi = new CustomerDaoImpl();
        cdi.updateCity(id, newCity);
    }

    public static void findCustomerByEmail(BufferedReader br) throws IOException {
        System.out.println("Customers email");
        String email = br.readLine();
        CustomerDaoImpl cdi = new CustomerDaoImpl();
        List<Customer> list= cdi.findCustomerByEmail(email);

        for(Customer c : list){
            System.out.println(c.getId() +") "+ c.getName() +" - "+ c.getCity() +" ["+ c.getEmail()+"]");
        }
    }


    public static void deleteCustomer(BufferedReader br) throws IOException {
        System.out.println("Choose customer nr");
        int id = Integer.parseInt(br.readLine());
        CustomerDaoImpl cdi = new CustomerDaoImpl();
        cdi.deleteCustomer(id);
    }
}