/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.hawkframework.com.ORM;

import java.lang.annotation.Annotation;

import sv.hawkframework.com.ORM.Annotations.DataModelAnnotations;





/**
 *
 * @author Camilo González 
 * @version 0.01
 */

public class TablesDataProperties {
	
	
	  /**	  
	   * @param object
	   * @return the table name
	   * @throws NullPointerException
	   * 
	   */
      public String getTableName(Object object) throws NullPointerException{
        
        
            
              Class<? extends Object> classProperties = object.getClass();
             
              Annotation annotation = classProperties.getAnnotation(DataModelAnnotations.class);
              DataModelAnnotations tableProperties = (DataModelAnnotations) annotation;
             
              return tableProperties.tableName();
            
            
        
     }
      
      /**
       * 
       * @param object
       * @return the id name of the table
       * @throws NullPointerException
       */
      
      public String getIdName(Object object) throws NullPointerException{
        
              
              Class<? extends Object> classProperties = object.getClass();
              Annotation annotation = classProperties.getAnnotation(DataModelAnnotations.class);
              DataModelAnnotations tableProperties = (DataModelAnnotations) annotation;
             
              return tableProperties.Id();
              
          
      }
    
}
