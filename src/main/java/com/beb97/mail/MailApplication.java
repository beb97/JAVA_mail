package com.beb97.mail;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@SpringBootApplication
public class MailApplication implements CommandLineRunner {

	@Autowired
	private JavaMailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String to = "beb97@hotmail.com";
		String subject = "test service";
		String text = "Hello world";
		sendSimpleMessage(to, subject, text);
	}
	/**
	MailSender interface: The top-level interface that provides basic functionality for sending simple emails
	JavaMailSender interface: the subinterface of the above MailSender. It supports MIME messages and is mostly used in conjunction with the MimeMessageHelper class for the creation of a MimeMessage. It's recommended to use the MimeMessagePreparator mechanism with this interface
	JavaMailSenderImpl class: provides an implementation of the JavaMailSender interface. It supports the MimeMessage and SimpleMailMessage
	SimpleMailMessage class: used to create a simple mail message including the from, to, cc, subject and text fields
	MimeMessagePreparator interface: provides a callback interface for the preparation of MIME messages
	MimeMessageHelper class: helper class for the creation of MIME messages. It offers support for images, typical mail attachments and text content in an HTML layout
	 */

	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("testmail@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		try {
			System.out.println("Sending mail to: "+to);
			emailSender.send(message);
			System.out.println("Sent!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void runTest() {
//		try (SimpleSmtpServer dumbster = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT)) {
//
//			sendMessage(dumbster.getPort(), "sender@here.com", "Test", "Test Body", "receiver@there.com");
//
//			List<SmtpMessage> emails = dumbster.getReceivedEmails();
//			assertThat(emails, hasSize(1));
//			SmtpMessage email = emails.get(0);
//			assertThat(email.getHeaderValue("Subject"), is("Test"));
//			assertThat(email.getBody(), is("Test Body"));
//			assertThat(email.getHeaderValue("To"), is("receiver@there.com"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}

