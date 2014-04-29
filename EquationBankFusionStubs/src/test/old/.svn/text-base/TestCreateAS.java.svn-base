package test.old;

import java.io.File;

import com.trapedza.bankfusion.core.MetaDataEnum;
import com.trapedza.bankfusion.core.Wrapper;
import com.trapedza.bankfusion.core.attributes.Attribute;

public class TestCreateAS

// Tests the ability to create BankFusion activity steps from Java code

{
	public TestCreateAS()
	{
	}
	public static void main(String args[])
	{
		// Basic values
		String name = "ActivityStep7";
		String description = "Activity Step 7 description";
		String codeProviderName = "AS7 Code Provider";
		String superDescriptorResourceId = "AS7 Super Descriptor";

		Wrapper o1 = BFToolbox.createActivityStepBase(name, description, codeProviderName, superDescriptorResourceId);

		// Tags
		Attribute tag;
		String tagName;
		String tagDescription;
		Object tagDefaultValue;
		String tagType;

		tagName = "InputTag1";
		tagDescription = "Input Tag 1 Description";
		tagDefaultValue = "Input Tag 1 Default Value";
		tagType = MetaDataEnum.PROP_INPUT_TAGS;

		tag = BFToolbox.createStringTagAttribute(tagName, tagDescription, tagDefaultValue, tagType);
		o1 = BFToolbox.attachActivityStepTag(tag, o1);

		tagName = "InputTag2";
		tagDescription = "Input Tag 2 Description";
		tagDefaultValue = "Input Tag 2 Default Value";
		tagType = MetaDataEnum.PROP_INPUT_TAGS;

		tag = BFToolbox.createStringTagAttribute(tagName, tagDescription, tagDefaultValue, tagType);
		o1 = BFToolbox.attachActivityStepTag(tag, o1);

		tagName = "OutputTag1";
		tagDescription = "Output Tag 1 Description";
		tagDefaultValue = "Output Tag 1 Default Value";
		tagType = MetaDataEnum.PROP_OUTPUT_TAGS;

		tag = BFToolbox.createStringTagAttribute(tagName, tagDescription, tagDefaultValue, tagType);
		o1 = BFToolbox.attachActivityStepTag(tag, o1);

		tagName = "InputTag3";
		tagDescription = "Input Tag 3 Description";
		tagDefaultValue = "Input Tag 3 Default Value";
		tagType = MetaDataEnum.PROP_INPUT_TAGS;

		tag = BFToolbox.createStringTagAttribute(tagName, tagDescription, tagDefaultValue, tagType);
		o1 = BFToolbox.attachActivityStepTag(tag, o1);

		File file = new File("E:\\BankFusion\\eclipse\\Workspace\\EquationBankFusionStubs\\output\\ActivityStep7.asd");
		BFToolbox.writeBFObject(o1, file);
		o1 = (Wrapper) BFToolbox.readBFFile(file);
		BFToolbox.writeBFObject(o1, file);

	}
}
