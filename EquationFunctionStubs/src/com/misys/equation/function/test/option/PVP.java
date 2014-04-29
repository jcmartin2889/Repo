package com.misys.equation.function.test.option;

import com.misys.equation.function.test.helper.EquationTestCasePromptValidate;

public class PVP extends EquationTestCasePromptValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PVP.java 4735 2009-09-15 16:58:25Z lima12 $";
	public void addFieldsWithPrompts()
	{
		setWrapper.addDisplayFieldWrapper(createField("SIT1", "Type", 4, "AAAR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT2", "Component", 6, "AAJR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT3", "Ccy", 3, "AAKR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT4", "Ccy", 3, "AAKR15R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT5", "Brnm", 4, "AAKR16R", ""));

		// AAKR17R - JUnit fail
		setWrapper.addDisplayFieldWrapper(createField("SIT6", "Acc", 13, "AAKR17R", ""));

		// AAKR18R - JUnit fail (Java Exception)
		setWrapper.addDisplayFieldWrapper(createField("SIT7", "Commitment Ref", 13, "AAKR18R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT8", "SortCode", 9, "A2R10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SIT9", "Channel", 6, "AALR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI10", "Code", 3, "AANR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI11", "Type", 3, "AAPR10R", ""));

		// AAQR10R - JUnit fail
		setWrapper.addDisplayFieldWrapper(createField("SI12", "Mnem", 3, "AAQR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI13", "IdDoc", 2, "AAWR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI14", "NvCode", 2, "AAXR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI15", "OSCode", 2, "AAYR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI16", "Code", 2, "ABTR10R", ""));

		// ABNR30R - JUnit fail
		setWrapper.addDisplayFieldWrapper(createField("SI17", "SortCode", 9, "ABNR30R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI18", "TaxRef", 16, "ABXR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI19", "Widgets", 3, "ACER10R", ""));

		// ABNR30R - JUnit fail (Data)
		setWrapper.addDisplayFieldWrapper(createField("SI20", "Ref", 16, "AD1R10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI21", "Option", 3, "AER10R", ""));

		// AC1R10R - JUnit fail
		setWrapper.addDisplayFieldWrapper(createField("SI22", "Acc", 13, "AC1R10R", ""));

		setWrapper.addDisplayFieldWrapper(createField("SI23", "Reference", 35, "ALCR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI24", "DocType", 6, "BBAR10R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI25", "EqFile", 2, "BER01R", ""));
		setWrapper.addDisplayFieldWrapper(createField("SI26", "CodeValue", 10, "BHR01R", ""));
	}
}
