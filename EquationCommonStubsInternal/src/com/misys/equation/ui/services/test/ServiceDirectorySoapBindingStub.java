/**
 * ServiceDirectorySoapBindingStub.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.misys.equation.ui.services.test;

public class ServiceDirectorySoapBindingStub extends org.apache.axis.client.Stub implements
				com.misys.equation.ui.services.test.ServiceDirectory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectorySoapBindingStub.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private final java.util.Vector cachedSerClasses = new java.util.Vector();
	private final java.util.Vector cachedSerQNames = new java.util.Vector();
	private final java.util.Vector cachedSerFactories = new java.util.Vector();
	private final java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[42];
		_initOperationDesc1();
		_initOperationDesc2();
		_initOperationDesc3();
		_initOperationDesc4();
		_initOperationDesc5();
	}

	private static void _initOperationDesc1()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("setContext");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("removeSession");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getEqSession");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqSessionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("exchangeBFKey");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "exchangeBFKeyReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("userExitValidate");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "userExitValidateReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getEqDataList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in7"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in8"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in9"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqDataListReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getEqReferral");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqReferralReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getRecentOptionHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getRecentOptionHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getFullMenuHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getFullMenuHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getUserSpoolFilesHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getUserSpoolFilesHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;

	}

	private static void _initOperationDesc2()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getUnitSpoolFilesHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getUnitSpoolFilesHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getJobLogHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getJobLogDirHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogDirHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getJobLogEntryHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in7"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogEntryHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[13] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getMsgQueueHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[14] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getMsgQueueDirHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueDirHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[15] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getMsgQueueEntryHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueEntryHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[16] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getMsgFileEntryHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgFileEntryHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[17] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("destroyPools");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[18] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getWorkLoadHTML");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getWorkLoadHTMLReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[19] = oper;

	}

	private static void _initOperationDesc3()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getJournalPrint");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in7"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJournalPrintReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[20] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("applyTransaction");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in7"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in8"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in9"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "applyTransactionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[21] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("applyTransactionData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "applyTransactionDataReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[22] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("remoteSupervisor");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "remoteSupervisorReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[23] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("removeSupervisor");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[24] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("checkSessionStatus");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "checkSessionStatusReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[25] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("authoriseBySupervisorOverride");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[26] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("authoriseBySupervisorRm");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[27] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("authoriseBySupervisorId");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper
						.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
										"authoriseBySupervisorIdReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[28] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("invalidateValidationUserExit");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[29] = oper;

	}

	private static void _initOperationDesc4()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("logoffDesktop");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[30] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getPromptHelpDetails");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getPromptHelpDetailsReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[31] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("addChildFunctionHandler");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper
						.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
										"addChildFunctionHandlerReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[32] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("resetContext");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "resetContextReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[33] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("isSessionAlive");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "isSessionAliveReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[34] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("updateFunctionData");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "updateFunctionDataReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[35] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getJobId");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobIdReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[36] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getPredictiveList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getPredictiveListReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[37] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("removeSpooledFile");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in4"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in5"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in6"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[38] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("repeatingGroupPageAction");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper
						.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
										"repeatingGroupPageActionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[39] = oper;

	}

	private static void _initOperationDesc5()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("breakByRepatingDataAction");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
						"breakByRepatingDataActionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[40] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("repeatingGroupSortAction");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in0"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in1"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in2"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName(
						"http://services.ui.equation.misys.com", "in3"), org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false,
						false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper
						.setReturnQName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
										"repeatingGroupSortActionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[41] = oper;

	}

	public ServiceDirectorySoapBindingStub() throws org.apache.axis.AxisFault
	{
		this(null);
	}

	public ServiceDirectorySoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
					throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public ServiceDirectorySoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault
	{
		if (service == null)
		{
			super.service = new org.apache.axis.client.Service();
		}
		else
		{
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException
	{
		try
		{
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet)
			{
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null)
			{
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null)
			{
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null)
			{
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null)
			{
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null)
			{
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements())
			{
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			return _call;
		}
		catch (java.lang.Throwable _t)
		{
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public void setContext(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "setContext"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void removeSession(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "removeSession"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getEqSession(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, java.lang.String in6) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqSession"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, in6 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String exchangeBFKey(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "exchangeBFKey"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String userExitValidate(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "userExitValidate"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, in6 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getEqDataList(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, int in8, int in9)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqDataList"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, in6, in7,
							new java.lang.Integer(in8), new java.lang.Integer(in9) });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getEqReferral(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getEqReferral"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getRecentOptionHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getRecentOptionHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getFullMenuHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getFullMenuHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getUserSpoolFilesHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getUserSpoolFilesHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getUnitSpoolFilesHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getUnitSpoolFilesHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getJobLogHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getJobLogDirHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogDirHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getJobLogEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobLogEntryHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, in6, in7 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getMsgQueueHTML(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[14]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getMsgQueueDirHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[15]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueDirHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getMsgQueueEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[16]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgQueueEntryHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getMsgFileEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[17]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getMsgFileEntryHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void destroyPools(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[18]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "destroyPools"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getWorkLoadHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[19]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getWorkLoadHTML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getJournalPrint(java.lang.String in0, java.lang.String in1, int in2, int in3, int in4,
					java.lang.String in5, java.lang.String in6, java.lang.String in7) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[20]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJournalPrint"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, new java.lang.Integer(in2),
							new java.lang.Integer(in3), new java.lang.Integer(in4), in5, in6, in7 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String applyTransaction(java.lang.String in0, java.lang.String in1, int in2, int in3, int in4,
					java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[21]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "applyTransaction"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, new java.lang.Integer(in2),
							new java.lang.Integer(in3), new java.lang.Integer(in4), in5, in6, in7, in8, in9 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String applyTransactionData(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[22]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "applyTransactionData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String remoteSupervisor(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[23]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "remoteSupervisor"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void removeSupervisor(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[24]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "removeSupervisor"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String checkSessionStatus(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[25]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "checkSessionStatus"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void authoriseBySupervisorOverride(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[26]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
						"authoriseBySupervisorOverride"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void authoriseBySupervisorRm(java.lang.String in0, java.lang.String in1, java.lang.String in2, int in3)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[27]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "authoriseBySupervisorRm"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, new java.lang.Integer(in3) });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String authoriseBySupervisorId(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[28]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "authoriseBySupervisorId"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void invalidateValidationUserExit(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[29]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com",
						"invalidateValidationUserExit"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void logoffDesktop(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[30]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "logoffDesktop"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getPromptHelpDetails(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[31]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getPromptHelpDetails"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String addChildFunctionHandler(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[32]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "addChildFunctionHandler"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String resetContext(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[33]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "resetContext"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String isSessionAlive(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[34]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "isSessionAlive"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String updateFunctionData(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[35]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "updateFunctionData"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getJobId(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[36]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getJobId"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String getPredictiveList(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, int in6) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[37]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "getPredictiveList"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call
							.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, new java.lang.Integer(in6) });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public void removeSpooledFile(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, int in6) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[38]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "removeSpooledFile"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call
							.invoke(new java.lang.Object[] { in0, in1, in2, in3, in4, in5, new java.lang.Integer(in6) });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			extractAttachments(_call);
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String repeatingGroupPageAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[39]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "repeatingGroupPageAction"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String breakByRepatingDataAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[40]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "breakByRepatingDataAction"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public java.lang.String repeatingGroupSortAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (super.cachedEndpoint == null)
		{
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[41]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "repeatingGroupSortAction"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { in0, in1, in2, in3 });

			if (_resp instanceof java.rmi.RemoteException)
			{
				throw (java.rmi.RemoteException) _resp;
			}
			else
			{
				extractAttachments(_call);
				try
				{
					return (java.lang.String) _resp;
				}
				catch (java.lang.Exception _exception)
				{
					return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
				}
			}
		}
		catch (org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

}
