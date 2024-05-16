package pbo.f01;

import javax.persistence.*;
import pbo.f01.model.*;
import java.util.*;

/**
 * 12S22010 - Reinaldy Hutapea
 * 12S22048 - Ira Wianda Sari Silalahi
 */

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();

        Executor executor = new Executor(entityManager);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("---")) {
                break;
            }

            String[] tokens = command.split("#");
            switch (tokens[0]) {
                case "student-add":
                    executor.studentAdd(tokens);
                    break;
                case "dorm-add":
                    executor.dormAdd(tokens);
                    break;
                case "assign":
                    executor.assignStudent(tokens);
                    break;
                case "display-all":
                    executor.displayAll();
                    break;
            }
        }

        executor.cleanupDatabase();
        scanner.close();
        factory.close();
    }

  
}