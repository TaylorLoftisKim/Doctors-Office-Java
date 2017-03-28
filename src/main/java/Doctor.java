import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Doctor {
  private String name;
  private String specialty;
  private int id;

  public Doctor(String name, String specialty) {
    this.name = name;
    this.specialty = specialty;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }

  public int getId() {
    return id;
  }

  public List<Patient> getPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctorId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Patient.class);
    }
  }

  public static List<Doctor> all() {
    String sql = "SELECT id, specialty FROM doctors";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }
}
