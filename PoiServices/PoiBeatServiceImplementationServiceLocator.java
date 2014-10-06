/**
 * PoiBeatServiceImplementationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PoiServices;

public class PoiBeatServiceImplementationServiceLocator extends org.apache.axis.client.Service implements PoiServices.PoiBeatServiceImplementationService {

    public PoiBeatServiceImplementationServiceLocator() {
    }


    public PoiBeatServiceImplementationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PoiBeatServiceImplementationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PoiBeatServiceImplementationPort
    private java.lang.String PoiBeatServiceImplementationPort_address = "http://192.168.1.68:10000/PoiBeat/";

    public java.lang.String getPoiBeatServiceImplementationPortAddress() {
        return PoiBeatServiceImplementationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PoiBeatServiceImplementationPortWSDDServiceName = "PoiBeatServiceImplementationPort";

    public java.lang.String getPoiBeatServiceImplementationPortWSDDServiceName() {
        return PoiBeatServiceImplementationPortWSDDServiceName;
    }

    public void setPoiBeatServiceImplementationPortWSDDServiceName(java.lang.String name) {
        PoiBeatServiceImplementationPortWSDDServiceName = name;
    }

    public PoiServices.PoiBeatServiceInterface getPoiBeatServiceImplementationPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PoiBeatServiceImplementationPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPoiBeatServiceImplementationPort(endpoint);
    }

    public PoiServices.PoiBeatServiceInterface getPoiBeatServiceImplementationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            PoiServices.PoiBeatServiceImplementationPortBindingStub _stub = new PoiServices.PoiBeatServiceImplementationPortBindingStub(portAddress, this);
            _stub.setPortName(getPoiBeatServiceImplementationPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPoiBeatServiceImplementationPortEndpointAddress(java.lang.String address) {
        PoiBeatServiceImplementationPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (PoiServices.PoiBeatServiceInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                PoiServices.PoiBeatServiceImplementationPortBindingStub _stub = new PoiServices.PoiBeatServiceImplementationPortBindingStub(new java.net.URL(PoiBeatServiceImplementationPort_address), this);
                _stub.setPortName(getPoiBeatServiceImplementationPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PoiBeatServiceImplementationPort".equals(inputPortName)) {
            return getPoiBeatServiceImplementationPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://PoiServices/", "PoiBeatServiceImplementationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://PoiServices/", "PoiBeatServiceImplementationPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PoiBeatServiceImplementationPort".equals(portName)) {
            setPoiBeatServiceImplementationPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
