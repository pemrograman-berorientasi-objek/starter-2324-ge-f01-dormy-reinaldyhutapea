package pbo.f01.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "s_Id", nullable = false, length = 40)
    private String s_Id;

    @Column(name = "s_name", nullable = false, length = 40)
    private String s_name;

    @Column(name = "s_entranceYear", nullable = false, length = 40)
    private String s_entranceYear;

    @Column(name = "s_gender", nullable = false, length = 40)
    private String s_gender;

    @ManyToMany
    @JoinTable(
        name = "student_dorm",
        joinColumns = @JoinColumn(name = "s_Id", referencedColumnName = "s_Id"),
        inverseJoinColumns = @JoinColumn(name = "d_name", referencedColumnName = "d_name")
    )
    private Set<Dorm> dorms = new HashSet<>();

    public Student() {}

    public Student(String s_Id, String s_name, String s_entranceYear, String s_gender) {
        this.s_Id = s_Id;
        this.s_name = s_name;
        this.s_entranceYear = s_entranceYear;
        this.s_gender = s_gender;
    }

    public String getS_Id() {
        return s_Id;
    }

    public void setS_Id(String s_Id) {
        this.s_Id = s_Id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_entranceYear() {
        return s_entranceYear;
    }

    public void setS_entranceYear(String s_entranceYear) {
        this.s_entranceYear = s_entranceYear;
    }

    public String getS_gender() {
        return s_gender;
    }

    public void setS_gender(String s_gender) {
        this.s_gender = s_gender;
    }

    public Set<Dorm> getDorms() {
        return dorms;
    }

    public void setDorms(Set<Dorm> dorms) {
        this.dorms = dorms;
    }

    @Override
    public String toString() {
        return s_Id + "|" + s_name + "|" + s_entranceYear;
    }
}
