/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import static rmi_project.Client.btn1;
import static rmi_project.Client.iplbl;

/**
 *
 * @author fab
 */
public class ImplementFindInterface extends UnicastRemoteObject implements FindInterface,ActionListener{
    
     String fileClient;
     public String name;
    public FindInterface clnt=null;
    String msg;
     private ArrayList<FindInterface> clientsList;
     
     
    
    public ImplementFindInterface() throws RemoteException{
      super();
     
    }
    
    public ImplementFindInterface(String n) throws RemoteException{
       this.name=n;
        clientsList=new ArrayList<FindInterface>();
       //clientsList.add(f);
    }
    
     @Override
     public synchronized void addClient(FindInterface f) throws RemoteException{
       
       clientsList.add(f);
         System.out.println(f);
    }
    
    

    

    @Override
    public synchronized void setClient(FindInterface a) throws RemoteException {
        System.out.println(a);
    }

    @Override
    public FindInterface getClient() throws RemoteException {
       return clnt;
    }
   public synchronized String getName()  throws RemoteException {
       return this.name;
   } 
   
   public synchronized String getMsg()  throws RemoteException {
       return msg;
   } 
    Runtime rt=Runtime.getRuntime();
    Scanner s=new Scanner(System.in);

       ////////////
private String file="";
private String destination;
private String ipNo,subnet,wanIp;


    JTextField frompath,destpath;
    JRadioButton file_transfer; 
    JButton copy;
    JLabel frompaths,topath,lblsusccess;
    
     @Override
     public synchronized void fileshareFrame(){
         JFrame f=new JFrame("Remote File Sharing");
         
         
       
         JPanel p=new JPanel();
         copy=new JButton("Copy");
         
         topath=new JLabel("To Path");
         frompaths=new JLabel();
         lblsusccess=new JLabel();
        
        frompath=new JTextField(10);
        destpath=new JTextField(10);
        
        file_transfer=new JRadioButton("Select file");
         
        p.setLayout(new GridLayout(3,2));
          p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Remote File Sharing"));
          
         
          topath=new JLabel("To Path");
          
          file_transfer.addActionListener(this);
          copy.addActionListener(this);
          
          copy.setForeground(Color.blue);
          
          
          
          p.add(file_transfer);
          p.add(frompaths);
          p.add(topath);
          p.add(destpath);
          p.add(copy);
          p.add(lblsusccess);
          
          f.add(p);
          f.setVisible(true);
         // f.pack();
          f.setLocationRelativeTo(null);
          f.setSize(800,400);
     }
    
     @Override
     public synchronized void setFile(String f,String dest){
        file=f;
        destination=dest;
    }
  
    @Override
    public synchronized boolean Copy() throws RemoteException {
        //FileInputStream in=null;
        
        try {
            File f=new File(file);
          FileInputStream  in = new FileInputStream(f);
            byte[] dt=new byte[1024*1024];
            int len=in.read(dt);
            while(len>0){
                transferData(f.getName(), dt, len);
                len=in.read(dt);
                //System.out.println(len);
                
            }   } catch (Exception ex) {
          ex.printStackTrace();
        } 
        
        return true;
    }
   ///////////////////////////////////////////

    @Override
    public synchronized boolean transferData(String filename, byte[] data, int len) throws RemoteException {
        try {
            //System.out.println("");
            File f=new File(destination+"\\"+filename);
            f.createNewFile();
            FileOutputStream out=new FileOutputStream(f,true);
            out.write(data, 0, len);
            out.flush();
            out.close();
            System.out.println("Done");
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         return true;
    }
   

 Connection con= connection();
 dbConnection db=new dbConnection();
    @Override
    public synchronized List<RemoteIpInfo> remoteIp() throws RemoteException {
        List<RemoteIpInfo> iplist=new ArrayList<>();
         try {
             //dbConnection db=new dbConnection();
             ResultSet rs=db.getIpList(con);
             while (rs.next()){
                 RemoteIpInfo rip=new RemoteIpInfo();
                 rip.setIpNo(rs.getString("ipNo"));
                 rip.setSubnet(rs.getString("wanIp"));
                 rip.setWanIp(rs.getString("subnet"));
                 rip.setStatus(rs.getInt("status"));
                 iplist.add(rip);
             }
             rs.close();
         } catch (Exception ex) {
             System.out.println(ex);
         }
         return iplist;
    }

    @Override
    public synchronized void insertRecord(RemoteIpInfo rip) throws RemoteException {
         JFrame fr=new JFrame("Insert record remotly");
       JPanel panel=new JPanel();
       JButton rg=new JButton("Insert");
       
       JLabel lblipno,lblsubnet,lblwanip,lblsuccess;
       JTextField txtip,txtsubnet,txtwanip;
       
       lblipno=new JLabel("Ip No.");
       lblsubnet=new JLabel("Subnet Mask");
       lblwanip=new JLabel("Wan Ip");
       lblsuccess=new JLabel();
       
       txtip=new JTextField(10);
       txtsubnet=new JTextField(10);
       txtwanip=new JTextField(10);
       
        panel.setLayout(new GridLayout(4, 2));
       
       panel.add(lblipno); panel.add(txtip);
       panel.add(lblsubnet); panel.add(txtsubnet);
       panel.add(lblwanip); panel.add(txtwanip);
        panel.add(rg);panel.add(lblsuccess);
       
//        dbConnection db=new dbConnection();
        rg.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
               rip.setIpNo(txtip.getText());
               rip.setSubnet(txtsubnet.getText());
               rip.setWanIp(txtwanip.getText());
                 try {
                     int x=db.insertRemotly(rip,con);
                     if(x>0){
                         lblsuccess.setText("Remote data Successfully Inserted");
                         lblsuccess.setForeground(Color.GREEN);
                     }else{
                         lblsuccess.setText("Error with insertion");
                         lblsuccess.setForeground(Color.red);
                     }
                 } catch (SQLException ex) {
                     Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                 }
               
             }
         });
       
       fr.add(panel);
       fr.setVisible(true);
       fr.pack();
    }
    
    
    
     JTable candtbl;
     String [] candselect;
    
     @Override
    public synchronized void Candidate() throws RemoteException {
        JFrame frm=new JFrame("Candidates Registration");
        JPanel pan1,pan2;
        JButton btn,delete;
        JTextField txtcode,txtname;
        JLabel lblcode,lblname,blberror;
        
        pan1=new JPanel();
        pan2=new JPanel();
        btn=new JButton("Save");
        delete=new JButton("Delete");
                
        txtcode=new JTextField(10);
        txtname=new JTextField(10);
        
        lblcode=new JLabel("cand code");
        lblname=new JLabel("cand name");
        blberror=new JLabel();
       
        pan1.setLayout(new GridLayout(3,2));
        pan1.add(lblcode);pan1.add(txtcode);
         pan1.add(lblname);pan1.add(txtname);
         pan1.add(btn);pan1.add(blberror);
         frm.add(pan1,BorderLayout.NORTH);
         
         btn.setForeground(Color.blue);
//         frm.pack();
         
         tbl=new JTable();
         JScrollPane sp=new JScrollPane(tbl);
         
        // pan1.setLayout(new GridLayout(3,1));
         
         pan2.add(sp);
         pan2.add(delete);
         
          try {
             load_data();
         } catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         frm.add(pan2,BorderLayout.CENTER);
         frm.setSize(600, 600);
         frm.setVisible(true);
//         frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
           btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    candidateReg(txtcode.getText(),txtname.getText());
                    load_data();
                } catch (Exception ex) {
                    blberror.setText("Error saving");
                    blberror.setForeground(Color.red);
                }
            }
        });
           
           
           delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String code = null;
                 Boolean bl;
                
                      
                for(int i=0;i<tbl.getRowCount();i++){
                     bl=Boolean.valueOf(tbl.getValueAt(i, 2).toString());
                    if(bl && tbl.getValueAt(i, 0).toString()!=null){
                         try {
                             code=tbl.getValueAt(i, 0).toString();
                             deleteSelected(code);
                            
                         } catch (SQLException ex) {
                             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                         }
                      }
                     
                }
                try {
                         load_data();
                     } catch (SQLException ex) {
                         Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
            }
        });
    }
    
    
    ////////////////////
    public synchronized void candidateReg(String code,String name) throws SQLException{
        db.candidateReg(code,name,con);
       
        //con.close();
              
        
    }
     public void  deleteSelected(String code) throws SQLException{
        db. deleteSelected(code,con);
         //con.close();
     }
    ///////////////////////
     JTable tbl;
     String [] selected;
      private int   mxVote=0;
     @Override
    public synchronized void VoteFrame() throws RemoteException{
       
             JFrame frame=new JFrame("Candidates");
             JLabel lblcncode,lblcnname;
             JTextField txtcncod,txtcnname;
             
             JButton vote,refresh;
             
             vote=new JButton("Save vote");
             refresh=new JButton("Refresh");
                     
              tbl=new JTable();
              
              vote.setForeground(Color.BLUE);
//               refresh.setForeground(Color.BLUE);

JPanel p,p1;
p=new JPanel();
p1=new JPanel();
        
//p.setLayout(new GridLayout(1,4));
JScrollPane sp=new JScrollPane(tbl);
p.add(sp);
p.add(refresh);
p1.add(vote);
frame.add(p,BorderLayout.NORTH);
frame.add(p1,BorderLayout.CENTER);
//frame.add(vote);
frame.setVisible(true);
//frame.pack();
frame.setSize(600, 700);

         try {
             load_data();
         } catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         try {
             ResultSet res_mxvote=db.maximunVote(con);
             if(res_mxvote.next()){
               mxVote=res_mxvote.getInt("mxVote");
             }
         } catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
         
   tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      
                 @Override
                 public void valueChanged(ListSelectionEvent e) {
                     //tbl.getv
                     
                     ArrayList<String> xx=new ArrayList<>();
                     for(int i=0;i<tbl.getRowCount();i++){
                         if(Boolean.valueOf(tbl.getValueAt(i, 2).toString())){
                   String x=tbl.getValueAt(i, 0).toString();
                   xx.add(x);
                             //System.out.print(String.valueOf(xx[i]));
                   if(xx.size()==mxVote){
                       System.out.println("listening---");
                       tbl.setEnabled(false);
                   }
                         }
                     }
                 }
             });
    vote.addActionListener(new ActionListener() {
//     String [] selectedvalue=null;
    
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     boolean b;
                      
                     //selectedvalue=selected;
                     for(int i=0;i<tbl.getRowCount();i++){
                      b=Boolean.valueOf(tbl.getValueAt(i, 2).toString());
                      if(b && tbl.getValueAt(i, 0).toString()!=null){
                           
//                            selected[i]
                    String val=tbl.getValueAt(i, 0).toString();
                    selected[i] =val;
                         // System.out.println(selected[i]);
            
                      }
                    }
                     
                     try {
                         int x= saveVoted(selected,ImplementFindInterface.in_username);
                         //System.out.println(selected.length);
                     } catch (SQLException ex) {
                         Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                     }
                  }
             });
    
    refresh.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     try {
                         load_data();
                         tbl.setEnabled(true);
                     } catch (SQLException ex) {
                         Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
   
    }

    
   
    ////////////////////
    private  void load_data() throws SQLException
    {
        
int noofcondidate=0;
  try {

ResultSet rs=db.getCandidates_forVote(con);
String [] col={"code","Cand_name","select"};

if(rs.last()){
    noofcondidate=rs.getRow();
    rs.beforeFirst();
}

 
selected=new String[noofcondidate];
tbl.setModel(new DefaultTableModel());
DefaultTableModel mod= new DefaultTableModel(){
    
    @Override
    public Class<?> getColumnClass(int column){
        switch(column){
            case 0:
                return String.class ;
            case 1:
                return String.class ;
                 case 2:
                return Boolean.class ;
            default:
                
                return String.class ;
        }
    }
};          
mod.setRowCount(noofcondidate);
mod.setColumnIdentifiers(col);
int row=0;
while (rs.next() && rs!=null){
   // mod.addRow(new Object[0]);
    mod.setValueAt(rs.getString("code"), row, 0);
    mod.setValueAt(rs.getString("name"), row, 1);
    mod.setValueAt(false, row, 2);
    
    row++;
}
tbl.setModel(mod);

  }
   catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    
    
    public int saveVoted(String [] voted,String voter) throws SQLException{
        int x=db.saveVoted(voted,con,voter);
        load_data();
        return x;
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
     private JFrame fr;
    public static String in_username;
    public static ArrayList<String> userlist;
     
   // @Override
    public synchronized void Login() throws RemoteException{
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
    
      
         
            
             
       
}//End of Login
    
     
     @Override
 public synchronized void callMenus() throws RemoteException{
      
          }
    @Override
    public synchronized void reports() throws RemoteException {
       JFrame f=new JFrame("Vote Count");
       JLabel rank,name,code,count;
       
       JLabel resrank,resname,rescode,rescount;
       
       JPanel p=new JPanel();
//       public static final int BOLD=1;
       Font font=new Font("Courier",Font.BOLD,12);
       rank=new JLabel("Rank");
       name=new JLabel("Candidate name");
       code=new JLabel("Candidate Code");
       count=new JLabel("Count");
       
//       resrank=new JLabel();
//       resname=new JLabel();
//       rescode=new JLabel();
//       rescount=new JLabel();
       
       
       
       rank.setForeground(Color.blue);
       rank.setFont(font);
       name.setForeground(Color.blue);
       name.setFont(font);
       code.setForeground(Color.blue);
       code.setFont(font);
       count.setForeground(Color.blue);
       count.setFont(font);
       
//       code.setBounds(100, 100, 100, 30);
//       name.setBounds(130, 100, 100, 30);
//       count.setBounds(160, 100, 100, 30);
//       rank.setBounds(190, 100, 100, 30);
       
              
               
        
       
                   
                    
                   
                  
       
//       p.setLayout(new GridLayout(3,4));
       
      // p.setLayout(new GridLayout());
         try {
               int countrows=0;
             ResultSet  rs =db.getVoteCount(con);
             if(rs.next()){
                 rs.last();
                 countrows=rs.getRow();
                 rs.beforeFirst();
                 
                 
                   f.add(code);
                    f.add(name);
                    f.add(count);
                    f.add(rank);
             
                      //f.add(p);
                //p.setLayout(new GridLayout(1,4));
            

//                    p.setLayout(new GridLayout(6,4));
              
                     
                   
                                int i=1,y=1;
              while(rs.next()){
              
               resrank=new JLabel();
               resname=new JLabel();
               rescode=new JLabel();
               rescount=new JLabel();
              
              
               if(rs.getRow()!=1){
                 String cnt=rs.getString("count");
                  rs.previous();
                  String cnt2=rs.getString("count");
                if(cnt.equals(cnt2)) {
                    
                   i--; 
                   rs.next();
                   
                   rescode.setText(rs.getString("code"));
               resname.setText(rs.getString("name"));
               rescount.setText(rs.getString("count"));
               resrank.setText(String.valueOf(i));
               
               i++;
               y++;
               
                }
                else{
                    
                  rs.next();  
                  rescode.setText(rs.getString("code"));
               resname.setText(rs.getString("name"));
               rescount.setText(rs.getString("count"));
               resrank.setText(String.valueOf(y));
               i++;
               y++;
                }
               }
               else{
                   
                   rescode.setText(rs.getString("code"));
               resname.setText(rs.getString("name"));
               rescount.setText(rs.getString("count"));
               resrank.setText(String.valueOf(i));
               
               
               i++;
               y++;
                   
               }
               
               
               
                    f.add(rescode);
                    f.add(resname);
                    f.add(rescount);
                    f.add(resrank);
               
              }
              }
             
              f.setLayout(new GridLayout(0,4));
        //f.add(p);
        f.setVisible(true);
        f.setSize(600, 600);
         
         
         } catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       
                   
        
         
       
       
    
    }

    @Override
    public synchronized void voterCastDeatil() throws RemoteException {
        JFrame f=new JFrame();
        Font font=new Font("Courier",Font.BOLD,12);
          JLabel code,name,voter,valcode,valname,valvoter;
          
          code=new JLabel("Code");
          name=new JLabel("Name");
          voter=new JLabel("Voter");
          
          code.setForeground(Color.blue);
          code.setFont(font);
          
          name.setForeground(Color.blue);
          name.setFont(font);
          
          voter.setForeground(Color.blue);
          voter.setFont(font);
          
          
          f.add(voter);
          f.add(code);
          f.add(name);
         
          
         try {
             ResultSet  rs =db.getVoterCastDetail(con);
             
             while(rs.next()){
                 valcode=new JLabel();
                 valname=new JLabel();
                 valvoter=new JLabel();
                 
                 valvoter.setText(rs.getString("voter"));
                 valcode.setText(rs.getString("code"));
                 valname.setText(rs.getString("name"));
                 
                 f.add(valvoter);
                 f.add(valcode);
                 f.add(valname);
                
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
         }
          
        f.setLayout(new GridLayout(0, 3));
        f.setVisible(true);
        f.setSize(600, 600);
    }

    @Override
    public  void actionPerformed(ActionEvent e) {
        if(e.getSource()==file_transfer){
            JFileChooser fc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
          int sv=fc.showSaveDialog(null);
          if(sv==JFileChooser.APPROVE_OPTION){
             frompaths.setText(fc.getSelectedFile().getAbsolutePath());
             
          }
        }
        else if(e.getSource()==copy){
            try { 
                setFile(frompaths.getText(),destpath.getText());
               boolean b= Copy();
               if(b){
                   lblsusccess.setText("Successfully Copied");
                   lblsusccess.setForeground(Color.GREEN);
               }
            } catch (RemoteException ex) {
                Logger.getLogger(ImplementFindInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
}