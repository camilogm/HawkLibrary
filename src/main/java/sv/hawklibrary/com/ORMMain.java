package sv.hawklibrary.com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Scanner;

import sv.hawklibrary.com.ORM.ORMApplicationTables;
import sv.hawklibrary.com.validators.ErrorORM;
import sv.hawklibrary.com.validators.NotDuplicatedException;

public class ORMMain {

	private static ORMApplicationTables<Prueba> pruebaORM= new ORMApplicationTables<>(Prueba.class);
	
	public static void main (String[] args) { 
		
		Scanner sca = new Scanner(System.in);
		Boolean wea = true;
		
		
		
		do {
	
		
		try {
			
			Prueba otra = new Prueba(21,"WEY","nono","wewe");
			Object[][] conditions = null;
			
			System.out.println("el contador es: "+pruebaORM.getCount());
		
			
			pruebaORM.setObject(otra);
			pruebaORM.updateAndSave();;
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (NotDuplicatedException e) { 
			
			for ( ErrorORM error : e.getFieldErrors()) { 
				System.out.println(error.getField());
				System.out.println(error.getMessage());
			}
		} catch (NullPointerException ex) {
			System.out.println(ex.getStackTrace());	
		}
		
		System.out.println("1 pa salir");
		int d = sca.nextInt();
		if (d==1) {
			wea = false;
		}
		
		
		
		}while(wea);
		
	}
	
}
