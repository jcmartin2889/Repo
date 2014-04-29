package com.misys.equation.common.ant.builder.version;



/**
 * This class will increment the equation version number. 
 * It will how many digits are needed to increment the previous EQ version number in one.
 * @author DeRoset
 *
 */
public class EquationVersionIncrementor {

	public static final String _revision = "$Id: EquationVersionIncrementor.java 14766 2012-10-27 20:19:15Z whittap1 $";
	
	private double currentVersion;
	private double valueToIncrement;
	private int size=0;
	
	
	/**
	 * this is the only constructor that guarantee that this class cannot exits without a version.
	 * @param currentVersion
	 */
	public EquationVersionIncrementor( String currentVersion  ) {
		
		setCurrentVersion(currentVersion);
		 valueToIncrement=createValueToincrement(currentVersion );
	}
	
	/**
	 * this method will set the current EQ version number.
	 * @param currentVersion the current EQ version number.
	 */
	private void setCurrentVersion(String currentVersion  )
	{
		
		String[] currentVersionArray = currentVersion.split(EuqationVersionNumberDefinitions.VERSION_NUMBER_DELIMITER_REXP.toString());
		
		StringBuilder doubleFormatBuffer= new StringBuilder(currentVersionArray[0]).append(EuqationVersionNumberDefinitions.VERSION_NUMBER_DELIMITER.toString());
		
		for (int index = 1; index < currentVersionArray.length; index++) {
			
			doubleFormatBuffer.append( currentVersionArray[index]);
			
		}		
		this.currentVersion=Double.parseDouble( doubleFormatBuffer.toString());
	} 
	
	/**
	 * This method will increase the equation version number.
	 * @return this method will return the new equation version number.
	 */
	public String getNewEquationVersionNumber() {
		
		// Add 2 doubles and round to the correct number of decimals
		String s = "" + valueToIncrement;
		String[] result = s.split("\\."); 
	    double numDp = Math.pow(10., result[1].length()+1);
	    double newVersion = Math.rint((currentVersion + valueToIncrement) * numDp) / numDp;
		
		return convertToEquationVersionNunberFormat( newVersion );
	}
	
	/**
	 * This method will create the value used to increment the EQ version number</br>
	 * if the EQ last version number is 1.2.3 the double value created to increase the version is 001.
	 * This value will be created base on the length of EQ last version number 
	 * @param currentVersion EQ last version number 
	 * @return The create the value used to increment the EQ version number.
	 */
	private double createValueToincrement(String currentVersion ){
		
		String[] currentVersionArray = currentVersion.split("\\.");
		StringBuilder valueToIncrementBuffer= new StringBuilder();
		double valueToIncrement=0;
		
		size=currentVersionArray.length;
		valueToIncrementBuffer.append("0").append(".");
		
		for (int index = 2; index < currentVersionArray.length; index++) {
			
			valueToIncrementBuffer.append("0");
		}
		valueToIncrementBuffer.append("1");
		
		valueToIncrement=Double.parseDouble(valueToIncrementBuffer.toString());
		return valueToIncrement;
	} 
	
	/**
	 * This method will convert the passed parameter in to the EQ version number format. 
	 * For example 1.x.x 
	 * @param newEquationVersionNumber
	 * @return a String representation of the passed number in EQ version number format.
	 */
	private String convertToEquationVersionNunberFormat(double newEquationVersionNumber){
		
		char[] newEquationVersionNumberArray = String.valueOf(newEquationVersionNumber).toCharArray();
		int newEquationVersionNumberLength=String.valueOf(newEquationVersionNumber).replaceAll("\\.", "").length();
		StringBuilder equationVersionFormatedNumber=null;
		
		
				
		equationVersionFormatedNumber= new StringBuilder().append(String.valueOf( newEquationVersionNumberArray[0]));
		equationVersionFormatedNumber.append(".");
		
		for (int index = 2; index < newEquationVersionNumberArray.length; index++) {
			
			equationVersionFormatedNumber.append( newEquationVersionNumberArray[index]);
			equationVersionFormatedNumber.append(".");
		}
		
		if(newEquationVersionNumberLength < size){
			
			for (int index = newEquationVersionNumberLength; index < size; index++) {
				
				equationVersionFormatedNumber.append("0");
				equationVersionFormatedNumber.append(".");
			}
			
		}
				
		return equationVersionFormatedNumber.toString().substring(0, equationVersionFormatedNumber.length()-1);
	}
	
	
	/**Setter and getters **/	
	
	public void setCurrentVersion(double currentVersion) {
		
		this.currentVersion = currentVersion;
	}
	
	
	

}
