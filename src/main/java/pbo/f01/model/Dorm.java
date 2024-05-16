package pbo.f01.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * 12S22010 - Reinaldy Hutapea
 * 12S22048 - Ira Wianda Sari Silalahi
 */
@Entity
@Table(name = "dorms")
public class Dorm {
    @Id
    @Column(name = "d_name", nullable = false, length = 30)
    private String d_name;

    @Column(name = "d_capacity", nullable = false)
    private int d_capacity;

    @Column(name = "d_gender", nullable = false, length = 20)
    private String d_gender;

    @ManyToMany(mappedBy = "dorms")
    private Set<Student> students = new HashSet<>();

    public Dorm() {}

    public Dorm(String d_name, int d_capacity, String d_gender) {
        this.d_name = d_name;
        this.d_capacity = d_capacity;
        this.d_gender = d_gender;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public int getD_capacity() {
        return d_capacity;
    }

    public void setD_capacity(int d_capacity) {
        this.d_capacity = d_capacity;
    }

    public String getD_gender() {
        return d_gender;
    }

    public void setD_gender(String d_gender) {
        this.d_gender = d_gender;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return d_name + "|" + d_gender + "|" + d_capacity + "|" + students.size();
    }
}
