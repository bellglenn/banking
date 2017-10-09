package mains;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

class Main {
  
	public static void main(String args[]) throws Exception {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml", Main.class);
    DataSource dataSource = (DataSource) ac.getBean("dataSource");
    // DataSource mysqlDataSource = (DataSource) ac.getBean("mysqlDataSource");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

//    jdbcTemplate.batchUpdate(new String[] { "update customer set first_name = 'FN#'",
//        "delete from customer where id > 2" });

  }
}
