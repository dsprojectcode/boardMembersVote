/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;

/**
 *
 * @author fab
 */
public interface ClientINTF extends Remote {
    public  void Login() throws RemoteException;
    public  Connection connection() throws RemoteException;
    
}
