package mains;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;    

public class Test {
  private final static String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
  private final static String USER = "gb";
  private final static String PASS = "Snapper_22";

//  public static void main(String[] args) {
//    Connection conn = null;  
//    try {    
//      Class.forName("oracle.jdbc.pool.OracleDataSource");    
//      System.out.println("Connecting to database...");    
//      conn = DriverManager.getConnection(DB_URL,USER,PASS);
//    } catch (Exception e) {    
//      e.printStackTrace();    
//    } finally {    
//      if (conn != null) {    
//        try {    
//          conn.close();    
//        } catch (SQLException e) {    
//          // ignore    
//        }    
//      }    
//    }            
//  }    
//}
  
	public static void main(String args[]) throws Exception {
	    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml", Main.class);
	    DataSource dataSource = (DataSource) ac.getBean("dataSource");
	    // DataSource mysqlDataSource = (DataSource) ac.getBean("mysqlDataSource");

	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

//	    jdbcTemplate.batchUpdate(new String[] { "update customer set first_name = 'FN#'",
//	        "delete from customer where id > 2" });

	  }
}
