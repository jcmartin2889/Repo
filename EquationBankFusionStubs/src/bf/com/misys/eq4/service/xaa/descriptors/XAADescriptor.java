/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: XAADescriptor.java 7214 2010-05-10 14:20:19Z goldsmc1 $
 */

package bf.com.misys.eq4.service.xaa.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import bf.com.misys.eq4.service.xaa.XAA;

/**
 * Class XAADescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class XAADescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


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

    public XAADescriptor() {
        super();
        _nsURI = "http://www.misys.com/eq4/service/xaa";
        _xmlName = "XAA";
        _elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _HH_HRC_holdCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HH_HRC_holdCode", "HH_HRC_holdCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getHH_HRC_holdCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setHH_HRC_holdCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HH_HRC_holdCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HH_HRC_holdCode);
        //-- validation code for: _HH_HRC_holdCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _HH_HRD_holdDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HH_HRD_holdDescription", "HH_HRD_holdDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getHH_HRD_holdDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setHH_HRD_holdDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HH_HRD_holdDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HH_HRD_holdDescription);
        //-- validation code for: _HH_HRD_holdDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _HH_DED_defaultExpiryDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HH_DED_defaultExpiryDate", "HH_DED_defaultExpiryDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getHH_DED_defaultExpiryDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setHH_DED_defaultExpiryDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HH_DED_defaultExpiryDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HH_DED_defaultExpiryDate);
        //-- validation code for: _HH_DED_defaultExpiryDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAB_accountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAB_accountBranch", "AA_HZAB_accountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAB_accountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAB_accountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAB_accountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAB_accountBranch);
        //-- validation code for: _AA_HZAB_accountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAN_basicPartOfAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAN_basicPartOfAccountNumber", "AA_HZAN_basicPartOfAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAN_basicPartOfAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAN_basicPartOfAccountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAN_basicPartOfAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAN_basicPartOfAccountNumber);
        //-- validation code for: _AA_HZAN_basicPartOfAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAS_accountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAS_accountSuffix", "AA_HZAS_accountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAS_accountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAS_accountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAS_accountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAS_accountSuffix);
        //-- validation code for: _AA_HZAS_accountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSHN_accountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSHN_accountShortName", "AA_HZSHN_accountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSHN_accountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSHN_accountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSHN_accountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSHN_accountShortName);
        //-- validation code for: _AA_HZSHN_accountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSHNA_arabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSHNA_arabicShortName", "AA_HZSHNA_arabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSHNA_arabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSHNA_arabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSHNA_arabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSHNA_arabicShortName);
        //-- validation code for: _AA_HZSHNA_arabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCUS_customerMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCUS_customerMnemonic", "AA_HZCUS_customerMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCUS_customerMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCUS_customerMnemonic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCUS_customerMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCUS_customerMnemonic);
        //-- validation code for: _AA_HZCUS_customerMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCLC_customerLocation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCLC_customerLocation", "AA_HZCLC_customerLocation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCLC_customerLocation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCLC_customerLocation( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCLC_customerLocation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCLC_customerLocation);
        //-- validation code for: _AA_HZCLC_customerLocation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCUN_customerFullName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCUN_customerFullName", "AA_HZCUN_customerFullName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCUN_customerFullName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCUN_customerFullName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCUN_customerFullName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCUN_customerFullName);
        //-- validation code for: _AA_HZCUN_customerFullName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCUNA_arabicCustomerFullName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCUNA_arabicCustomerFullName", "AA_HZCUNA_arabicCustomerFullName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCUNA_arabicCustomerFullName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCUNA_arabicCustomerFullName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCUNA_arabicCustomerFullName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCUNA_arabicCustomerFullName);
        //-- validation code for: _AA_HZCUNA_arabicCustomerFullName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZACT_accountType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZACT_accountType", "AA_HZACT_accountType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZACT_accountType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZACT_accountType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZACT_accountType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZACT_accountType);
        //-- validation code for: _AA_HZACT_accountType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAPP_owningApplicationCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAPP_owningApplicationCode", "AA_HZAPP_owningApplicationCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAPP_owningApplicationCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAPP_owningApplicationCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAPP_owningApplicationCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAPP_owningApplicationCode);
        //-- validation code for: _AA_HZAPP_owningApplicationCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZACS_accountStructure
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZACS_accountStructure", "AA_HZACS_accountStructure", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZACS_accountStructure();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZACS_accountStructure( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZACS_accountStructure = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZACS_accountStructure);
        //-- validation code for: _AA_HZACS_accountStructure
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCCY_currencyMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCCY_currencyMnemonic", "AA_HZCCY_currencyMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCCY_currencyMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCCY_currencyMnemonic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCCY_currencyMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCCY_currencyMnemonic);
        //-- validation code for: _AA_HZCCY_currencyMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCED_currencyEditField
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCED_currencyEditField", "AA_HZCED_currencyEditField", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCED_currencyEditField();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCED_currencyEditField( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCED_currencyEditField = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCED_currencyEditField);
        //-- validation code for: _AA_HZCED_currencyEditField
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI47_internalNonCustomerAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI47_internalNonCustomerAccount", "AA_HZAI47_internalNonCustomerAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI47_internalNonCustomerAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI47_internalNonCustomerAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI47_internalNonCustomerAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI47_internalNonCustomerAccount);
        //-- validation code for: _AA_HZAI47_internalNonCustomerAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI14_deceasedOrLiquidated
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI14_deceasedOrLiquidated", "AA_HZAI14_deceasedOrLiquidated", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI14_deceasedOrLiquidated();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI14_deceasedOrLiquidated( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI14_deceasedOrLiquidated = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI14_deceasedOrLiquidated);
        //-- validation code for: _AA_HZAI14_deceasedOrLiquidated
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI17_accountStatusBlocked
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI17_accountStatusBlocked", "AA_HZAI17_accountStatusBlocked", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI17_accountStatusBlocked();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI17_accountStatusBlocked( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI17_accountStatusBlocked = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI17_accountStatusBlocked);
        //-- validation code for: _AA_HZAI17_accountStatusBlocked
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI20_accountStatusInactive
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI20_accountStatusInactive", "AA_HZAI20_accountStatusInactive", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI20_accountStatusInactive();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI20_accountStatusInactive( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI20_accountStatusInactive = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI20_accountStatusInactive);
        //-- validation code for: _AA_HZAI20_accountStatusInactive
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI30_accountClosing
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI30_accountClosing", "AA_HZAI30_accountClosing", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI30_accountClosing();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI30_accountClosing( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI30_accountClosing = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI30_accountClosing);
        //-- validation code for: _AA_HZAI30_accountClosing
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay", "AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay);
        //-- validation code for: _AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCTP_customerType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCTP_customerType", "AA_HZCTP_customerType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCTP_customerType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCTP_customerType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCTP_customerType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCTP_customerType);
        //-- validation code for: _AA_HZCTP_customerType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZACD_analysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZACD_analysisCode", "AA_HZACD_analysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZACD_analysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZACD_analysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZACD_analysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZACD_analysisCode);
        //-- validation code for: _AA_HZACD_analysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSAC_sundryAnalysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSAC_sundryAnalysisCode", "AA_HZSAC_sundryAnalysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSAC_sundryAnalysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSAC_sundryAnalysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSAC_sundryAnalysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSAC_sundryAnalysisCode);
        //-- validation code for: _AA_HZSAC_sundryAnalysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZNANC_numericAnalysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZNANC_numericAnalysisCode", "AA_HZNANC_numericAnalysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZNANC_numericAnalysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZNANC_numericAnalysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZNANC_numericAnalysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZNANC_numericAnalysisCode);
        //-- validation code for: _AA_HZNANC_numericAnalysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(5);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCNAL_residenceCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCNAL_residenceCountry", "AA_HZCNAL_residenceCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCNAL_residenceCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCNAL_residenceCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCNAL_residenceCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCNAL_residenceCountry);
        //-- validation code for: _AA_HZCNAL_residenceCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCNAR_riskCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCNAR_riskCountry", "AA_HZCNAR_riskCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCNAR_riskCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCNAR_riskCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCNAR_riskCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCNAR_riskCountry);
        //-- validation code for: _AA_HZCNAR_riskCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCNAP_parentCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCNAP_parentCountry", "AA_HZCNAP_parentCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCNAP_parentCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCNAP_parentCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCNAP_parentCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCNAP_parentCountry);
        //-- validation code for: _AA_HZCNAP_parentCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSFC_statementFrequencyCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSFC_statementFrequencyCode", "AA_HZSFC_statementFrequencyCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSFC_statementFrequencyCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSFC_statementFrequencyCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSFC_statementFrequencyCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSFC_statementFrequencyCode);
        //-- validation code for: _AA_HZSFC_statementFrequencyCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZBALS_balanceOnThePreviousStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZBALS_balanceOnThePreviousStatement", "AA_HZBALS_balanceOnThePreviousStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZBALS_balanceOnThePreviousStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZBALS_balanceOnThePreviousStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZBALS_balanceOnThePreviousStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZBALS_balanceOnThePreviousStatement);
        //-- validation code for: _AA_HZBALS_balanceOnThePreviousStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement", "AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement);
        //-- validation code for: _AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSTML_lastStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSTML_lastStatementDate", "AA_HZSTML_lastStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSTML_lastStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSTML_lastStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSTML_lastStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSTML_lastStatementDate);
        //-- validation code for: _AA_HZSTML_lastStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSTMN_nextStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSTMN_nextStatementDate", "AA_HZSTMN_nextStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSTMN_nextStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSTMN_nextStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSTMN_nextStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSTMN_nextStatementDate);
        //-- validation code for: _AA_HZSTMN_nextStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSTNL_lastStatementNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSTNL_lastStatementNumber", "AA_HZSTNL_lastStatementNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSTNL_lastStatementNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSTNL_lastStatementNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSTNL_lastStatementNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSTNL_lastStatementNumber);
        //-- validation code for: _AA_HZSTNL_lastStatementNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZRETP_retentionPeriod00None011MthSysDflt
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZRETP_retentionPeriod00None011MthSysDflt", "AA_HZRETP_retentionPeriod00None011MthSysDflt", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZRETP_retentionPeriod00None011MthSysDflt();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZRETP_retentionPeriod00None011MthSysDflt( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZRETP_retentionPeriod00None011MthSysDflt = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZRETP_retentionPeriod00None011MthSysDflt);
        //-- validation code for: _AA_HZRETP_retentionPeriod00None011MthSysDflt
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZNS3_numberOfReferenceCharactersForReconciliation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZNS3_numberOfReferenceCharactersForReconciliation", "AA_HZNS3_numberOfReferenceCharactersForReconciliation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZNS3_numberOfReferenceCharactersForReconciliation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZNS3_numberOfReferenceCharactersForReconciliation( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZNS3_numberOfReferenceCharactersForReconciliation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZNS3_numberOfReferenceCharactersForReconciliation);
        //-- validation code for: _AA_HZNS3_numberOfReferenceCharactersForReconciliation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZOAD_dateAccountOpened
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZOAD_dateAccountOpened", "AA_HZOAD_dateAccountOpened", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZOAD_dateAccountOpened();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZOAD_dateAccountOpened( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZOAD_dateAccountOpened = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZOAD_dateAccountOpened);
        //-- validation code for: _AA_HZOAD_dateAccountOpened
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZDLE_dateOfLastCustomerGeneratedEntry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZDLE_dateOfLastCustomerGeneratedEntry", "AA_HZDLE_dateOfLastCustomerGeneratedEntry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZDLE_dateOfLastCustomerGeneratedEntry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZDLE_dateOfLastCustomerGeneratedEntry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZDLE_dateOfLastCustomerGeneratedEntry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZDLE_dateOfLastCustomerGeneratedEntry);
        //-- validation code for: _AA_HZDLE_dateOfLastCustomerGeneratedEntry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCAD_dateAccountClosed
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCAD_dateAccountClosed", "AA_HZCAD_dateAccountClosed", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCAD_dateAccountClosed();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCAD_dateAccountClosed( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCAD_dateAccountClosed = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCAD_dateAccountClosed);
        //-- validation code for: _AA_HZCAD_dateAccountClosed
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZDLM_dateLastMaintained
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZDLM_dateLastMaintained", "AA_HZDLM_dateLastMaintained", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZDLM_dateLastMaintained();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZDLM_dateLastMaintained( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZDLM_dateLastMaintained = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZDLM_dateLastMaintained);
        //-- validation code for: _AA_HZDLM_dateLastMaintained
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZP1R_p1RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZP1R_p1RatingForAccount", "AA_HZP1R_p1RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZP1R_p1RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZP1R_p1RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZP1R_p1RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZP1R_p1RatingForAccount);
        //-- validation code for: _AA_HZP1R_p1RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZP2R_p2RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZP2R_p2RatingForAccount", "AA_HZP2R_p2RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZP2R_p2RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZP2R_p2RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZP2R_p2RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZP2R_p2RatingForAccount);
        //-- validation code for: _AA_HZP2R_p2RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZP3R_p3RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZP3R_p3RatingForAccount", "AA_HZP3R_p3RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZP3R_p3RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZP3R_p3RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZP3R_p3RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZP3R_p3RatingForAccount);
        //-- validation code for: _AA_HZP3R_p3RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZP4R_p4RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZP4R_p4RatingForAccount", "AA_HZP4R_p4RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZP4R_p4RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZP4R_p4RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZP4R_p4RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZP4R_p4RatingForAccount);
        //-- validation code for: _AA_HZP4R_p4RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZP5R_p5RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZP5R_p5RatingForAccount", "AA_HZP5R_p5RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZP5R_p5RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZP5R_p5RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZP5R_p5RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZP5R_p5RatingForAccount);
        //-- validation code for: _AA_HZP5R_p5RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZC1R_c1RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZC1R_c1RatingForAccount", "AA_HZC1R_c1RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZC1R_c1RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZC1R_c1RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZC1R_c1RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZC1R_c1RatingForAccount);
        //-- validation code for: _AA_HZC1R_c1RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZC2R_c2RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZC2R_c2RatingForAccount", "AA_HZC2R_c2RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZC2R_c2RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZC2R_c2RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZC2R_c2RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZC2R_c2RatingForAccount);
        //-- validation code for: _AA_HZC2R_c2RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZC3R_c3RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZC3R_c3RatingForAccount", "AA_HZC3R_c3RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZC3R_c3RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZC3R_c3RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZC3R_c3RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZC3R_c3RatingForAccount);
        //-- validation code for: _AA_HZC3R_c3RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZC4R_c4RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZC4R_c4RatingForAccount", "AA_HZC4R_c4RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZC4R_c4RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZC4R_c4RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZC4R_c4RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZC4R_c4RatingForAccount);
        //-- validation code for: _AA_HZC4R_c4RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZC5R_c5RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZC5R_c5RatingForAccount", "AA_HZC5R_c5RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZC5R_c5RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZC5R_c5RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZC5R_c5RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZC5R_c5RatingForAccount);
        //-- validation code for: _AA_HZC5R_c5RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZACO_accountOfficer
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZACO_accountOfficer", "AA_HZACO_accountOfficer", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZACO_accountOfficer();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZACO_accountOfficer( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZACO_accountOfficer = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZACO_accountOfficer);
        //-- validation code for: _AA_HZACO_accountOfficer
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZLNM_languageCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZLNM_languageCode", "AA_HZLNM_languageCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZLNM_languageCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZLNM_languageCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZLNM_languageCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZLNM_languageCode);
        //-- validation code for: _AA_HZLNM_languageCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAI33_printChargesStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAI33_printChargesStatement", "AA_HZAI33_printChargesStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAI33_printChargesStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAI33_printChargesStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAI33_printChargesStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAI33_printChargesStatement);
        //-- validation code for: _AA_HZAI33_printChargesStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCSFC_chargesStatementFrequency
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCSFC_chargesStatementFrequency", "AA_HZCSFC_chargesStatementFrequency", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCSFC_chargesStatementFrequency();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCSFC_chargesStatementFrequency( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCSFC_chargesStatementFrequency = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCSFC_chargesStatementFrequency);
        //-- validation code for: _AA_HZCSFC_chargesStatementFrequency
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCSTL_lastChargesStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCSTL_lastChargesStatementDate", "AA_HZCSTL_lastChargesStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCSTL_lastChargesStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCSTL_lastChargesStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCSTL_lastChargesStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCSTL_lastChargesStatementDate);
        //-- validation code for: _AA_HZCSTL_lastChargesStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCSTN_nextChargesStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCSTN_nextChargesStatementDate", "AA_HZCSTN_nextChargesStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCSTN_nextChargesStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCSTN_nextChargesStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCSTN_nextChargesStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCSTN_nextChargesStatementDate);
        //-- validation code for: _AA_HZCSTN_nextChargesStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABND_debitNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABND_debitNominatedAccountBranch", "AA_HZABND_debitNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABND_debitNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABND_debitNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABND_debitNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABND_debitNominatedAccountBranch);
        //-- validation code for: _AA_HZABND_debitNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZANND_debitNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZANND_debitNominatedAccountBasic", "AA_HZANND_debitNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZANND_debitNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZANND_debitNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZANND_debitNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZANND_debitNominatedAccountBasic);
        //-- validation code for: _AA_HZANND_debitNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASND_debitNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASND_debitNominatedAccountSuffix", "AA_HZASND_debitNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASND_debitNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASND_debitNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASND_debitNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASND_debitNominatedAccountSuffix);
        //-- validation code for: _AA_HZASND_debitNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNND_debitNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNND_debitNominatedAccountShortName", "AA_HZSNND_debitNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNND_debitNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNND_debitNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNND_debitNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNND_debitNominatedAccountShortName);
        //-- validation code for: _AA_HZSNND_debitNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNDA_debitNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNDA_debitNominatedAccountArabicShortName", "AA_HZSNDA_debitNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNDA_debitNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNDA_debitNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNDA_debitNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNDA_debitNominatedAccountArabicShortName);
        //-- validation code for: _AA_HZSNDA_debitNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABNC_creditNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABNC_creditNominatedAccountBranch", "AA_HZABNC_creditNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABNC_creditNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABNC_creditNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABNC_creditNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABNC_creditNominatedAccountBranch);
        //-- validation code for: _AA_HZABNC_creditNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZANNC_creditNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZANNC_creditNominatedAccountBasic", "AA_HZANNC_creditNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZANNC_creditNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZANNC_creditNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZANNC_creditNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZANNC_creditNominatedAccountBasic);
        //-- validation code for: _AA_HZANNC_creditNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASNC_creditNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASNC_creditNominatedAccountSuffix", "AA_HZASNC_creditNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASNC_creditNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASNC_creditNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASNC_creditNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASNC_creditNominatedAccountSuffix);
        //-- validation code for: _AA_HZASNC_creditNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNNC_creditNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNNC_creditNominatedAccountShortName", "AA_HZSNNC_creditNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNNC_creditNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNNC_creditNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNNC_creditNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNNC_creditNominatedAccountShortName);
        //-- validation code for: _AA_HZSNNC_creditNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNCA_creditNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNCA_creditNominatedAccountArabicShortName", "AA_HZSNCA_creditNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNCA_creditNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNCA_creditNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNCA_creditNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNCA_creditNominatedAccountArabicShortName);
        //-- validation code for: _AA_HZSNCA_creditNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABNG_chargeNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABNG_chargeNominatedAccountBranch", "AA_HZABNG_chargeNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABNG_chargeNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABNG_chargeNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABNG_chargeNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABNG_chargeNominatedAccountBranch);
        //-- validation code for: _AA_HZABNG_chargeNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZANNG_chargeNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZANNG_chargeNominatedAccountBasic", "AA_HZANNG_chargeNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZANNG_chargeNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZANNG_chargeNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZANNG_chargeNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZANNG_chargeNominatedAccountBasic);
        //-- validation code for: _AA_HZANNG_chargeNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASNG_chargeNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASNG_chargeNominatedAccountSuffix", "AA_HZASNG_chargeNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASNG_chargeNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASNG_chargeNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASNG_chargeNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASNG_chargeNominatedAccountSuffix);
        //-- validation code for: _AA_HZASNG_chargeNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNNG_chargeNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNNG_chargeNominatedAccountShortName", "AA_HZSNNG_chargeNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNNG_chargeNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNNG_chargeNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNNG_chargeNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNNG_chargeNominatedAccountShortName);
        //-- validation code for: _AA_HZSNNG_chargeNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNGA_chargeNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNGA_chargeNominatedAccountArabicShortName", "AA_HZSNGA_chargeNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNGA_chargeNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNGA_chargeNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNGA_chargeNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNGA_chargeNominatedAccountArabicShortName);
        //-- validation code for: _AA_HZSNGA_chargeNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABN_chargeNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABN_chargeNominatedAccountBranch", "AA_HZABN_chargeNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABN_chargeNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABN_chargeNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABN_chargeNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABN_chargeNominatedAccountBranch);
        //-- validation code for: _AA_HZABN_chargeNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZANN_chargeNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZANN_chargeNominatedAccountBasic", "AA_HZANN_chargeNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZANN_chargeNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZANN_chargeNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZANN_chargeNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZANN_chargeNominatedAccountBasic);
        //-- validation code for: _AA_HZANN_chargeNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASN_chargeNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASN_chargeNominatedAccountSuffix", "AA_HZASN_chargeNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASN_chargeNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASN_chargeNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASN_chargeNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASN_chargeNominatedAccountSuffix);
        //-- validation code for: _AA_HZASN_chargeNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNN_chargeNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNN_chargeNominatedAccountShortName", "AA_HZSNN_chargeNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNN_chargeNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNN_chargeNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNN_chargeNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNN_chargeNominatedAccountShortName);
        //-- validation code for: _AA_HZSNN_chargeNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNA_chargeNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNA_chargeNominatedAccountArabicShortName", "AA_HZSNA_chargeNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNA_chargeNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNA_chargeNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNA_chargeNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNA_chargeNominatedAccountArabicShortName);
        //-- validation code for: _AA_HZSNA_chargeNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABM_masterInterestAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABM_masterInterestAccountBranch", "AA_HZABM_masterInterestAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABM_masterInterestAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABM_masterInterestAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABM_masterInterestAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABM_masterInterestAccountBranch);
        //-- validation code for: _AA_HZABM_masterInterestAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZANM_masterInterestAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZANM_masterInterestAccountBasic", "AA_HZANM_masterInterestAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZANM_masterInterestAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZANM_masterInterestAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZANM_masterInterestAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZANM_masterInterestAccountBasic);
        //-- validation code for: _AA_HZANM_masterInterestAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASM_masterInterestAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASM_masterInterestAccountSuffix", "AA_HZASM_masterInterestAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASM_masterInterestAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASM_masterInterestAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASM_masterInterestAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASM_masterInterestAccountSuffix);
        //-- validation code for: _AA_HZASM_masterInterestAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNM_masterInterestAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNM_masterInterestAccountShortName", "AA_HZSNM_masterInterestAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNM_masterInterestAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNM_masterInterestAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNM_masterInterestAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNM_masterInterestAccountShortName);
        //-- validation code for: _AA_HZSNM_masterInterestAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSNMA_masterInterestAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSNMA_masterInterestAccountArabicShortName", "AA_HZSNMA_masterInterestAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSNMA_masterInterestAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSNMA_masterInterestAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSNMA_masterInterestAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSNMA_masterInterestAccountArabicShortName);
        //-- validation code for: _AA_HZSNMA_masterInterestAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAIC7_jointAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAIC7_jointAccount", "AA_HZAIC7_jointAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAIC7_jointAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAIC7_jointAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAIC7_jointAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAIC7_jointAccount);
        //-- validation code for: _AA_HZAIC7_jointAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZYFON_fontisAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZYFON_fontisAccount", "AA_HZYFON_fontisAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZYFON_fontisAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZYFON_fontisAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZYFON_fontisAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZYFON_fontisAccount);
        //-- validation code for: _AA_HZYFON_fontisAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZDFRQ_fontisDownloadFrequency
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZDFRQ_fontisDownloadFrequency", "AA_HZDFRQ_fontisDownloadFrequency", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZDFRQ_fontisDownloadFrequency();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZDFRQ_fontisDownloadFrequency( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZDFRQ_fontisDownloadFrequency = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZDFRQ_fontisDownloadFrequency);
        //-- validation code for: _AA_HZDFRQ_fontisDownloadFrequency
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSN20_externalAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSN20_externalAccountNumber", "AA_HZSN20_externalAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSN20_externalAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSN20_externalAccountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSN20_externalAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSN20_externalAccountNumber);
        //-- validation code for: _AA_HZSN20_externalAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(20);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCAN_accountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCAN_accountNumber", "AA_HZCAN_accountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCAN_accountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCAN_accountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCAN_accountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCAN_accountNumber);
        //-- validation code for: _AA_HZCAN_accountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZUNMW_unmaturedWithdrawal
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZUNMW_unmaturedWithdrawal", "AA_HZUNMW_unmaturedWithdrawal", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZUNMW_unmaturedWithdrawal();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZUNMW_unmaturedWithdrawal( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZUNMW_unmaturedWithdrawal = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZUNMW_unmaturedWithdrawal);
        //-- validation code for: _AA_HZUNMW_unmaturedWithdrawal
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAII7_noticeAC
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAII7_noticeAC", "AA_HZAII7_noticeAC", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAII7_noticeAC();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAII7_noticeAC( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAII7_noticeAC = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAII7_noticeAC);
        //-- validation code for: _AA_HZAII7_noticeAC
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZCLS_closingWithdrawal
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZCLS_closingWithdrawal", "AA_HZCLS_closingWithdrawal", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZCLS_closingWithdrawal();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZCLS_closingWithdrawal( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZCLS_closingWithdrawal = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZCLS_closingWithdrawal);
        //-- validation code for: _AA_HZCLS_closingWithdrawal
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZPAB_sourceOriginalBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZPAB_sourceOriginalBranch", "AA_HZPAB_sourceOriginalBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZPAB_sourceOriginalBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZPAB_sourceOriginalBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZPAB_sourceOriginalBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZPAB_sourceOriginalBranch);
        //-- validation code for: _AA_HZPAB_sourceOriginalBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZPCHD_branchChangeDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZPCHD_branchChangeDate", "AA_HZPCHD_branchChangeDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZPCHD_branchChangeDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZPCHD_branchChangeDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZPCHD_branchChangeDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZPCHD_branchChangeDate);
        //-- validation code for: _AA_HZPCHD_branchChangeDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZABD_previousDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZABD_previousDescription", "AA_HZABD_previousDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZABD_previousDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZABD_previousDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZABD_previousDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZABD_previousDescription);
        //-- validation code for: _AA_HZABD_previousDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZDFRM_fontisDownloadFrequencyMeridian
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZDFRM_fontisDownloadFrequencyMeridian", "AA_HZDFRM_fontisDownloadFrequencyMeridian", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZDFRM_fontisDownloadFrequencyMeridian();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZDFRM_fontisDownloadFrequencyMeridian( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZDFRM_fontisDownloadFrequencyMeridian = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZDFRM_fontisDownloadFrequencyMeridian);
        //-- validation code for: _AA_HZDFRM_fontisDownloadFrequencyMeridian
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZTRM_transferMethod
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZTRM_transferMethod", "AA_HZTRM_transferMethod", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZTRM_transferMethod();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZTRM_transferMethod( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZTRM_transferMethod = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZTRM_transferMethod);
        //-- validation code for: _AA_HZTRM_transferMethod
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZXMD_transferMethodDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZXMD_transferMethodDescription", "AA_HZXMD_transferMethodDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZXMD_transferMethodDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZXMD_transferMethodDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZXMD_transferMethodDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZXMD_transferMethodDescription);
        //-- validation code for: _AA_HZXMD_transferMethodDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(25);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZHLD_held
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZHLD_held", "AA_HZHLD_held", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZHLD_held();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZHLD_held( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZHLD_held = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZHLD_held);
        //-- validation code for: _AA_HZHLD_held
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAUT_authorised
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAUT_authorised", "AA_HZAUT_authorised", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAUT_authorised();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAUT_authorised( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAUT_authorised = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAUT_authorised);
        //-- validation code for: _AA_HZAUT_authorised
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZSTA_status
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZSTA_status", "AA_HZSTA_status", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZSTA_status();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZSTA_status( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZSTA_status = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZSTA_status);
        //-- validation code for: _AA_HZSTA_status
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(25);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZDLMZ_instructionDateLastMaintained
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZDLMZ_instructionDateLastMaintained", "AA_HZDLMZ_instructionDateLastMaintained", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZDLMZ_instructionDateLastMaintained();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZDLMZ_instructionDateLastMaintained( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZDLMZ_instructionDateLastMaintained = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZDLMZ_instructionDateLastMaintained);
        //-- validation code for: _AA_HZDLMZ_instructionDateLastMaintained
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZREF_lastCpReference
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZREF_lastCpReference", "AA_HZREF_lastCpReference", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZREF_lastCpReference();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZREF_lastCpReference( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZREF_lastCpReference = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZREF_lastCpReference);
        //-- validation code for: _AA_HZREF_lastCpReference
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZASIP_aCSettlInstPresent
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZASIP_aCSettlInstPresent", "AA_HZASIP_aCSettlInstPresent", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZASIP_aCSettlInstPresent();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZASIP_aCSettlInstPresent( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZASIP_aCSettlInstPresent = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZASIP_aCSettlInstPresent);
        //-- validation code for: _AA_HZASIP_aCSettlInstPresent
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZREFA_settlementReference
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZREFA_settlementReference", "AA_HZREFA_settlementReference", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZREFA_settlementReference();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZREFA_settlementReference( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZREFA_settlementReference = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZREFA_settlementReference);
        //-- validation code for: _AA_HZREFA_settlementReference
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZTADT_taxAdviceType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZTADT_taxAdviceType", "AA_HZTADT_taxAdviceType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZTADT_taxAdviceType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZTADT_taxAdviceType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZTADT_taxAdviceType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZTADT_taxAdviceType);
        //-- validation code for: _AA_HZTADT_taxAdviceType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZNTAD_taxAdviceTypeNextYear
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZNTAD_taxAdviceTypeNextYear", "AA_HZNTAD_taxAdviceTypeNextYear", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZNTAD_taxAdviceTypeNextYear();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZNTAD_taxAdviceTypeNextYear( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZNTAD_taxAdviceTypeNextYear = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZNTAD_taxAdviceTypeNextYear);
        //-- validation code for: _AA_HZNTAD_taxAdviceTypeNextYear
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAUNO_lastAuditLetterNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAUNO_lastAuditLetterNumber", "AA_HZAUNO_lastAuditLetterNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAUNO_lastAuditLetterNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAUNO_lastAuditLetterNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAUNO_lastAuditLetterNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAUNO_lastAuditLetterNumber);
        //-- validation code for: _AA_HZAUNO_lastAuditLetterNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _AA_HZAUDT_lastAuditLetterDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_AA_HZAUDT_lastAuditLetterDate", "AA_HZAUDT_lastAuditLetterDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                XAA target = (XAA) object;
                return target.getAA_HZAUDT_lastAuditLetterDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    XAA target = (XAA) object;
                    target.setAA_HZAUDT_lastAuditLetterDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_AA_HZAUDT_lastAuditLetterDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_AA_HZAUDT_lastAuditLetterDate);
        //-- validation code for: _AA_HZAUDT_lastAuditLetterDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
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
        return bf.com.misys.eq4.service.xaa.XAA.class;
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
