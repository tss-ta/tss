/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;


import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;



/**
 *
 * @author maks
 */

public class MailerBean implements SessionBean {


    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

    }


    public void ejbRemove() throws EJBException, RemoteException {

    }


    public void ejbActivate() throws EJBException, RemoteException {

    }


    public void ejbPassivate() throws EJBException, RemoteException {
       
    }
    
    public boolean sendMail(String emailAddres, String message1){
//              // Recipient's email ID needs to be mentioned.
//      String to = "maksbrunarskiy@gmail.com";
//
//      // Sender's email ID needs to be mentioned
//      String from = "maksbrunarskiy@ukr.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      try{
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO,
//                                  new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//
//         // Now set the actual message
//         message.setText("This is actual message");
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      }catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
        
        
        
        
        
        
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//
//        String msgBody = "...!!!!!!";
//
//        try {
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress("admin@tss.com", "Example.com Admin"));
//            msg.addRecipient(Message.RecipientType.TO,
//                             new InternetAddress("maksbrunarskiy@gmail.com", "Mr. User"));
//            msg.setSubject("Your Example.com account has been activated");
//            msg.setText(msgBody);
//            Transport.send(msg);
//
//        } catch (AddressException ex) {
//            Logger.getLogger(MailerBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(MailerBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(MailerBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   
 
        
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
