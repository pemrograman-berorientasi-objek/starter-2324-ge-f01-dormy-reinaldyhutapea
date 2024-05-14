package pbo.f01.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "ASSIGNMENT", uniqueConstraints = @UniqueConstraint(columnNames = { "s_name" }))
public class Assignment {
    @Id
    @Column(name = "s_Id", nullable = false, length = 20)
    private String s_Id;
    @Column(name = "d_name", nullable = false, length = 20)
    private String d_name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "ASSIGNMENT",
    joinColumns = @JoinColumn(name = "s_Id"),
    inverseJoinColumns = @JoinColumn(name = "d_name")
    )
    private Set<Dorm> Dorms = new HashSet<>();

    public Assignment(){
        //default
    }

    public Assignment(String s_Id, String d_name){
        this.s_Id = s_Id;
        this.d_name = d_name;
    }

    public String getS_Id(){
        return s_Id;
    }

    public void setS_Id(String s_Id){
        this.s_Id = s_Id;
    }
    public String getD_name(){
        return d_name;
    }
    public void setD_name(String d_name){
        this.d_name = d_name;
    }
    
    @Override
    public String toString(){
        return s_Id + "|" + d_name;
    }
}
