/**
 * PoiBeatServiceInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PoiServices;

public interface PoiBeatServiceInterface extends java.rmi.Remote {
    public boolean loginUser(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String registerUser(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String setMonitorData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String getMapData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
