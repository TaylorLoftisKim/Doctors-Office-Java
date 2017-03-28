import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PatientTest {

  Patient pt1 = new Patient("Alex","123",1);

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctors_office", null, null);
  }

  // @After
  // public void tearDown() {
  //   try (Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM patients *;";
  //     con.createQuery(sql).executeUpdate();
  //   }
  // }


  @Test
  public void create_data(){
    Patient pt2 = new Patient("John", "dong",3);
    pt2.save();
    System.out.println(pt2.find(14));
    System.out.println(pt2.all());
    assertEquals(true, pt2.find(14) instanceof Patient );
  }

  @Test
  public void check_if_all_works(){
    pt1.save();
    assertEquals(true, pt1.all() instanceof List<?>);

  }

}
