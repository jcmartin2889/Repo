package com.misys.equation.utility.test;

import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import junit.framework.TestCase;

public class SimpleMail extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SimpleMail.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private final String mailhost = "smtp.gmail.com";

	public void test() throws Exception
	{
		sendMail("BankFusionEquation Demo", "Email stubbed and ready to hook into an activity step.", "bfeqdemo@gmail.com",
						"chris.weddell@misys.com");
	}

	public synchronized void sendMail(String subject, String bodyT, String sender, String recipients) throws Exception
	{

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.from", sender);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("bfeqdemo@gmail.com", "bankfusi0n");
			}
		});
		// create and fill the body
		MimeBodyPart body = new MimeBodyPart();
		body.setText(bodyT);

		// create and fill the attachment, it is assumed to be a File
		File f = new File("E:\\EQRPT.pdf");
		DataSource source = new FileDataSource(f);
		MimeBodyPart attachement = new MimeBodyPart();
		attachement.setDataHandler(new DataHandler(source));
		attachement.setFileName(f.getName());

		// create the Multipart and add the body and attachment
		Multipart multipartContent = new MimeMultipart();
		multipartContent.addBodyPart(body);
		multipartContent.addBodyPart(attachement);

		MimeMessage message = new MimeMessage(session);
		message.setSubject(subject);
		message.setContent(multipartContent);
		if (recipients.indexOf(',') > 0)
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
		else
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

		Transport.send(message);

	}
}
