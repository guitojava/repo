package com.memopage;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// THANKS to  http://www.techcrony.info/?p=23#comment-4605 



 public class SendGMail {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String emailMsgTxt = "Test Message Contents";
	private static final String emailSubjectTxt = "A test from gmail";
	private static final String emailFromAddress = "georgiosleon@gmail.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String[] sendTo = { "georgiosleon@gmail.com" };

	public static void main(String args[]) throws Exception {

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		new SendGMail().sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt,	emailFromAddress);
		System.out.println("Sucessfully Sent mail to All Users");
	}

	
	
	public void sendSSLMessage(String recipients[], String subject,  String message, String from) throws MessagingException {
		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.encoding", "UTF-8");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"georgiosleon@gmail.com", "arxangellos");
					}
				});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		//msg.addHeader("charset", "UTF-8");

		
		
		// Setting the Subject and Content Type
		msg.setSubject(subject);
		
		
		msg.setHeader("Content-Type","text/plain; charset=\"utf-8\"");
		msg.setHeader("Content-Transfer-Encoding", "quoted-printable");
		msg.setContent(message, "text/plain");
		
		Transport.send(msg);
		
		
	}
}