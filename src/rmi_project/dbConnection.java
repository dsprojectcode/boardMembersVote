/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fab
 */
public class dbConnection {
    
//    public Connection connection() throws SQLException{
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            
//             con=DriverManager.getConnection("jdbc:mysql://localhost:3030/RMI","root","oib@123");  
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return con;
//    }
    
    public ResultSet getIpList(Connection con) throws SQLException{
      //Connection con=  connection();
        ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select * from ipinfo where status=0");
             rs=p.executeQuery();
             
              //con.close();
        
        return rs;
    }

    public int insertRemotly(RemoteIpInfo ipinfo,Connection con) throws SQLException {
//         Connection con=  connection();
       
        
            
            PreparedStatement p=con.prepareStatement("insert into ipinfo(ipNo,subnet,wanIp) values(?,?,?)");
            p.setString(1, ipinfo.getIpNo());
             p.setString(2, ipinfo.getSubnet());
              p.setString(3, ipinfo.getWanIp());
              // con.close();
             return p.executeUpdate();
              
        
    }
    
    public ResultSet getCandidates_forVote(Connection con) throws SQLException{
      ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select * from candidates");
             rs=p.executeQuery();
        // con.close();
        return rs;   
    }

  public  int saveVoted(String[] voted,Connection con,String voter) throws SQLException {
      deleteIfduplicate(voter,con);
        PreparedStatement p = null;
        int x=0;
        for(int i=0;i<voted.length;i++){
            if(voted[i]!=null){
          p=con.prepareStatement("insert into vote(code,voter) values(?,?)");
            // p.setString(1, voter);
          p.setString(1, voted[i]);
            p.setString(2, voter);
             x+=p.executeUpdate();
             
              //con.close();
            }
        }
          
           return x;
        
    }
  public void deleteIfduplicate(String voter,Connection con) throws SQLException{
     PreparedStatement p = null; 
      p=con.prepareStatement("delete from vote where voter=?");
            // p.setString(1, voter);
          p.setString(1, voter);
          p.executeUpdate();
           //con.close();
  }
  
    public ResultSet Login(String username,String pw,Connection con) throws SQLException{
         ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select * from users where username=? and password=?");
             p.setString(1, username);
              p.setString(2, pw);
             rs=p.executeQuery();
         //con.close();
        return rs;   
    
}

    void candidateReg(String code, String name,Connection con) throws SQLException {
     PreparedStatement p = null;
             
           
          p=con.prepareStatement("insert into candidates(code,name) values(?,?)");
            // p.setString(1, voter);
          p.setString(1, code);
            p.setString(2, name);
            p.executeUpdate();
           
         // con.close();
    }

    void deleteSelected(String code,Connection con) throws SQLException {
        PreparedStatement p=con.prepareStatement("delete from candidates where code=?");
            // p.setString(1, voter);
          p.setString(1, code);
            
            p.executeUpdate();
            //con.close();
    }

    ResultSet getVoteCount(Connection con) throws SQLException {
        ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select count(a.code) as count,a.code,b.name from vote a inner join candidates b on a.code=b.code group by a.code order by count desc");
             
             rs=p.executeQuery();
         //con.close();
        return rs;
        
    }

    ResultSet getVoterCastDetail(Connection con) throws SQLException {
        ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select b.code,b.name,a.voter from vote a inner join candidates b on a.code=b.code order by a.voter");
             
             rs=p.executeQuery();
         //con.close();
        return rs;
        
       
    }

    public ResultSet maximunVote(Connection con) throws SQLException {
        ResultSet rs = null;
        
            
            PreparedStatement p=con.prepareStatement("select mxVote from parammxvote");
             
             rs=p.executeQuery();
         //con.close();
        return rs;
    }
}
