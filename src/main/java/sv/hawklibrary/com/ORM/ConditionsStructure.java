package sv.hawklibrary.com.ORM;


public class ConditionsStructure {

	private Integer min;
	private Integer max;
	private WhereStructure[] conditions;
	private String[] fields;
	private static final int maxRow=25;
	
	/**
	 * @param min
	 * @param max
	 * @param conditions
	 * @param fields
	 */
	
	public ConditionsStructure() {
		
	}
	
	public ConditionsStructure(Integer min, Integer max, WhereStructure[] conditions,String[] fields) {
		this.min = min;
		this.max = max;
		this.conditions = conditions;
		this.fields = fields;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}


	public WhereStructure[] getConditions() {
		return conditions;
	}

	public void setConditions(WhereStructure[] conditions) {
		this.conditions = conditions;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	
	public Object[][] serializeConditions(){	
		return SerializeObjects.getConditionsAsMatrix(conditions);
	}
	
	public Integer[] serializeLimits() {
		return SerializeObjects.getRangeAsIntArray(min, max, maxRow);
		
	}
	
	
	
	
	
	
}
