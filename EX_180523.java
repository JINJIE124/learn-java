package EX1;
import java.sql.*;
public class EX_180523 {
         public static void main(String args[]) {
        	 Connection con;
        	 Statement sql;
        	 ResultSet rs;
        	 String DriverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        	 String DataBase="jdbc:sqlserver://localhost:1433;databaseName=MyTest";
        	 try {
        		 Class.forName(DriverName);
        	 }
        	 catch(Exception e){
        		 System.out.println(e);
        	 }
        	 try {
        		 con=DriverManager.getConnection(DataBase,"sa","123456");
        		 sql=con.createStatement();
        		 rs=sql.executeQuery("select * from E where salary>0");
        		 while(rs.next()) {
        			 String number=rs.getString(1);
        			 String name=rs.getString(2);
        			 Date date=rs.getDate("Birthday");
        			 int salary=rs.getInt("salary");
        			 System.out.print(number+"\t");
        			 System.out.print(name+"\t");
        			 System.out.print(date+"\t");
        			 System.out.print(salary+" \n");
        			 
        		 }
        		 con.close();
        	 }
        	 catch(Exception e) {
        		 System.out.println(e);
        	 }
         }
}
