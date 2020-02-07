/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author fab
 */
public class FrameIp extends JFrame implements ActionListener{
    FindInterface impl=new ImplementFindInterface() ;
    ArrayList<FindInterface> clientsList;
    
        public FrameIp() throws RemoteException{
            //clientsList=clientsLists;
            }
   // FindInterface fint=new ImplementFindInterface();

    
   public void IpList(List<RemoteIpInfo> remoteIps) throws RemoteException{
     //  private Simplebo
       String [] col={"Ip No","Subnet","Wan Ip"};
       List<RemoteIpInfo> remoteIp=remoteIps;
       JFrame f=new JFrame("select from remote db");
       
     
      // 
       JTable tbl=new JTable();
        DefaultTableModel mod= new DefaultTableModel();
         mod.setRowCount(remoteIp.size());
         mod.setColumnIdentifiers(col);
        int row=0;
         for(RemoteIpInfo ip:remoteIp){
             mod.setValueAt(ip.getIpNo(), row, 0);
             mod.setValueAt(ip.getSubnet(),row,1);
             mod.setValueAt(ip.getWanIp(), row, 2);
//             mod.setValueAt(ip.getStatus(),row,3);
             row++;
       }
      tbl.setModel(mod);
       
       JScrollPane sp=new JScrollPane(tbl);
       f.add(sp);
       f.setVisible(true);
       f.pack();
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

       
}
   
//   public void addFrame(){
//       JFrame fr=new JFrame("Insert record remotly");
//       JPanel panel=new JPanel();
//       JButton rg=new JButton("Insert");
//       
//       JLabel lblipno,lblsubnet,lblwanip;
//       JTextField txtip,txtsubnet,txtwanip;
//       
//       lblipno=new JLabel("Ip No.");
//       lblsubnet=new JLabel("Subnet Mask");
//       lblwanip=new JLabel("Wan Ip");
//       
//       txtip=new JTextField(10);
//       txtsubnet=new JTextField(10);
//       txtwanip=new JTextField(10);
//       
//        panel.setLayout(new GridLayout(4, 2));
//       
//       panel.add(lblipno); panel.add(txtip);
//       panel.add(lblsubnet); panel.add(txtsubnet);
//       panel.add(lblwanip); panel.add(txtwanip);
//       
//       fr.add(panel);
//       fr.setVisible(true);
//       fr.pack();
//       
//      
//   }
//  
        JMenu candidate,vote,fileshare,report;
       JMenuItem candreg,votercast,votecount,votedtl,itmfileshare;
   
   public synchronized void menus(){
       
       JFrame f=new JFrame("DS Project IN RMI");
       JMenuBar mb=new JMenuBar();
       
       
       candidate=new JMenu("Candidate");
       vote=new JMenu("Vote");
       fileshare=new JMenu("File_sharing");
       report=new JMenu("Reports");
       
       candreg=new JMenuItem("Candidate_Reg");
       votecount=new JMenuItem("Vote_Result");
       votedtl=new JMenuItem("Voters cast");
       votercast=new JMenuItem("Casting_vote");
       
       itmfileshare=new JMenuItem("Share");
       
       candidate.add(candreg);
       vote.add(votercast);
       report.add(votecount);
       report.add(votedtl);
       fileshare.add(itmfileshare);
       
       mb.add(candidate);
       mb.add(vote);
       mb.add(fileshare);
       mb.add(report);
       
       f.setJMenuBar(mb);
       f.setVisible(true);
       f.setSize(500, 500);
       f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        votercast.addActionListener(this);
        candreg.addActionListener(this);
        fileshare.addActionListener(this);
        votecount.addActionListener(this);
        votedtl.addActionListener(this);
        itmfileshare.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("here");
      if(e.getSource()==votercast)  {
        
          try {
             // System.out.println("here");
             //while(true){
              impl.VoteFrame();
             //}
          } catch (RemoteException ex) {
              Logger.getLogger(FrameIp.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
      else if(e.getSource()==candreg)  {
        
          try {
             // System.out.println("here");
              impl.Candidate();
          } catch (RemoteException ex) {
              Logger.getLogger(FrameIp.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
       else if(e.getSource()==votecount)  {
        
          try {
             // System.out.println("here");
              impl.reports();
          } catch (RemoteException ex) {
              Logger.getLogger(FrameIp.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
      
       else if(e.getSource()==votedtl)  {
        
          try {
             // System.out.println("here");
              impl.voterCastDeatil();
          } catch (RemoteException ex) {
              Logger.getLogger(FrameIp.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
      else if(e.getSource()==itmfileshare)  {
        
          try {
             // System.out.println("here");
              impl.fileshareFrame();
          } catch (RemoteException ex) {
              Logger.getLogger(FrameIp.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
      
    }
}
