package pbo.f01.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "DORM")
public class Dorm {
    @Id
    @GeneratedValue
    @Column(name = "d_name", nullable = false, length = 30)
    private String d_name;
    @Column(name = "d_capacity", nullable = false, length = 30)
    private String d_capacity;
    @Column(name = "d_gender", nullable = false, length = 20)
    private String d_gender;

    
    
public Dorm (){
    //
}

public Dorm( String d_name, String d_capacity, String d_gender) {
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

public String getD_capacity() {
    return d_capacity;
}

public void setD_capacity(String d_capacity) {
    this.d_capacity = d_capacity;
}

public String getD_gender() {
    return d_gender;
}

public void setD_gender(String d_gender) {
    this.d_gender = d_gender;
}


@Override
public String toString() {
    return d_name + "|" + d_capacity + "|" + d_gender;
}
}