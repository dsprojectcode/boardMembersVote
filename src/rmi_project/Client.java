/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import static rmi_project.ImplementFindInterface.in_username;
import static rmi_project.ImplementFindInterface.userlist;

/**
 *
 * @author fab
 */
public class Client extends JFrame implements ActionListener,ClientINTF,Runnable{
    
    Connection con= connection();
 dbConnection db=new dbConnection();
 
    static JLabel frompath,topath,iplbl,namelbl,llbclient,lbliptoshutdown,empty;
     static FindInterface fn;
      static JTextField ip,dest,name;
     static JButton btn1,addnew,listip,vote,login;
     
     ImplementFindInterface imp=new ImplementFindInterface();
     
    @Override
     public void run(){
        try {
            //while(true){
            Login();
            //}
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public Client(FindInterface fintr) throws RemoteException{
        fn.addClient(fintr);
     }
     
     public final  JFrame getmain(){
                    return this;
                }
     
    public static void main(String []args) {///////////////main
        Client cl;
        JFrame frame=new JFrame("RMI Project");
        
        
        JPanel p,p1,p3,p2;
        ButtonGroup bgp=new ButtonGroup();
        
         btn1=new JButton("Copy");
       
       
       // txt1=new JTextField(20);
         //txt2=new JTextField(20);
       
         //label
         frompath=new JLabel();
         topath=new JLabel();
         iplbl=new JLabel("Destintion Ip");
         namelbl=new JLabel("Enter client name");
         llbclient=new JLabel();
         
//         addnew=new JButton("Addnew");
//         listip=new JButton("IpList");
//         
//         vote=new JButton("Vote");
//         
//         login=new JButton("Login");
//         
//         addnew.setBorderPainted(false);
//           listip.setBorderPainted(false);
           
         //text field
         ip=new JTextField(20);
         //dest=new JTextField(20);
          name=new JTextField(10);
         //radio
        JRadioButton file_transfer,file_transfer_to,shut,logoff,restart,hibernate;
//        file_transfer=new JRadioButton("fileCopy_From");
//         file_transfer_to=new JRadioButton("fileCopy_To");
         
         
       // shut=new JRadioButton("Shutdown");
//        logoff=new JRadioButton("Logoff");
        // restart=new JRadioButton("Restart");
        // hibernate=new JRadioButton("Abort");
         
         
         
         //group
        // bgp.add(shut);
         // bgp.add(logoff);
          // bgp.add(restart);
           // bgp.add(hibernate);
            
            //panel
            p=new JPanel();
             p1=new JPanel();
             p3=new JPanel();
            p2=new JPanel();
             
                p1.setLayout(new GridLayout(3,2));
             
                p.setLayout(new GridLayout(2,4));
                
               // lbliptoshutdown=new JLabel("Enter Ip");
               // empty=new JLabel();
                // p.add(lbliptoshutdown);
               // p.add(ip);
               // p.add(empty);
                
                // p.add(shut);
                // p.add(logoff);
                 //p.add(restart);
                  //p.add(hibernate);
                  
                 
               
//                 p1.add(file_transfer);
//                 p1.add(frompath);
                // p1.add(iplbl);
                // p1.add(dest);
                // p1.add(btn1);
                 
                 p3.setLayout(new GridLayout(3,3));
                  p3.add(namelbl);
                  p3.add(name);
                  p3.add(llbclient);
                  
                  llbclient.setForeground(Color.red);
                  
                  //p2.add(listip);
                 // p2.add(addnew);
                  //p2.add(vote);
                 // p2.add(login);
                  
                //p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Remote pc"));
               // p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"File Transfer Remotly"));
               
                //adding actionlistenerrrrr
                         
                //file_transfer.addActionListener(cl);
                //btn1.addActionListener(cl);
                
               // shut.addActionListener(cl);
                 //logoff.addActionListener(cl);
               // restart.addActionListener(cl);
                //hibernate.addActionListener(cl);  
                //hibernate.addActionListener(cl);
//                listip.addActionListener(cl);
//                addnew.addActionListener(cl);
//                vote.addActionListener(cl);
//                login.addActionListener(cl);
                
                
                
                name.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e){
                        int k=e.getKeyCode();
                        if(k==KeyEvent.VK_ENTER){
                            try {
                                Toolkit.getDefaultToolkit().beep();
                                 
                                fn=new ImplementFindInterface(name.getText());
                                
                               
                                
                                   fn=(FindInterface)Naming.lookup("rmi://192.168.1.3:1902/abc");
                                         llbclient.setText("Client "+name.getText()+" is ready");
//                                         fn.Login();
                                         new Thread(new Client(fn)).start();
                                       //ImplementFindInterface  fnx=new ImplementFindInterface(fn);
                                        //new Client(fn);
//                             String rmiendpt="20";
//                              String servicename="server1";
//                                if(args.length>0){
//                                     rmiendpt=args[0]; 
//                                       servicename=args[1];
//                                }
                               
                                
//                                        fn=(FindInterface)Naming.lookup(rmiendpt+"/"+servicename);
                                   
                                
                               //  final JFrame f=new JFrame();
                                 //f.dispose();
                                  //this.dispose();
                                
                                 
                            } catch (RemoteException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NotBoundException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     
                        }
                    }
});
                
              
      //  JFileChooser fc=new JFileChooser("D:");
       // fc.showSaveDialog(null);
        //frame.add(file_transfer);
        
      
        
        //frame.add(p,BorderLayout.WEST);
       // frame.add(p1,BorderLayout.CENTER);
        frame.add(p3,BorderLayout.NORTH);
        //frame.add(p2,BorderLayout.EAST);
        //f.add(bgp);
        frame.setVisible(true);
        //frame.setSize(1000,800);
//        frame.setSize(500, 500);
//        frame.pack();
frame.setSize(400, 300);
        ///////////////////////////////////////////
         
     
            
     


        
        
       // frame.pack();
       
      
    }

     @Override
    public void actionPerformed(ActionEvent e) {
      String com=e.getActionCommand();
//      if(com.equals("fileCopy_From")){
//          JFileChooser fc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//          int sv=fc.showSaveDialog(null);
//          if(sv==JFileChooser.APPROVE_OPTION){
//             frompath.setText(fc.getSelectedFile().getAbsolutePath());
//          }
//      }
//      
//      else if(com.equals("Copy")){
//          try {
//              fn.setFile(frompath.getText(),dest.getText());
//              fn.Copy();
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//      }
      
//       if(com.equals("Shutdown")){
//          try {
//             
//              fn.sendRequest(ip.getText(),com);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//      }
//       else if(com.equals("Abort")){
//          try {
//              fn.sendRequest(ip.getText(),com);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//      }
//        else if(com.equals("Restart")){
//          try {
//              fn.sendRequest(ip.getText(),com);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//      }   
//      else if(com.equals("Logoff")){
//          try {
//              fn.sendRequest(ip.getText(),com);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//      }
      
//      else if(com.equals("IpList")){
//        
//          try {
//               FrameIp i = new FrameIp();
//               
//               
//               List<RemoteIpInfo> remoteIp=fn.remoteIp();
//               i.IpList(remoteIp);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//        
//                 
//      }
//      
//      
//      else if(com.equals("Addnew")){
//        RemoteIpInfo iprmt=new RemoteIpInfo();
//          try {
//              // FrameIp i = new FrameIp();
//               
//               
//              fn.insertRecord(iprmt);
//              // i.IpList(remoteIp);
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//        
//                 
//      }
//       else if(com.equals("Vote")){
//          try {
//              fn.VoteFrame();
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//       }
//      else if(com.equals("Login")){
//          try {
//              fn.Login();
//          } catch (RemoteException ex) {
//              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//          }
//       }
    }

    @Override
    public void Login() throws RemoteException {
         JFrame f=new JFrame();
        JLabel username,password,lblerror,lblsuccess;
        JTextField txtusername=new JTextField(10);
        
        JPasswordField pw=new JPasswordField(10);
        JButton btn=new JButton("Login");
        
        username=new JLabel("User name");
         password=new JLabel("Password");
         lblerror=new JLabel();
         lblsuccess=new JLabel();
         
         JPanel p=new JPanel();
         p.setLayout(new GridLayout(3,2));
         p.add(username);
         p.add(txtusername);
         p.add(password);
          p.add(pw);
          p.add(btn);
          p.setBackground(Color.cyan);
          f.add(p);
          f.setVisible(true);
          f.setSize(500, 500);
          f.toFront();
          f.setLocationRelativeTo(null);
//           f.setLayout(null);
           f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         btn.setForeground(Color.BLUE);
         
       // in_username=  new String[clientsList.size()];
        userlist=new ArrayList<String>();
        
          btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
             ResultSet  rs = db.Login(txtusername.getText(),String.copyValueOf(pw.getPassword()), con);
             if(rs!=null){
              if (rs.next()){
                     in_username=rs.getString("username");
                // rip.setIpNo(rs.getString("ipNo")); 
                userlist.add(in_username);
              final Frame[] fr=Frame.getFrames();
              for(final Frame frm:fr){
                  frm.dispose();
              }
                  try {
                   
                    FrameIp m = new FrameIp();
                         m.menus();
//                    for(int i=0;i<clientsList.size();i++){
//                     clientsList.get(i).callMenus();
//                    }
                  } catch (RemoteException ex) {
                      Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
                 
                            }
         } 
              else{
                      in_username=null;   
                        }
              System.out.println(in_username);
                }
               
             catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
                
            }
            
        });
    
      
         
    }
    
   
    
    @Override
    public synchronized Connection connection() throws RemoteException{
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            try {  
                con=DriverManager.getConnection("jdbc:mysql://localhost:3030/RMI","root","oib@123");
            } catch (SQLException ex) {
                Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
