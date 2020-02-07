/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


/**
 *
 * @author fab
 */
public class Server {
     
   public static void main(String []args) throws MalformedURLException {
       try {
        
           FindInterface intrf=new ImplementFindInterface("server");
           LocateRegistry.createRegistry(1902);
       
        
           Naming.rebind("rmi://192.168.1.3:1902/abc",intrf);
           


            System.out.println("Server ready!");
           
        
       } catch (RemoteException ex) {
           System.out.println(ex);
       }
       }
   }


