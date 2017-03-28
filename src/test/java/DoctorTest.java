import org.sql2o.*;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

public class DoctorTest {

  Doctor doc1 = new Doctor("Kirk","Surgery");

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctors_office", null, null);
  }
  //
  // @After
  // public void tearDown() {
  //   try (Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM name_of_your_table *;";
  //     con.createQuery(sql).executeUpdate();
  //   }
  // }

  @Test
  public void test_Doctor_instantiation(){
    assertEquals(true, doc1 instanceof Doctor);
  }

  @Test
  public void test_ReturnsAllPatientsInList() {
    Doctor doctor = new Doctor("Dr. Dong", "Koreans");
    Patient patients = new Patient("Alex", "123", 1);
    patients.save();
    assertEquals(true, doctor.getPatients() instanceof List<?>);
  }

}
