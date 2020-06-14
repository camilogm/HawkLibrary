package sv.hawkframework.com;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.hawkframework.com.ORM.ORMApplicationTables;
import sv.hawkframework.com.validators.ErrorORM;
import sv.hawkframework.com.validators.NotDuplicatedException;

public class ORMMain {

	private static ORMApplicationTables<Prueba> pruebaORM= new ORMApplicationTables<>(Prueba.class);
	
	public static void main (String[] args) { 
		
		Prueba prueba = pruebaORM.find(21);
		Prueba otra = new Prueba(0,"WEY","nono");
		prueba.setNombre("nono");
		Object[][] conditions = null;
		ArrayList<Prueba> pruebas = pruebaORM.findMany(conditions);
		
		for (Prueba pru : pruebas) {
			System.out.println("prueba : "+pru.getNombre());
		}
		
		try {
			
			pruebaORM.setObject(otra);
			pruebaORM.addAndSave();
		
			pruebaORM.setObject(prueba);
			pruebaORM.updateAndSave();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (NotDuplicatedException e) { 
			
			for ( ErrorORM error : e.getFieldErrors()) { 
				System.out.println(error.getField());
				System.out.println(error.getMessage());
			}
		}
		
	}
	
}
