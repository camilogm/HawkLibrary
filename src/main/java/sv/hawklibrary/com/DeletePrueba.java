package sv.hawklibrary.com;

import java.sql.SQLException;

import sv.hawklibrary.com.ORM.ORMApplicationTables;

public class DeletePrueba {

	public static void main(String[] args) { 
		
		ORMApplicationTables<Prueba> pruebaORM = new ORMApplicationTables<>(Prueba.class);
		Prueba pr = new Prueba(19,null,null,null);
		
		pruebaORM.setObject(pr);
		try {
			
			if (pruebaORM.deleteAndSave())
				System.out.println("borrado");
			
			
		} catch (SQLException e) {
			System.out.println("wea");
			e.printStackTrace();
		}
		
		
		
	}
	
}
