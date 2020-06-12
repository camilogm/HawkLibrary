package sv.hawkframework.com.ORM.Validations;


import sv.hawkframework.com.ORM.Validations.Interfaces.IValidationTypes;


public class ValidationTypes implements IValidationTypes{

	@Override
	public String getValue(String value) {
		try {
            if ( value==null){
                value=null;
                
                return value;
            }
       
            Integer.parseInt(value);                
			return value+"";
		} catch (NumberFormatException nfe){
		    value="'"+value+"'";
		    return value;
		}
	}

	@Override
	public Boolean isNumeric(String value) {
		try {

            Integer.parseInt(value);
            return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
