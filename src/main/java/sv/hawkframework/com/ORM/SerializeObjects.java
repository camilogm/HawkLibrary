package sv.hawkframework.com.ORM;

/**
 * 
 * @author Camilo González
 * @version 1.0
 */
public class SerializeObjects {

	
	public static final Object[][] getConditionsAsMatrix(WhereStructure[] conditions ){
		
		Object[][] conditionsInMatrix=new Object[conditions.length][];
		WhereStructure ws;
		for (int i=0;i<conditions.length;i++) {	
			ws=conditions[i];
			Object[] condition= {ws.getFieldName(),ws.getOperator(),ws.getFieldValue(),ws.getConcatCondition()};
			conditionsInMatrix[i]=condition;
		}	
		return conditionsInMatrix;
		
	}
	
	
	public static final Integer[] getRangeAsIntArray(Integer min, Integer max,Integer maxRow) { 
		
		Integer[] limits;
		if (max==null && min==null) 
			limits=new Integer[]{0,maxRow};		
		else if (max==null && min!=null){
			limits=new Integer[] {min,maxRow};
		}
		else if (max!=null && min==null)
			limits=new Integer[] {0,max};
		else
			limits=new Integer[] {min,max};
		
		return limits;
	}
}
