/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.netcracker.entity.Driver;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.util.GlobalVariables;
import com.netcracker.util.PasswordsKeeper;

/**
 *
 * @author Stanislav Zabielin
 * @author maks
 */
public class MailerBean implements SessionBean {

	private final String username = "team.a.taxi@gmail.com";
	private final String password = String.valueOf(PasswordsKeeper
			.getEmailPassword());

	public void sendEmail(String emailAddress, String title, String msg) {
		if (!GlobalVariables.isMailerOn)
			return;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailAddress));
			message.setSubject(title);
			message.setText(msg);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
	public void sendEmail(User user, String title, String msg){
            sendEmail(user.getEmail(), title, msg);
        }

	public void changeToUpdated(User user, TaxiOrder to) {
		sendEmail(
				user,
				"Yours Taxi Order was Updated",
				"Dear, " + user.getUsername() + ". Your Taxi Order (id = "
						+ to.getId()
						+ ") status was changed from “Queued” to “Updated”.");
	}

	public void changeToRefused(User user, TaxiOrder to) {
		sendEmail(
				user,
				"Yours Taxi Order was Refused",
				"Dear, "
						+ user.getUsername()
						+ ". Your Taxi Order (id = "
						+ to.getId()
						+ ") was Refused. We apologise for any inconveniences.");
	}

	public void changeToAssigned(User user, TaxiOrder to, String driver) {
		sendEmail(user, "Driver was assigned to your Taxi Order", "Dear, "
				+ user.getUsername() + ". Driver was assigned to your Taxi Order (id = " + to.getId()
				+ "). Information about driver: "+ driver);
	}

	public void changeToCompleted(User user, TaxiOrder to) {
		sendEmail(
				user,
				"Yours Taxi Order was Completed",
				"Dear, " + user.getUsername() + ". Your Taxi Order (id = "
						+ to.getId() + ") status was Completed.");
	}
    @Deprecated
	public void sendToken(String email, Integer token) {
		sendEmail(
				email,
				"Driver Registration",
				"Hello!" +
						"  Please, register your Driver account using this: " + token + " token."

		);
	}

    public void sendDriverInvite(String email, String signupURL) {
        sendEmail(
                email,
                "Driver Registration",
                "Hello user! To register your driver account, please, follow this link: " + signupURL);
    }
	@Override
	public void setSessionContext(SessionContext ctx) throws EJBException,
			RemoteException {

	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {

	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
	}

}
