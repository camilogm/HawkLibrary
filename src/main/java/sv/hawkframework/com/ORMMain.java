package sv.hawkframework.com;

import java.util.ArrayList;

import sv.hawkframework.com.ORM.ORMApplicationTables;

public class ORMMain {

	private static ORMApplicationTables<Departamento> depaORM= new ORMApplicationTables<>();
	
	public static void main (String[] args) { 
		Departamento depa = new Departamento(0,"FFG","NUEVO");
		depaORM.setObject(depa);
		depaORM.addAndSave();
		
	}
	
}
