/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: ZT1Descriptor.java 4945 2009-09-25 09:44:40Z blossem1 $
 */

package bf.com.misys.eq4.service.zt1.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import bf.com.misys.eq4.service.zt1.ZT1;

/**
 * Class ZT1Descriptor.
 * 
 * @version $Revision$ $Date$
 */
public class ZT1Descriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public ZT1Descriptor() {
        super();
        _nsURI = "http://www.misys.com/eq4/service/zt1";
        _xmlName = "ZT1";
        _elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _ANC_CUS_customerMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CUS_customerMnemonic", "ANC_CUS_customerMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CUS_customerMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CUS_customerMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CUS_customerMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CUS_customerMnemonic);
        //-- validation code for: _ANC_CUS_customerMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CLC_customerLocation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CLC_customerLocation", "ANC_CLC_customerLocation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CLC_customerLocation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CLC_customerLocation( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CLC_customerLocation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CLC_customerLocation);
        //-- validation code for: _ANC_CLC_customerLocation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CUN_customerFullName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CUN_customerFullName", "ANC_CUN_customerFullName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CUN_customerFullName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CUN_customerFullName( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CUN_customerFullName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CUN_customerFullName);
        //-- validation code for: _ANC_CUN_customerFullName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CPNC_customersBasicNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CPNC_customersBasicNumber", "ANC_CPNC_customersBasicNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CPNC_customersBasicNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CPNC_customersBasicNumber( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CPNC_customersBasicNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CPNC_customersBasicNumber);
        //-- validation code for: _ANC_CPNC_customersBasicNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_DAS_defaultAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_DAS_defaultAccountShortName", "ANC_DAS_defaultAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_DAS_defaultAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_DAS_defaultAccountShortName( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_DAS_defaultAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_DAS_defaultAccountShortName);
        //-- validation code for: _ANC_DAS_defaultAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CTP_customerType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CTP_customerType", "ANC_CTP_customerType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CTP_customerType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CTP_customerType( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CTP_customerType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CTP_customerType);
        //-- validation code for: _ANC_CTP_customerType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_BRNM_branchMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_BRNM_branchMnemonic", "ANC_BRNM_branchMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_BRNM_branchMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_BRNM_branchMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_BRNM_branchMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_BRNM_branchMnemonic);
        //-- validation code for: _ANC_BRNM_branchMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CRB1_taxCode1
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CRB1_taxCode1", "ANC_CRB1_taxCode1", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CRB1_taxCode1();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CRB1_taxCode1( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CRB1_taxCode1 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CRB1_taxCode1);
        //-- validation code for: _ANC_CRB1_taxCode1
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ANC_CRB2_taxCode2
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANC_CRB2_taxCode2", "ANC_CRB2_taxCode2", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANC_CRB2_taxCode2();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANC_CRB2_taxCode2( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANC_CRB2_taxCode2 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANC_CRB2_taxCode2);
        //-- validation code for: _ANC_CRB2_taxCode2
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ANX_EAD1_eMailAddress1
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ANX_EAD1_eMailAddress1", "ANX_EAD1_eMailAddress1", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getANX_EAD1_eMailAddress1();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setANX_EAD1_eMailAddress1( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ANX_EAD1_eMailAddress1 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ANX_EAD1_eMailAddress1);
        //-- validation code for: _ANX_EAD1_eMailAddress1
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(60);
        }
        desc.setValidator(fieldValidator);
        //-- _CAA_NA1_addressLine1
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CAA_NA1_addressLine1", "CAA_NA1_addressLine1", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getCAA_NA1_addressLine1();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setCAA_NA1_addressLine1( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_CAA_NA1_addressLine1 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CAA_NA1_addressLine1);
        //-- validation code for: _CAA_NA1_addressLine1
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _CAA_NA2_addressLine2
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CAA_NA2_addressLine2", "CAA_NA2_addressLine2", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getCAA_NA2_addressLine2();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setCAA_NA2_addressLine2( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_CAA_NA2_addressLine2 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CAA_NA2_addressLine2);
        //-- validation code for: _CAA_NA2_addressLine2
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _CAA_NA3_addressLine3
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CAA_NA3_addressLine3", "CAA_NA3_addressLine3", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getCAA_NA3_addressLine3();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setCAA_NA3_addressLine3( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_CAA_NA3_addressLine3 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CAA_NA3_addressLine3);
        //-- validation code for: _CAA_NA3_addressLine3
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _CAA_NA4_addressLine4
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CAA_NA4_addressLine4", "CAA_NA4_addressLine4", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getCAA_NA4_addressLine4();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setCAA_NA4_addressLine4( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_CAA_NA4_addressLine4 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CAA_NA4_addressLine4);
        //-- validation code for: _CAA_NA4_addressLine4
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _CAA_NA5_addressLine5
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CAA_NA5_addressLine5", "CAA_NA5_addressLine5", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getCAA_NA5_addressLine5();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setCAA_NA5_addressLine5( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_CAA_NA5_addressLine5 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CAA_NA5_addressLine5);
        //-- validation code for: _CAA_NA5_addressLine5
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _MCO_C1R_customersC1Rating
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_MCO_C1R_customersC1Rating", "MCO_C1R_customersC1Rating", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getMCO_C1R_customersC1Rating();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setMCO_C1R_customersC1Rating( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_MCO_C1R_customersC1Rating = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_MCO_C1R_customersC1Rating);
        //-- validation code for: _MCO_C1R_customersC1Rating
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_AB_accountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_AB_accountBranch", "OCA_AB_accountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_AB_accountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_AB_accountBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_AB_accountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_AB_accountBranch);
        //-- validation code for: _OCA_AB_accountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_AN_basicPartOfAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_AN_basicPartOfAccountNumber", "OCA_AN_basicPartOfAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_AN_basicPartOfAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_AN_basicPartOfAccountNumber( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_AN_basicPartOfAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_AN_basicPartOfAccountNumber);
        //-- validation code for: _OCA_AN_basicPartOfAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_AS_accountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_AS_accountSuffix", "OCA_AS_accountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_AS_accountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_AS_accountSuffix( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_AS_accountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_AS_accountSuffix);
        //-- validation code for: _OCA_AS_accountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_ACT_accountType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_ACT_accountType", "OCA_ACT_accountType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_ACT_accountType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_ACT_accountType( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_ACT_accountType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_ACT_accountType);
        //-- validation code for: _OCA_ACT_accountType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_SHN_accountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_SHN_accountShortName", "OCA_SHN_accountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_SHN_accountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_SHN_accountShortName( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_SHN_accountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_SHN_accountShortName);
        //-- validation code for: _OCA_SHN_accountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_CCY_currencyMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_CCY_currencyMnemonic", "OCA_CCY_currencyMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_CCY_currencyMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_CCY_currencyMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_CCY_currencyMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_CCY_currencyMnemonic);
        //-- validation code for: _OCA_CCY_currencyMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _OCA_OAD_dateAccountOpened
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_OCA_OAD_dateAccountOpened", "OCA_OAD_dateAccountOpened", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getOCA_OAD_dateAccountOpened();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setOCA_OAD_dateAccountOpened( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_OCA_OAD_dateAccountOpened = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_OCA_OAD_dateAccountOpened);
        //-- validation code for: _OCA_OAD_dateAccountOpened
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_DLP_dealType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_DLP_dealType", "RDS_DLP_dealType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_DLP_dealType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_DLP_dealType( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_DLP_dealType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_DLP_dealType);
        //-- validation code for: _RDS_DLP_dealType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_DLR_dealReference
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_DLR_dealReference", "RDS_DLR_dealReference", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_DLR_dealReference();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_DLR_dealReference( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_DLR_dealReference = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_DLR_dealReference);
        //-- validation code for: _RDS_DLR_dealReference
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(13);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_BRNM_branchMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_BRNM_branchMnemonic", "RDS_BRNM_branchMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_BRNM_branchMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_BRNM_branchMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_BRNM_branchMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_BRNM_branchMnemonic);
        //-- validation code for: _RDS_BRNM_branchMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_CUS_customerMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_CUS_customerMnemonic", "RDS_CUS_customerMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_CUS_customerMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_CUS_customerMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_CUS_customerMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_CUS_customerMnemonic);
        //-- validation code for: _RDS_CUS_customerMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_CLC_customerLocation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_CLC_customerLocation", "RDS_CLC_customerLocation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_CLC_customerLocation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_CLC_customerLocation( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_CLC_customerLocation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_CLC_customerLocation);
        //-- validation code for: _RDS_CLC_customerLocation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_CCY_currencyMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_CCY_currencyMnemonic", "RDS_CCY_currencyMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_CCY_currencyMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_CCY_currencyMnemonic( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_CCY_currencyMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_CCY_currencyMnemonic);
        //-- validation code for: _RDS_CCY_currencyMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_DLA_dealAmount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_DLA_dealAmount", "RDS_DLA_dealAmount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_DLA_dealAmount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_DLA_dealAmount( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_DLA_dealAmount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_DLA_dealAmount);
        //-- validation code for: _RDS_DLA_dealAmount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_SDT_startDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_SDT_startDate", "RDS_SDT_startDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_SDT_startDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_SDT_startDate( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_SDT_startDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_SDT_startDate);
        //-- validation code for: _RDS_SDT_startDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_MDT_maturityDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_MDT_maturityDate", "RDS_MDT_maturityDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_MDT_maturityDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_MDT_maturityDate( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_MDT_maturityDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_MDT_maturityDate);
        //-- validation code for: _RDS_MDT_maturityDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_CTRD_contractDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_CTRD_contractDate", "RDS_CTRD_contractDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_CTRD_contractDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_CTRD_contractDate( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_CTRD_contractDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_CTRD_contractDate);
        //-- validation code for: _RDS_CTRD_contractDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ABF_fundingSettlementBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ABF_fundingSettlementBranch", "RDS_ABF_fundingSettlementBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ABF_fundingSettlementBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ABF_fundingSettlementBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ABF_fundingSettlementBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ABF_fundingSettlementBranch);
        //-- validation code for: _RDS_ABF_fundingSettlementBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ANF_fundingSettlementACNo
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ANF_fundingSettlementACNo", "RDS_ANF_fundingSettlementACNo", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ANF_fundingSettlementACNo();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ANF_fundingSettlementACNo( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ANF_fundingSettlementACNo = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ANF_fundingSettlementACNo);
        //-- validation code for: _RDS_ANF_fundingSettlementACNo
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ASF_fundingSettlementACSfx
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ASF_fundingSettlementACSfx", "RDS_ASF_fundingSettlementACSfx", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ASF_fundingSettlementACSfx();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ASF_fundingSettlementACSfx( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ASF_fundingSettlementACSfx = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ASF_fundingSettlementACSfx);
        //-- validation code for: _RDS_ASF_fundingSettlementACSfx
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_XMF_fundingTransferMethod
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_XMF_fundingTransferMethod", "RDS_XMF_fundingTransferMethod", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_XMF_fundingTransferMethod();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_XMF_fundingTransferMethod( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_XMF_fundingTransferMethod = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_XMF_fundingTransferMethod);
        //-- validation code for: _RDS_XMF_fundingTransferMethod
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ABM_maturitySettlementBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ABM_maturitySettlementBranch", "RDS_ABM_maturitySettlementBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ABM_maturitySettlementBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ABM_maturitySettlementBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ABM_maturitySettlementBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ABM_maturitySettlementBranch);
        //-- validation code for: _RDS_ABM_maturitySettlementBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ANM_maturitySettlementACNo
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ANM_maturitySettlementACNo", "RDS_ANM_maturitySettlementACNo", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ANM_maturitySettlementACNo();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ANM_maturitySettlementACNo( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ANM_maturitySettlementACNo = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ANM_maturitySettlementACNo);
        //-- validation code for: _RDS_ANM_maturitySettlementACNo
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ASM_maturitySettlementACSfx
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ASM_maturitySettlementACSfx", "RDS_ASM_maturitySettlementACSfx", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ASM_maturitySettlementACSfx();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ASM_maturitySettlementACSfx( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ASM_maturitySettlementACSfx = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ASM_maturitySettlementACSfx);
        //-- validation code for: _RDS_ASM_maturitySettlementACSfx
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_XMM_maturityTransferMethod
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_XMM_maturityTransferMethod", "RDS_XMM_maturityTransferMethod", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_XMM_maturityTransferMethod();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_XMM_maturityTransferMethod( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_XMM_maturityTransferMethod = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_XMM_maturityTransferMethod);
        //-- validation code for: _RDS_XMM_maturityTransferMethod
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ABI_interestSettlementBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ABI_interestSettlementBranch", "RDS_ABI_interestSettlementBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ABI_interestSettlementBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ABI_interestSettlementBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ABI_interestSettlementBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ABI_interestSettlementBranch);
        //-- validation code for: _RDS_ABI_interestSettlementBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ANI_interestSettlementACNo
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ANI_interestSettlementACNo", "RDS_ANI_interestSettlementACNo", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ANI_interestSettlementACNo();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ANI_interestSettlementACNo( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ANI_interestSettlementACNo = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ANI_interestSettlementACNo);
        //-- validation code for: _RDS_ANI_interestSettlementACNo
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_ASI_interestSettlementACSfx
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_ASI_interestSettlementACSfx", "RDS_ASI_interestSettlementACSfx", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_ASI_interestSettlementACSfx();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_ASI_interestSettlementACSfx( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_ASI_interestSettlementACSfx = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_ASI_interestSettlementACSfx);
        //-- validation code for: _RDS_ASI_interestSettlementACSfx
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_XMI_interestTransferMethod
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_XMI_interestTransferMethod", "RDS_XMI_interestTransferMethod", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_XMI_interestTransferMethod();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_XMI_interestTransferMethod( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_XMI_interestTransferMethod = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_XMI_interestTransferMethod);
        //-- validation code for: _RDS_XMI_interestTransferMethod
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _RDS_PRC_periodCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_RDS_PRC_periodCode", "RDS_PRC_periodCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getRDS_PRC_periodCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setRDS_PRC_periodCode( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_RDS_PRC_periodCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_RDS_PRC_periodCode);
        //-- validation code for: _RDS_PRC_periodCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_AB_accountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_AB_accountBranch", "ASC_AB_accountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_AB_accountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_AB_accountBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_AB_accountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_AB_accountBranch);
        //-- validation code for: _ASC_AB_accountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_AN_basicPartOfAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_AN_basicPartOfAccountNumber", "ASC_AN_basicPartOfAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_AN_basicPartOfAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_AN_basicPartOfAccountNumber( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_AN_basicPartOfAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_AN_basicPartOfAccountNumber);
        //-- validation code for: _ASC_AN_basicPartOfAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_AS_accountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_AS_accountSuffix", "ASC_AS_accountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_AS_accountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_AS_accountSuffix( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_AS_accountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_AS_accountSuffix);
        //-- validation code for: _ASC_AS_accountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_VFR_valueFromDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_VFR_valueFromDate", "ASC_VFR_valueFromDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_VFR_valueFromDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_VFR_valueFromDate( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_VFR_valueFromDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_VFR_valueFromDate);
        //-- validation code for: _ASC_VFR_valueFromDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_DRF_usersOwnReferenceForDealsReconciliationEtc
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_DRF_usersOwnReferenceForDealsReconciliationEtc", "ASC_DRF_usersOwnReferenceForDealsReconciliationEtc", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_DRF_usersOwnReferenceForDealsReconciliationEtc();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_DRF_usersOwnReferenceForDealsReconciliationEtc( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_DRF_usersOwnReferenceForDealsReconciliationEtc = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_DRF_usersOwnReferenceForDealsReconciliationEtc);
        //-- validation code for: _ASC_DRF_usersOwnReferenceForDealsReconciliationEtc
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_AMA_amount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_AMA_amount", "ASC_AMA_amount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_AMA_amount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_AMA_amount( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_AMA_amount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_AMA_amount);
        //-- validation code for: _ASC_AMA_amount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ASC_TCD_transactionCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASC_TCD_transactionCode", "ASC_TCD_transactionCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASC_TCD_transactionCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASC_TCD_transactionCode( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASC_TCD_transactionCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASC_TCD_transactionCode);
        //-- validation code for: _ASC_TCD_transactionCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ASD_AB_accountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASD_AB_accountBranch", "ASD_AB_accountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASD_AB_accountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASD_AB_accountBranch( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASD_AB_accountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASD_AB_accountBranch);
        //-- validation code for: _ASD_AB_accountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ASD_AN_basicPartOfAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASD_AN_basicPartOfAccountNumber", "ASD_AN_basicPartOfAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASD_AN_basicPartOfAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASD_AN_basicPartOfAccountNumber( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASD_AN_basicPartOfAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASD_AN_basicPartOfAccountNumber);
        //-- validation code for: _ASD_AN_basicPartOfAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ASD_AS_accountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASD_AS_accountSuffix", "ASD_AS_accountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASD_AS_accountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASD_AS_accountSuffix( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASD_AS_accountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASD_AS_accountSuffix);
        //-- validation code for: _ASD_AS_accountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ASD_TCD_transactionCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ASD_TCD_transactionCode", "ASD_TCD_transactionCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ZT1 target = (ZT1) object;
                return target.getASD_TCD_transactionCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ZT1 target = (ZT1) object;
                    target.setASD_TCD_transactionCode( (java.lang.String) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);
        
        java.util.List substitutionGroupes_ASD_TCD_transactionCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ASD_TCD_transactionCode);
        //-- validation code for: _ASD_TCD_transactionCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    public java.lang.Class getJavaClass(
    ) {
        return bf.com.misys.eq4.service.zt1.ZT1.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    public java.lang.String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    public java.lang.String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    public java.lang.String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}