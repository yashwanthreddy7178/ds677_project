/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.SystemInitial;
import net.Sender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.Receiver;

/**
 *
 * @author Lida
 */
public class SystemOperation {
    
    
    public static void firstDataProcess()
        {
            Connection conn = SystemInitial.getConn();
            String sql = "select * from CategoryInfo";
            PreparedStatement pstmt;
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            Sender sender = new Sender("Queue");

            DataInfo dataInfo = new DataInfo();

            while (rs.next())
            {
                String categoryName = rs.getString(1);
                String categoryID = rs.getString(2);
                System.out.println("category name: "+categoryName+" category ID: "+ categoryID);
                dataInfo.setItemName("@"+categoryName);
                dataInfo.setItemID(categoryID);
                dataInfo.printItem();
                sender.sendMessage(dataInfo);
            }
            } catch (Exception e) {
                 e.printStackTrace();
            }


            sql = "select * from TransferInfo";
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            Sender sender = new Sender("Queue");

            DataInfo dataInfo = new DataInfo();

            while (rs.next())
            {
                String transferName = rs.getString(1);
                String transferID = rs.getString(2);
                System.out.println("Transfer name: "+transferName+" Transfer Amount: "+ transferID);
                dataInfo.setItemName("#"+transferName);
                dataInfo.setItemID(transferID);
                dataInfo.printItem();
                sender.sendMessage(dataInfo);
            }
            } catch (Exception e) {
                 e.printStackTrace();
            }

        }
    
     public static void resultProcess(){
          try{
        Receiver receiver = new Receiver("Queue2");
            Thread receiverThread = new Thread(receiver);  
            receiverThread.start();}
        catch(Exception e){
            e.printStackTrace();
        }
         
     }
    public static void checkDataProcess(String categoryID){
        Connection conn = SystemInitial.getConn();
        String sql = "select * from " + categoryID;
        PreparedStatement pstmt;
        try {
        pstmt = (PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        Sender sender = new Sender("Queue3");

        DataInfo dataInfo = new DataInfo();

        while (rs.next())
        {
            String transferName = rs.getString(1);
            String transferID = rs.getString(2);
            System.out.println("check transfer name: "+transferName+" check transfer amount: "+ transferID);
            dataInfo.setItemName("!"+transferName);
            dataInfo.setItemID(transferID);
            dataInfo.printItem();
            sender.sendMessage(dataInfo);
        }


        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    
}
