package sv.hawkframework.com.ORM;

public class WhereStructure {

	private String fieldName;
	private String fieldValue;
	private String operator;
	private String concatCondition;
	
	public WhereStructure() {
		
	}

	public WhereStructure(String fieldName, String fieldValue, String operator, String concatCondition) {
	
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.operator = operator;
		this.concatCondition = concatCondition;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getConcatCondition() {
		return concatCondition;
	}

	public void setConcatCondition(String concatCondition) {
		this.concatCondition = concatCondition;
	}
	
	
	
	
	
}
