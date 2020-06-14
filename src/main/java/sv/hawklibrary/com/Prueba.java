package sv.hawklibrary.com;

import com.google.gson.annotations.SerializedName;

import sv.hawklibrary.com.ORM.Annotations.DataModelAnnotations;
import sv.hawklibrary.com.ORM.Annotations.NotDuplicated;
import sv.hawklibrary.com.ORM.Annotations.PrimaryKey;

@DataModelAnnotations(tableName = "Prueba")
public class Prueba {
	
	@PrimaryKey
	private Integer id;
	@NotDuplicated
	private String codigo;
	@NotDuplicated
	private String nombre;
	@SerializedName("prueba_campo")
	private String pruebaCampo;
	
	public Prueba() {
		
	}
	public Prueba(Integer id, String codigo, String nombre,String pruebaCampo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.pruebaCampo = pruebaCampo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPruebaCampo() {
		return pruebaCampo;
	}
	public void setPruebaCampo(String pruebaCampo) {
		this.pruebaCampo = pruebaCampo;
	}
	
	
	
	
	

}
