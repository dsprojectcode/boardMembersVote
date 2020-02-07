/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author fab
 */
public interface FindInterface extends Remote{
//    public String find(String value) throws RemoteException;
//    public String send(String msg)  throws RemoteException;
    
    public void setClient(FindInterface a)  throws RemoteException;
    public FindInterface getClient()  throws RemoteException;
    
    public String getName()  throws RemoteException;
     public String getMsg()  throws RemoteException;
//     public void sendRequest(String request,String type)  throws RemoteException;
     ///////
    // public boolean login(FileCL)
     public boolean Copy() throws RemoteException;
      public boolean transferData(String filename,byte[] data,int len)  throws RemoteException;
      public void fileshareFrame()throws RemoteException;
      
       public void setFile(String f,String destination)  throws RemoteException;
       //public byte[] getDesktop() throws RemoteException;
       
       public Connection connection() throws RemoteException;
       public List<RemoteIpInfo> remoteIp() throws RemoteException;
       public void insertRecord(RemoteIpInfo rip) throws RemoteException;
       
        public void Candidate()throws RemoteException;
       public void VoteFrame()throws RemoteException;
        public void Login() throws RemoteException;
        
        public void reports() throws RemoteException;
        public void voterCastDeatil() throws RemoteException;
//       public void setRecords(String ipnumber,String subnet,String WANIp) throws RemoteException;
        public void addClient(FindInterface fn) throws RemoteException;
        
         public void callMenus()throws RemoteException;
}
