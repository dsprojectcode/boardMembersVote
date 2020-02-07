/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_project;

import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author fab
 */
public class RemoteIpInfo implements java.io.Serializable{
    private int id;
    private String ipNo;
   private String  subnet;
    private String wanIp;
    private int status;
    
     public void setIpNo(String ipNo){
      this.ipNo=ipNo;  
    }
     
    public void setSubnet(String subnet){
      this.subnet=subnet;  
    }
    public void setWanIp(String wanIp){
      this.wanIp=wanIp;  
    }
    public void setStatus(int status){
      this.status=status;  
    }
    
     public String getIpNo(){
      return ipNo;  
    }
     
    public String getSubnet(){
        return subnet;
    }
   
    public String getWanIp(){
    return wanIp;    
    }
    public int getStatus(){
       return status; 
    }
   
    
   
    
}
