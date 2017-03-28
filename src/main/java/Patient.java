import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Patient {
  private String name;
  private String birthday;
  private int id;
  private int doctorId;

  public Patient(String name, String birthday) {
    this.name = name;
    this.birthday = birthday;
    doctorId = 0;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }

  public int getId() {
    return id;
  }

  public void addDocId(int input){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE patients SET doctorId = :doctorId WHERE id= :id";
      con.createQuery(sql)
        .addParameter("doctorId", input)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Patient> all() {
    String sql = "SELECT id, name, birthday, doctorId FROM patients";
     try (Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Patient.class);
     }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where id=:id";
      Patient pt = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return pt;
    }
  }

  public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO patients (name,birthday,doctorId) VALUES (:name, :birthday, :doctorId)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("name", this.name)
       .addParameter("birthday", this.birthday)
       .addParameter("doctorId", this.doctorId)
       .executeUpdate()
       .getKey();
   }
 }

  @Override
  public String toString(){
    return name + birthday + doctorId;
  }

}
