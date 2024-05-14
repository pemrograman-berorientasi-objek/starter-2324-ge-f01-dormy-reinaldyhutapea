package pbo.f01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        String command;
        while (scanner.hasNextLine()) { // Periksa ketersediaan baris berikutnya
            command = scanner.nextLine();
            if (command.isEmpty()) {
                break;
        
    }
    String[] tokens = command.split("#");

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals("student-add")) {
                    studentAdd(tokens);
                } else if (tokens[i].equals("dorm_add")) {
                    dormAdd(tokens);
                } else if (tokens[i].equals("assign")) {
                    assignStudent(tokens);
                } else if (tokens[i].equals("display-all")) {
                    displayAll();
                } else if (tokens[i].equals("---")){
                    break;
                }
            }
        }
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Student s").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        scanner.close();
    }

    private static void studentAdd(String[] tokens){
        String s_Id = tokens[1];
        String s_name= tokens[2];
        String  s_entranceYear = tokens[3];
        String s_gender = tokens[4];

        List<Student> students = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.s_Id = :s_Id", Student.class)
                .setParameter("s_Id", s_Id)
                .getResultList();
        if (!students.isEmpty()) {
            return;

    }
    Student student = new Student(s_Id, s_name, s_entranceYear, s_gender);
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
}

private static void studentShowAll() {
    List<Student> students = entityManager.createQuery(
            "SELECT s FROM Student s ORDER BY s.s_Id ASC", Student.class)
            .getResultList();

    for (Student student : students) {
        String output = student.getS_Id() + "|" + student.getS_name() + "|" + student.getS_entranceYear() + "|" + student.getS_gender();
        System.out.println(output);
    }
}


private static void dormAdd(String[] tokens) {
    String d_name = tokens[1];
    String d_capacity = tokens[2];
    String d_gender = tokens[3];

    List<Dorm> dorms = entityManager.createQuery(
            "SELECT c FROM Dorm c WHERE c.d_name = :d_name", Dorm.class)
            .setParameter("d_name", d_name)
            .getResultList();
    if (!dorms.isEmpty()) {
        return;
    }

    Dorm dorm = new Dorm(d_name, d_capacity, d_gender);
    entityManager.getTransaction().begin();
    entityManager.persist(dorm);
    entityManager.getTransaction().commit();
}

private static void dormShowAll() {
    List<Dorm> dorms = entityManager.createQuery(
            "SELECT c FROM Dorm c ORDER BY c.d_name ASC", Dorm.class)
            .getResultList();

    for (Dorm dorm : dorms) {
        String output = dorm.getD_name() + "|" + dorm.getD_capacity() + "|" + dorm.getD_gender();
        System.out.println(output);
    }
}
private static void displayAll(){
    studentShowAll();
    dormShowAll();
}

private static void assignStudent(String[] tokens) {
    String s_Id = tokens[1];
    String d_name = tokens[2];

    Student student = entityManager.createQuery(
            "SELECT s FROM Student s WHERE s.s_Id = :s_Id", Student.class)
            .setParameter("s_Id", s_Id)
            .getSingleResult();

    Dorm dorm = entityManager.createQuery(
            "SELECT c FROM Dorm c WHERE c.d_name = :d_name", Dorm.class)
            .setParameter("d_name", d_name)
            .getSingleResult();

    long assignedStudentCount = entityManager.createQuery(
            "SELECT COUNT(a) FROM Assignment a WHERE a.d_name = :d_name", Long.class)
            .setParameter("d_name", d_name)
            .getSingleResult();

    if (assignedStudentCount >= Long.parseLong(dorm.getD_capacity())) {
        System.out.println("Asrama " + d_name + " sudah penuh.");
        return;
    }

    if (!student.getS_gender().equals(dorm.getD_gender())) {
        System.out.println("Jenis kelamin mahasiswa tidak sesuai dengan asrama.");
        return;
    }

    List<Assignment> assignments = entityManager.createQuery(
            "SELECT e FROM Assignment e WHERE e.s_Id = :s_Id AND e.d_name = :d_name", Assignment.class)
            .setParameter("s_Id", s_Id)
            .setParameter("d_name", d_name)
            .getResultList();

    if (!assignments.isEmpty()) {
        return;
    }

    Assignment assignment = new Assignment(s_Id, d_name);
    entityManager.getTransaction().begin();
    entityManager.persist(assignment);
    entityManager.getTransaction().commit();
}
    }
    





