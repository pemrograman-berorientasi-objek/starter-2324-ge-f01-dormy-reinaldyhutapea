package pbo.f01.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import javax.persistence.*;

/**
 * 12S22010 - Reinaldy Hutapea
 * 12S22048 - Ira Wianda Sari Silalahi
 */
public class Executor {
    private EntityManager entityManager;

    public Executor(EntityManager em) {
        this.entityManager = em;
    }

    public void studentAdd(String[] tokens) {
        String s_Id = tokens[1];
        String s_name = tokens[2];
        String s_entranceYear = tokens[3];
        String s_gender = tokens[4];

        Student existingStudent = entityManager.find(Student.class, s_Id);
        if (existingStudent != null) {
            return;
        }

        entityManager.getTransaction().begin();
        Student student = new Student(s_Id, s_name, s_entranceYear, s_gender);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public void dormAdd(String[] tokens) {
        String d_name = tokens[1];
        int d_capacity = Integer.parseInt(tokens[2]);
        String d_gender = tokens[3];

        entityManager.getTransaction().begin();
        Dorm dorm = new Dorm(d_name, d_capacity, d_gender);
        entityManager.persist(dorm);
        entityManager.getTransaction().commit();
    }

    public void assignStudent(String[] tokens) {
        String s_Id = tokens[1];
        String d_name = tokens[2];

        Student student = entityManager.find(Student.class, s_Id);
        Dorm dorm = entityManager.find(Dorm.class, d_name);

        if (student == null || dorm == null) {
            return;
        }

        if (!student.getS_gender().equals(dorm.getD_gender())) {
            return;
        }

        if (dorm.getStudents().size() >= dorm.getD_capacity()) {
            return;
        }

        entityManager.getTransaction().begin();
        dorm.getStudents().add(student);
        entityManager.merge(dorm);
        entityManager.getTransaction().commit();
    }

    public void displayAll() {
        List<Dorm> dorms = entityManager.createQuery("SELECT d FROM Dorm d ORDER BY d.d_name", Dorm.class)
                .getResultList();

        for (Dorm dorm : dorms) {
            System.out.println(dorm);

            List<Student> students = new ArrayList<>(dorm.getStudents());
            students.sort(Comparator.comparing(Student::getS_name));

            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void cleanupDatabase() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
        entityManager.createQuery("DELETE FROM Dorm").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
