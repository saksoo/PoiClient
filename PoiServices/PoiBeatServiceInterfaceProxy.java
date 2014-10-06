package PoiServices;

public class PoiBeatServiceInterfaceProxy implements PoiServices.PoiBeatServiceInterface {
  private String _endpoint = null;
  private PoiServices.PoiBeatServiceInterface poiBeatServiceInterface = null;
  
  public PoiBeatServiceInterfaceProxy() {
    _initPoiBeatServiceInterfaceProxy();
  }
  
  public PoiBeatServiceInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initPoiBeatServiceInterfaceProxy();
  }
  
  private void _initPoiBeatServiceInterfaceProxy() {
    try {
      poiBeatServiceInterface = (new PoiServices.PoiBeatServiceImplementationServiceLocator()).getPoiBeatServiceImplementationPort();
      if (poiBeatServiceInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)poiBeatServiceInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)poiBeatServiceInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (poiBeatServiceInterface != null)
      ((javax.xml.rpc.Stub)poiBeatServiceInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public PoiServices.PoiBeatServiceInterface getPoiBeatServiceInterface() {
    if (poiBeatServiceInterface == null)
      _initPoiBeatServiceInterfaceProxy();
    return poiBeatServiceInterface;
  }
  
  public boolean loginUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (poiBeatServiceInterface == null)
      _initPoiBeatServiceInterfaceProxy();
    return poiBeatServiceInterface.loginUser(arg0);
  }
  
  public java.lang.String registerUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (poiBeatServiceInterface == null)
      _initPoiBeatServiceInterfaceProxy();
    return poiBeatServiceInterface.registerUser(arg0);
  }
  
  public java.lang.String setMonitorData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (poiBeatServiceInterface == null)
      _initPoiBeatServiceInterfaceProxy();
    return poiBeatServiceInterface.setMonitorData(arg0, arg1);
  }
  
  public java.lang.String getMapData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (poiBeatServiceInterface == null)
      _initPoiBeatServiceInterfaceProxy();
    return poiBeatServiceInterface.getMapData(arg0, arg1);
  }
  
  
}