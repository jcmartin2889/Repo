/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: X76Descriptor.java 7094 2010-04-28 10:58:02Z goldsmc1 $
 */

package bf.com.misys.eq4.service.x76.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import bf.com.misys.eq4.service.x76.X76;

/**
 * Class X76Descriptor.
 * 
 * @version $Revision$ $Date$
 */
public class X76Descriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


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

    public X76Descriptor() {
        super();
        _nsURI = "http://www.misys.com/eq4/service/x76";
        _xmlName = "X76";
        _elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _HCI_HRC_holdCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HCI_HRC_holdCode", "HCI_HRC_holdCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getHCI_HRC_holdCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setHCI_HRC_holdCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HCI_HRC_holdCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HCI_HRC_holdCode);
        //-- validation code for: _HCI_HRC_holdCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _HCI_HRD_holdDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HCI_HRD_holdDescription", "HCI_HRD_holdDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getHCI_HRD_holdDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setHCI_HRD_holdDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HCI_HRD_holdDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HCI_HRD_holdDescription);
        //-- validation code for: _HCI_HRD_holdDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _HCI_DED_defaultExpiryDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_HCI_DED_defaultExpiryDate", "HCI_DED_defaultExpiryDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getHCI_DED_defaultExpiryDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setHCI_DED_defaultExpiryDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_HCI_DED_defaultExpiryDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_HCI_DED_defaultExpiryDate);
        //-- validation code for: _HCI_DED_defaultExpiryDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAB_accountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAB_accountBranch", "ABE_HZAB_accountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAB_accountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAB_accountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAB_accountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAB_accountBranch);
        //-- validation code for: _ABE_HZAB_accountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAN_basicPartOfAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAN_basicPartOfAccountNumber", "ABE_HZAN_basicPartOfAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAN_basicPartOfAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAN_basicPartOfAccountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAN_basicPartOfAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAN_basicPartOfAccountNumber);
        //-- validation code for: _ABE_HZAN_basicPartOfAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAS_accountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAS_accountSuffix", "ABE_HZAS_accountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAS_accountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAS_accountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAS_accountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAS_accountSuffix);
        //-- validation code for: _ABE_HZAS_accountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSHN_accountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSHN_accountShortName", "ABE_HZSHN_accountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSHN_accountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSHN_accountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSHN_accountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSHN_accountShortName);
        //-- validation code for: _ABE_HZSHN_accountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSHNA_arabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSHNA_arabicShortName", "ABE_HZSHNA_arabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSHNA_arabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSHNA_arabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSHNA_arabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSHNA_arabicShortName);
        //-- validation code for: _ABE_HZSHNA_arabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCUS_customerMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCUS_customerMnemonic", "ABE_HZCUS_customerMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCUS_customerMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCUS_customerMnemonic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCUS_customerMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCUS_customerMnemonic);
        //-- validation code for: _ABE_HZCUS_customerMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCLC_customerLocation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCLC_customerLocation", "ABE_HZCLC_customerLocation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCLC_customerLocation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCLC_customerLocation( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCLC_customerLocation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCLC_customerLocation);
        //-- validation code for: _ABE_HZCLC_customerLocation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCUN_customerFullName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCUN_customerFullName", "ABE_HZCUN_customerFullName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCUN_customerFullName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCUN_customerFullName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCUN_customerFullName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCUN_customerFullName);
        //-- validation code for: _ABE_HZCUN_customerFullName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCUNA_arabicCustomerFullName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCUNA_arabicCustomerFullName", "ABE_HZCUNA_arabicCustomerFullName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCUNA_arabicCustomerFullName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCUNA_arabicCustomerFullName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCUNA_arabicCustomerFullName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCUNA_arabicCustomerFullName);
        //-- validation code for: _ABE_HZCUNA_arabicCustomerFullName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZACT_accountType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZACT_accountType", "ABE_HZACT_accountType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZACT_accountType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZACT_accountType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZACT_accountType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZACT_accountType);
        //-- validation code for: _ABE_HZACT_accountType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAPP_owningApplicationCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAPP_owningApplicationCode", "ABE_HZAPP_owningApplicationCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAPP_owningApplicationCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAPP_owningApplicationCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAPP_owningApplicationCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAPP_owningApplicationCode);
        //-- validation code for: _ABE_HZAPP_owningApplicationCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZACS_accountStructure
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZACS_accountStructure", "ABE_HZACS_accountStructure", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZACS_accountStructure();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZACS_accountStructure( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZACS_accountStructure = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZACS_accountStructure);
        //-- validation code for: _ABE_HZACS_accountStructure
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCCY_currencyMnemonic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCCY_currencyMnemonic", "ABE_HZCCY_currencyMnemonic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCCY_currencyMnemonic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCCY_currencyMnemonic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCCY_currencyMnemonic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCCY_currencyMnemonic);
        //-- validation code for: _ABE_HZCCY_currencyMnemonic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCED_currencyEditField
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCED_currencyEditField", "ABE_HZCED_currencyEditField", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCED_currencyEditField();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCED_currencyEditField( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCED_currencyEditField = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCED_currencyEditField);
        //-- validation code for: _ABE_HZCED_currencyEditField
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI47_internalNonCustomerAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI47_internalNonCustomerAccount", "ABE_HZAI47_internalNonCustomerAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI47_internalNonCustomerAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI47_internalNonCustomerAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI47_internalNonCustomerAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI47_internalNonCustomerAccount);
        //-- validation code for: _ABE_HZAI47_internalNonCustomerAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI14_deceasedOrLiquidated
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI14_deceasedOrLiquidated", "ABE_HZAI14_deceasedOrLiquidated", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI14_deceasedOrLiquidated();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI14_deceasedOrLiquidated( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI14_deceasedOrLiquidated = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI14_deceasedOrLiquidated);
        //-- validation code for: _ABE_HZAI14_deceasedOrLiquidated
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI17_accountStatusBlocked
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI17_accountStatusBlocked", "ABE_HZAI17_accountStatusBlocked", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI17_accountStatusBlocked();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI17_accountStatusBlocked( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI17_accountStatusBlocked = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI17_accountStatusBlocked);
        //-- validation code for: _ABE_HZAI17_accountStatusBlocked
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI20_accountStatusInactive
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI20_accountStatusInactive", "ABE_HZAI20_accountStatusInactive", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI20_accountStatusInactive();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI20_accountStatusInactive( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI20_accountStatusInactive = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI20_accountStatusInactive);
        //-- validation code for: _ABE_HZAI20_accountStatusInactive
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI30_accountClosing
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI30_accountClosing", "ABE_HZAI30_accountClosing", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI30_accountClosing();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI30_accountClosing( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI30_accountClosing = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI30_accountClosing);
        //-- validation code for: _ABE_HZAI30_accountClosing
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay", "ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay);
        //-- validation code for: _ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCTP_customerType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCTP_customerType", "ABE_HZCTP_customerType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCTP_customerType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCTP_customerType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCTP_customerType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCTP_customerType);
        //-- validation code for: _ABE_HZCTP_customerType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZACD_analysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZACD_analysisCode", "ABE_HZACD_analysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZACD_analysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZACD_analysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZACD_analysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZACD_analysisCode);
        //-- validation code for: _ABE_HZACD_analysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSAC_sundryAnalysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSAC_sundryAnalysisCode", "ABE_HZSAC_sundryAnalysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSAC_sundryAnalysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSAC_sundryAnalysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSAC_sundryAnalysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSAC_sundryAnalysisCode);
        //-- validation code for: _ABE_HZSAC_sundryAnalysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZNANC_numericAnalysisCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZNANC_numericAnalysisCode", "ABE_HZNANC_numericAnalysisCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZNANC_numericAnalysisCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZNANC_numericAnalysisCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZNANC_numericAnalysisCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZNANC_numericAnalysisCode);
        //-- validation code for: _ABE_HZNANC_numericAnalysisCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(5);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCNAL_residenceCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCNAL_residenceCountry", "ABE_HZCNAL_residenceCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCNAL_residenceCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCNAL_residenceCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCNAL_residenceCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCNAL_residenceCountry);
        //-- validation code for: _ABE_HZCNAL_residenceCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCNAR_riskCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCNAR_riskCountry", "ABE_HZCNAR_riskCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCNAR_riskCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCNAR_riskCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCNAR_riskCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCNAR_riskCountry);
        //-- validation code for: _ABE_HZCNAR_riskCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCNAP_parentCountry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCNAP_parentCountry", "ABE_HZCNAP_parentCountry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCNAP_parentCountry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCNAP_parentCountry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCNAP_parentCountry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCNAP_parentCountry);
        //-- validation code for: _ABE_HZCNAP_parentCountry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSFC_statementFrequencyCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSFC_statementFrequencyCode", "ABE_HZSFC_statementFrequencyCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSFC_statementFrequencyCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSFC_statementFrequencyCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSFC_statementFrequencyCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSFC_statementFrequencyCode);
        //-- validation code for: _ABE_HZSFC_statementFrequencyCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZBALS_balanceOnThePreviousStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZBALS_balanceOnThePreviousStatement", "ABE_HZBALS_balanceOnThePreviousStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZBALS_balanceOnThePreviousStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZBALS_balanceOnThePreviousStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZBALS_balanceOnThePreviousStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZBALS_balanceOnThePreviousStatement);
        //-- validation code for: _ABE_HZBALS_balanceOnThePreviousStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement", "ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement);
        //-- validation code for: _ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSTML_lastStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSTML_lastStatementDate", "ABE_HZSTML_lastStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSTML_lastStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSTML_lastStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSTML_lastStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSTML_lastStatementDate);
        //-- validation code for: _ABE_HZSTML_lastStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSTMN_nextStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSTMN_nextStatementDate", "ABE_HZSTMN_nextStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSTMN_nextStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSTMN_nextStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSTMN_nextStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSTMN_nextStatementDate);
        //-- validation code for: _ABE_HZSTMN_nextStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSTNL_lastStatementNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSTNL_lastStatementNumber", "ABE_HZSTNL_lastStatementNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSTNL_lastStatementNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSTNL_lastStatementNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSTNL_lastStatementNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSTNL_lastStatementNumber);
        //-- validation code for: _ABE_HZSTNL_lastStatementNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZRETP_retentionPeriod00None011MthSysDflt
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZRETP_retentionPeriod00None011MthSysDflt", "ABE_HZRETP_retentionPeriod00None011MthSysDflt", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZRETP_retentionPeriod00None011MthSysDflt();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZRETP_retentionPeriod00None011MthSysDflt( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZRETP_retentionPeriod00None011MthSysDflt = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZRETP_retentionPeriod00None011MthSysDflt);
        //-- validation code for: _ABE_HZRETP_retentionPeriod00None011MthSysDflt
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZNS3_numberOfReferenceCharactersForReconciliation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZNS3_numberOfReferenceCharactersForReconciliation", "ABE_HZNS3_numberOfReferenceCharactersForReconciliation", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZNS3_numberOfReferenceCharactersForReconciliation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZNS3_numberOfReferenceCharactersForReconciliation( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZNS3_numberOfReferenceCharactersForReconciliation = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZNS3_numberOfReferenceCharactersForReconciliation);
        //-- validation code for: _ABE_HZNS3_numberOfReferenceCharactersForReconciliation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZOAD_dateAccountOpened
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZOAD_dateAccountOpened", "ABE_HZOAD_dateAccountOpened", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZOAD_dateAccountOpened();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZOAD_dateAccountOpened( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZOAD_dateAccountOpened = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZOAD_dateAccountOpened);
        //-- validation code for: _ABE_HZOAD_dateAccountOpened
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZDLE_dateOfLastCustomerGeneratedEntry
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZDLE_dateOfLastCustomerGeneratedEntry", "ABE_HZDLE_dateOfLastCustomerGeneratedEntry", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZDLE_dateOfLastCustomerGeneratedEntry();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZDLE_dateOfLastCustomerGeneratedEntry( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZDLE_dateOfLastCustomerGeneratedEntry = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZDLE_dateOfLastCustomerGeneratedEntry);
        //-- validation code for: _ABE_HZDLE_dateOfLastCustomerGeneratedEntry
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCAD_dateAccountClosed
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCAD_dateAccountClosed", "ABE_HZCAD_dateAccountClosed", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCAD_dateAccountClosed();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCAD_dateAccountClosed( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCAD_dateAccountClosed = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCAD_dateAccountClosed);
        //-- validation code for: _ABE_HZCAD_dateAccountClosed
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZDLM_dateLastMaintained
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZDLM_dateLastMaintained", "ABE_HZDLM_dateLastMaintained", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZDLM_dateLastMaintained();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZDLM_dateLastMaintained( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZDLM_dateLastMaintained = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZDLM_dateLastMaintained);
        //-- validation code for: _ABE_HZDLM_dateLastMaintained
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZP1R_p1RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZP1R_p1RatingForAccount", "ABE_HZP1R_p1RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZP1R_p1RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZP1R_p1RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZP1R_p1RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZP1R_p1RatingForAccount);
        //-- validation code for: _ABE_HZP1R_p1RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZP2R_p2RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZP2R_p2RatingForAccount", "ABE_HZP2R_p2RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZP2R_p2RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZP2R_p2RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZP2R_p2RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZP2R_p2RatingForAccount);
        //-- validation code for: _ABE_HZP2R_p2RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZP3R_p3RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZP3R_p3RatingForAccount", "ABE_HZP3R_p3RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZP3R_p3RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZP3R_p3RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZP3R_p3RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZP3R_p3RatingForAccount);
        //-- validation code for: _ABE_HZP3R_p3RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZP4R_p4RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZP4R_p4RatingForAccount", "ABE_HZP4R_p4RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZP4R_p4RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZP4R_p4RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZP4R_p4RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZP4R_p4RatingForAccount);
        //-- validation code for: _ABE_HZP4R_p4RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZP5R_p5RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZP5R_p5RatingForAccount", "ABE_HZP5R_p5RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZP5R_p5RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZP5R_p5RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZP5R_p5RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZP5R_p5RatingForAccount);
        //-- validation code for: _ABE_HZP5R_p5RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZC1R_c1RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZC1R_c1RatingForAccount", "ABE_HZC1R_c1RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZC1R_c1RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZC1R_c1RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZC1R_c1RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZC1R_c1RatingForAccount);
        //-- validation code for: _ABE_HZC1R_c1RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZC2R_c2RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZC2R_c2RatingForAccount", "ABE_HZC2R_c2RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZC2R_c2RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZC2R_c2RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZC2R_c2RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZC2R_c2RatingForAccount);
        //-- validation code for: _ABE_HZC2R_c2RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZC3R_c3RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZC3R_c3RatingForAccount", "ABE_HZC3R_c3RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZC3R_c3RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZC3R_c3RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZC3R_c3RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZC3R_c3RatingForAccount);
        //-- validation code for: _ABE_HZC3R_c3RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZC4R_c4RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZC4R_c4RatingForAccount", "ABE_HZC4R_c4RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZC4R_c4RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZC4R_c4RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZC4R_c4RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZC4R_c4RatingForAccount);
        //-- validation code for: _ABE_HZC4R_c4RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZC5R_c5RatingForAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZC5R_c5RatingForAccount", "ABE_HZC5R_c5RatingForAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZC5R_c5RatingForAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZC5R_c5RatingForAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZC5R_c5RatingForAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZC5R_c5RatingForAccount);
        //-- validation code for: _ABE_HZC5R_c5RatingForAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZACO_accountOfficer
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZACO_accountOfficer", "ABE_HZACO_accountOfficer", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZACO_accountOfficer();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZACO_accountOfficer( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZACO_accountOfficer = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZACO_accountOfficer);
        //-- validation code for: _ABE_HZACO_accountOfficer
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZLNM_languageCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZLNM_languageCode", "ABE_HZLNM_languageCode", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZLNM_languageCode();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZLNM_languageCode( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZLNM_languageCode = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZLNM_languageCode);
        //-- validation code for: _ABE_HZLNM_languageCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAI33_printChargesStatement
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAI33_printChargesStatement", "ABE_HZAI33_printChargesStatement", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAI33_printChargesStatement();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAI33_printChargesStatement( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAI33_printChargesStatement = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAI33_printChargesStatement);
        //-- validation code for: _ABE_HZAI33_printChargesStatement
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCSFC_chargesStatementFrequency
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCSFC_chargesStatementFrequency", "ABE_HZCSFC_chargesStatementFrequency", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCSFC_chargesStatementFrequency();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCSFC_chargesStatementFrequency( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCSFC_chargesStatementFrequency = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCSFC_chargesStatementFrequency);
        //-- validation code for: _ABE_HZCSFC_chargesStatementFrequency
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCSTL_lastChargesStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCSTL_lastChargesStatementDate", "ABE_HZCSTL_lastChargesStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCSTL_lastChargesStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCSTL_lastChargesStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCSTL_lastChargesStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCSTL_lastChargesStatementDate);
        //-- validation code for: _ABE_HZCSTL_lastChargesStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCSTN_nextChargesStatementDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCSTN_nextChargesStatementDate", "ABE_HZCSTN_nextChargesStatementDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCSTN_nextChargesStatementDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCSTN_nextChargesStatementDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCSTN_nextChargesStatementDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCSTN_nextChargesStatementDate);
        //-- validation code for: _ABE_HZCSTN_nextChargesStatementDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABND_debitNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABND_debitNominatedAccountBranch", "ABE_HZABND_debitNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABND_debitNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABND_debitNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABND_debitNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABND_debitNominatedAccountBranch);
        //-- validation code for: _ABE_HZABND_debitNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZANND_debitNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZANND_debitNominatedAccountBasic", "ABE_HZANND_debitNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZANND_debitNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZANND_debitNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZANND_debitNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZANND_debitNominatedAccountBasic);
        //-- validation code for: _ABE_HZANND_debitNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASND_debitNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASND_debitNominatedAccountSuffix", "ABE_HZASND_debitNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASND_debitNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASND_debitNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASND_debitNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASND_debitNominatedAccountSuffix);
        //-- validation code for: _ABE_HZASND_debitNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNND_debitNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNND_debitNominatedAccountShortName", "ABE_HZSNND_debitNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNND_debitNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNND_debitNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNND_debitNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNND_debitNominatedAccountShortName);
        //-- validation code for: _ABE_HZSNND_debitNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNDA_debitNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNDA_debitNominatedAccountArabicShortName", "ABE_HZSNDA_debitNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNDA_debitNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNDA_debitNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNDA_debitNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNDA_debitNominatedAccountArabicShortName);
        //-- validation code for: _ABE_HZSNDA_debitNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABNC_creditNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABNC_creditNominatedAccountBranch", "ABE_HZABNC_creditNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABNC_creditNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABNC_creditNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABNC_creditNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABNC_creditNominatedAccountBranch);
        //-- validation code for: _ABE_HZABNC_creditNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZANNC_creditNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZANNC_creditNominatedAccountBasic", "ABE_HZANNC_creditNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZANNC_creditNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZANNC_creditNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZANNC_creditNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZANNC_creditNominatedAccountBasic);
        //-- validation code for: _ABE_HZANNC_creditNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASNC_creditNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASNC_creditNominatedAccountSuffix", "ABE_HZASNC_creditNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASNC_creditNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASNC_creditNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASNC_creditNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASNC_creditNominatedAccountSuffix);
        //-- validation code for: _ABE_HZASNC_creditNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNNC_creditNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNNC_creditNominatedAccountShortName", "ABE_HZSNNC_creditNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNNC_creditNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNNC_creditNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNNC_creditNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNNC_creditNominatedAccountShortName);
        //-- validation code for: _ABE_HZSNNC_creditNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNCA_creditNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNCA_creditNominatedAccountArabicShortName", "ABE_HZSNCA_creditNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNCA_creditNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNCA_creditNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNCA_creditNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNCA_creditNominatedAccountArabicShortName);
        //-- validation code for: _ABE_HZSNCA_creditNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABNG_chargeNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABNG_chargeNominatedAccountBranch", "ABE_HZABNG_chargeNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABNG_chargeNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABNG_chargeNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABNG_chargeNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABNG_chargeNominatedAccountBranch);
        //-- validation code for: _ABE_HZABNG_chargeNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZANNG_chargeNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZANNG_chargeNominatedAccountBasic", "ABE_HZANNG_chargeNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZANNG_chargeNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZANNG_chargeNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZANNG_chargeNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZANNG_chargeNominatedAccountBasic);
        //-- validation code for: _ABE_HZANNG_chargeNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASNG_chargeNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASNG_chargeNominatedAccountSuffix", "ABE_HZASNG_chargeNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASNG_chargeNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASNG_chargeNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASNG_chargeNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASNG_chargeNominatedAccountSuffix);
        //-- validation code for: _ABE_HZASNG_chargeNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNNG_chargeNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNNG_chargeNominatedAccountShortName", "ABE_HZSNNG_chargeNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNNG_chargeNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNNG_chargeNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNNG_chargeNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNNG_chargeNominatedAccountShortName);
        //-- validation code for: _ABE_HZSNNG_chargeNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNGA_chargeNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNGA_chargeNominatedAccountArabicShortName", "ABE_HZSNGA_chargeNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNGA_chargeNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNGA_chargeNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNGA_chargeNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNGA_chargeNominatedAccountArabicShortName);
        //-- validation code for: _ABE_HZSNGA_chargeNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABN_chargeNominatedAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABN_chargeNominatedAccountBranch", "ABE_HZABN_chargeNominatedAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABN_chargeNominatedAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABN_chargeNominatedAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABN_chargeNominatedAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABN_chargeNominatedAccountBranch);
        //-- validation code for: _ABE_HZABN_chargeNominatedAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZANN_chargeNominatedAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZANN_chargeNominatedAccountBasic", "ABE_HZANN_chargeNominatedAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZANN_chargeNominatedAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZANN_chargeNominatedAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZANN_chargeNominatedAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZANN_chargeNominatedAccountBasic);
        //-- validation code for: _ABE_HZANN_chargeNominatedAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASN_chargeNominatedAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASN_chargeNominatedAccountSuffix", "ABE_HZASN_chargeNominatedAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASN_chargeNominatedAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASN_chargeNominatedAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASN_chargeNominatedAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASN_chargeNominatedAccountSuffix);
        //-- validation code for: _ABE_HZASN_chargeNominatedAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNN_chargeNominatedAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNN_chargeNominatedAccountShortName", "ABE_HZSNN_chargeNominatedAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNN_chargeNominatedAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNN_chargeNominatedAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNN_chargeNominatedAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNN_chargeNominatedAccountShortName);
        //-- validation code for: _ABE_HZSNN_chargeNominatedAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNA_chargeNominatedAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNA_chargeNominatedAccountArabicShortName", "ABE_HZSNA_chargeNominatedAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNA_chargeNominatedAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNA_chargeNominatedAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNA_chargeNominatedAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNA_chargeNominatedAccountArabicShortName);
        //-- validation code for: _ABE_HZSNA_chargeNominatedAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABM_masterInterestAccountBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABM_masterInterestAccountBranch", "ABE_HZABM_masterInterestAccountBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABM_masterInterestAccountBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABM_masterInterestAccountBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABM_masterInterestAccountBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABM_masterInterestAccountBranch);
        //-- validation code for: _ABE_HZABM_masterInterestAccountBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZANM_masterInterestAccountBasic
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZANM_masterInterestAccountBasic", "ABE_HZANM_masterInterestAccountBasic", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZANM_masterInterestAccountBasic();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZANM_masterInterestAccountBasic( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZANM_masterInterestAccountBasic = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZANM_masterInterestAccountBasic);
        //-- validation code for: _ABE_HZANM_masterInterestAccountBasic
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(6);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASM_masterInterestAccountSuffix
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASM_masterInterestAccountSuffix", "ABE_HZASM_masterInterestAccountSuffix", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASM_masterInterestAccountSuffix();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASM_masterInterestAccountSuffix( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASM_masterInterestAccountSuffix = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASM_masterInterestAccountSuffix);
        //-- validation code for: _ABE_HZASM_masterInterestAccountSuffix
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNM_masterInterestAccountShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNM_masterInterestAccountShortName", "ABE_HZSNM_masterInterestAccountShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNM_masterInterestAccountShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNM_masterInterestAccountShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNM_masterInterestAccountShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNM_masterInterestAccountShortName);
        //-- validation code for: _ABE_HZSNM_masterInterestAccountShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSNMA_masterInterestAccountArabicShortName
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSNMA_masterInterestAccountArabicShortName", "ABE_HZSNMA_masterInterestAccountArabicShortName", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSNMA_masterInterestAccountArabicShortName();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSNMA_masterInterestAccountArabicShortName( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSNMA_masterInterestAccountArabicShortName = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSNMA_masterInterestAccountArabicShortName);
        //-- validation code for: _ABE_HZSNMA_masterInterestAccountArabicShortName
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAIC7_jointAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAIC7_jointAccount", "ABE_HZAIC7_jointAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAIC7_jointAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAIC7_jointAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAIC7_jointAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAIC7_jointAccount);
        //-- validation code for: _ABE_HZAIC7_jointAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZYFON_fontisAccount
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZYFON_fontisAccount", "ABE_HZYFON_fontisAccount", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZYFON_fontisAccount();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZYFON_fontisAccount( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZYFON_fontisAccount = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZYFON_fontisAccount);
        //-- validation code for: _ABE_HZYFON_fontisAccount
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZDFRQ_fontisDownloadFrequency
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZDFRQ_fontisDownloadFrequency", "ABE_HZDFRQ_fontisDownloadFrequency", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZDFRQ_fontisDownloadFrequency();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZDFRQ_fontisDownloadFrequency( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZDFRQ_fontisDownloadFrequency = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZDFRQ_fontisDownloadFrequency);
        //-- validation code for: _ABE_HZDFRQ_fontisDownloadFrequency
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSN20_externalAccountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSN20_externalAccountNumber", "ABE_HZSN20_externalAccountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSN20_externalAccountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSN20_externalAccountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSN20_externalAccountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSN20_externalAccountNumber);
        //-- validation code for: _ABE_HZSN20_externalAccountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(20);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCAN_accountNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCAN_accountNumber", "ABE_HZCAN_accountNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCAN_accountNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCAN_accountNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCAN_accountNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCAN_accountNumber);
        //-- validation code for: _ABE_HZCAN_accountNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(15);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZUNMW_unmaturedWithdrawal
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZUNMW_unmaturedWithdrawal", "ABE_HZUNMW_unmaturedWithdrawal", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZUNMW_unmaturedWithdrawal();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZUNMW_unmaturedWithdrawal( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZUNMW_unmaturedWithdrawal = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZUNMW_unmaturedWithdrawal);
        //-- validation code for: _ABE_HZUNMW_unmaturedWithdrawal
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAII7_noticeAC
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAII7_noticeAC", "ABE_HZAII7_noticeAC", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAII7_noticeAC();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAII7_noticeAC( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAII7_noticeAC = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAII7_noticeAC);
        //-- validation code for: _ABE_HZAII7_noticeAC
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZCLS_closingWithdrawal
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZCLS_closingWithdrawal", "ABE_HZCLS_closingWithdrawal", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZCLS_closingWithdrawal();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZCLS_closingWithdrawal( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZCLS_closingWithdrawal = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZCLS_closingWithdrawal);
        //-- validation code for: _ABE_HZCLS_closingWithdrawal
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZPAB_sourceOriginalBranch
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZPAB_sourceOriginalBranch", "ABE_HZPAB_sourceOriginalBranch", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZPAB_sourceOriginalBranch();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZPAB_sourceOriginalBranch( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZPAB_sourceOriginalBranch = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZPAB_sourceOriginalBranch);
        //-- validation code for: _ABE_HZPAB_sourceOriginalBranch
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(4);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZPCHD_branchChangeDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZPCHD_branchChangeDate", "ABE_HZPCHD_branchChangeDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZPCHD_branchChangeDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZPCHD_branchChangeDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZPCHD_branchChangeDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZPCHD_branchChangeDate);
        //-- validation code for: _ABE_HZPCHD_branchChangeDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZABD_previousDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZABD_previousDescription", "ABE_HZABD_previousDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZABD_previousDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZABD_previousDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZABD_previousDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZABD_previousDescription);
        //-- validation code for: _ABE_HZABD_previousDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(35);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZDFRM_fontisDownloadFrequencyMeridian
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZDFRM_fontisDownloadFrequencyMeridian", "ABE_HZDFRM_fontisDownloadFrequencyMeridian", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZDFRM_fontisDownloadFrequencyMeridian();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZDFRM_fontisDownloadFrequencyMeridian( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZDFRM_fontisDownloadFrequencyMeridian = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZDFRM_fontisDownloadFrequencyMeridian);
        //-- validation code for: _ABE_HZDFRM_fontisDownloadFrequencyMeridian
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(3);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZTRM_transferMethod
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZTRM_transferMethod", "ABE_HZTRM_transferMethod", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZTRM_transferMethod();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZTRM_transferMethod( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZTRM_transferMethod = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZTRM_transferMethod);
        //-- validation code for: _ABE_HZTRM_transferMethod
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(2);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZXMD_transferMethodDescription
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZXMD_transferMethodDescription", "ABE_HZXMD_transferMethodDescription", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZXMD_transferMethodDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZXMD_transferMethodDescription( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZXMD_transferMethodDescription = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZXMD_transferMethodDescription);
        //-- validation code for: _ABE_HZXMD_transferMethodDescription
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(25);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZHLD_held
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZHLD_held", "ABE_HZHLD_held", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZHLD_held();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZHLD_held( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZHLD_held = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZHLD_held);
        //-- validation code for: _ABE_HZHLD_held
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAUT_authorised
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAUT_authorised", "ABE_HZAUT_authorised", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAUT_authorised();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAUT_authorised( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAUT_authorised = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAUT_authorised);
        //-- validation code for: _ABE_HZAUT_authorised
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZSTA_status
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZSTA_status", "ABE_HZSTA_status", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZSTA_status();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZSTA_status( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZSTA_status = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZSTA_status);
        //-- validation code for: _ABE_HZSTA_status
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(25);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZDLMZ_instructionDateLastMaintained
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZDLMZ_instructionDateLastMaintained", "ABE_HZDLMZ_instructionDateLastMaintained", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZDLMZ_instructionDateLastMaintained();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZDLMZ_instructionDateLastMaintained( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZDLMZ_instructionDateLastMaintained = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZDLMZ_instructionDateLastMaintained);
        //-- validation code for: _ABE_HZDLMZ_instructionDateLastMaintained
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZREF_lastCpReference
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZREF_lastCpReference", "ABE_HZREF_lastCpReference", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZREF_lastCpReference();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZREF_lastCpReference( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZREF_lastCpReference = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZREF_lastCpReference);
        //-- validation code for: _ABE_HZREF_lastCpReference
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZASIP_aCSettlInstPresent
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZASIP_aCSettlInstPresent", "ABE_HZASIP_aCSettlInstPresent", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZASIP_aCSettlInstPresent();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZASIP_aCSettlInstPresent( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZASIP_aCSettlInstPresent = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZASIP_aCSettlInstPresent);
        //-- validation code for: _ABE_HZASIP_aCSettlInstPresent
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZREFA_settlementReference
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZREFA_settlementReference", "ABE_HZREFA_settlementReference", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZREFA_settlementReference();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZREFA_settlementReference( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZREFA_settlementReference = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZREFA_settlementReference);
        //-- validation code for: _ABE_HZREFA_settlementReference
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(16);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZTADT_taxAdviceType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZTADT_taxAdviceType", "ABE_HZTADT_taxAdviceType", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZTADT_taxAdviceType();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZTADT_taxAdviceType( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZTADT_taxAdviceType = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZTADT_taxAdviceType);
        //-- validation code for: _ABE_HZTADT_taxAdviceType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZNTAD_taxAdviceTypeNextYear
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZNTAD_taxAdviceTypeNextYear", "ABE_HZNTAD_taxAdviceTypeNextYear", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZNTAD_taxAdviceTypeNextYear();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZNTAD_taxAdviceTypeNextYear( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZNTAD_taxAdviceTypeNextYear = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZNTAD_taxAdviceTypeNextYear);
        //-- validation code for: _ABE_HZNTAD_taxAdviceTypeNextYear
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setMaxLength(1);
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAUNO_lastAuditLetterNumber
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAUNO_lastAuditLetterNumber", "ABE_HZAUNO_lastAuditLetterNumber", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAUNO_lastAuditLetterNumber();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAUNO_lastAuditLetterNumber( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAUNO_lastAuditLetterNumber = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAUNO_lastAuditLetterNumber);
        //-- validation code for: _ABE_HZAUNO_lastAuditLetterNumber
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _ABE_HZAUDT_lastAuditLetterDate
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_ABE_HZAUDT_lastAuditLetterDate", "ABE_HZAUDT_lastAuditLetterDate", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getABE_HZAUDT_lastAuditLetterDate();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setABE_HZAUDT_lastAuditLetterDate( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_ABE_HZAUDT_lastAuditLetterDate = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_ABE_HZAUDT_lastAuditLetterDate);
        //-- validation code for: _ABE_HZAUDT_lastAuditLetterDate
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- _CRG1_crg1
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_CRG1_crg1", "CRG1_crg1", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                X76 target = (X76) object;
                return target.getCRG1_crg1();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    X76 target = (X76) object;
                    target.setCRG1_crg1( (java.lang.String) value);
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
        
        java.util.List substitutionGroupes_CRG1_crg1 = new java.util.ArrayList();
        desc.setSubstitutes(substitutionGroupes_CRG1_crg1);
        //-- validation code for: _CRG1_crg1
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
        return bf.com.misys.eq4.service.x76.X76.class;
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
