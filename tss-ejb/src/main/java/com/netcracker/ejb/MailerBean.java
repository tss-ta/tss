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

import com.netcracker.entity.User;
import com.netcracker.util.PasswordsKeeper;

/**
 *
 * @author Stanislav Zabielin
 */
public class MailerBean implements SessionBean {

	private final String username = "team.a.taxi@gmail.com";
	private final String password = String.valueOf(PasswordsKeeper.getEmailPassword());

	public void sendEmail(User user, String title,String msg ) {
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
					InternetAddress.parse(user.getEmail()));
			message.setSubject(title);
			message.setText(msg);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
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
