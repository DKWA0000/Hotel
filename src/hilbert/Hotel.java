package hilbert;

import hilbert.connector.MysqlConnector;
import hilbert.controller.CustomerControllerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Hotel {
        public Hotel() {
            try {
                MysqlConnector.createDatabaseFresh();
                HotelDBInitializer.getInstance();
            }
                catch(SQLException e){
                    System.out.println("Database error: " + e.getMessage());
                    e.printStackTrace();
                }
            run();
        }

        private void run(){

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                try{
                    menuChoices(br, menu(br));
                }
                catch (IOException e){
                    System.out.println("Make sure not to close bufferedReader before done");
                }
            }
        }

        private int menu(BufferedReader br) throws IOException {
            System.out.println("=====HOTELL MENU=====");

            //Kunder
            System.out.println("1. Lägga till ny kund");
            System.out.println("2. Visa alla kunder");
            System.out.println("3. Sök kund via E-post");
            System.out.println("4. Uppdatera stad för en kund");
            System.out.println("5. Ta bort en kund");

            //Rum
            System.out.println("6. Lägga till ett rum");
            System.out.println("7. Visa alla rum");
            System.out.println("8. Visa alla tillgängliga rum");
            System.out.println("9. Updatera priset för ett rum");
            System.out.println("10. Ändra rum typ");

            //Bokningar
            System.out.println("11. Boka ett rum");
            System.out.println("12. Lista alla bokningar");
            System.out.println("13. Sök bokningar via e-post");
            System.out.println("14. Avboka rum");
            System.out.println("0. Exit program");
            return Integer.parseInt(br.readLine());
        }

        private void menuChoices(BufferedReader br, int choice) throws IOException {
            switch(choice){

                //Kunder
                case 1 : CustomerControllerImpl.addCostumer(br);         break;
                case 2 : CustomerControllerImpl.getAllCustomers();       break;
                case 3 : CustomerControllerImpl.findCustomerByEmail(br); break;
                case 4 : CustomerControllerImpl.updateCity(br);          break;
                case 5 : CustomerControllerImpl.deleteCustomer(br);      break;

                //Rum
                case 6 : break;
                case 7 : break;
                case 8 : break;
                case 9 : break;
                case 10 : break;

                //Bokningar
                case 11 : break;
                case 12 : break;
                case 13 : break;
                case 14 : break;

                case 0 : br.close(); System.exit(0);
            }
        }
}
