package io.altar.auth_example.util;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;


// https://www.baeldung.com/java-email-sendgrid
// https://www.twilio.com/docs/sendgrid/for-developers/sending-email/v3-java-code-example
public class SendGridService {
    
    private static final String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");
    private static final String FROM_EMAIL = System.getenv("FROM_EMAIL");
    private static final String FROM_NAME = System.getenv("FROM_NAME");

    private static final String REGISTER_TEMPLATE_ID = "d-bf37c60f3c0a47a19575d2671e7158b5";
    private static final String CONFIRMATION_TEMPLATE_ID = "d-17209e4d800b434baa31e92e7be6fee7";
    
    public static void sendPasswordResetEmail(String toEmail, String resetUrl) throws Exception {
        if (SENDGRID_API_KEY == null || FROM_EMAIL == null) {
            throw new IllegalStateException("SendGrid API key or sender email not configured");
        }
        
        Email from = new Email(FROM_EMAIL, FROM_NAME);
        Email to = new Email(toEmail);
        String subject = "Password Reset Request";
        
        // Create HTML content with the reset URL
        String htmlContent = "<html><body>" +
                "<h2>Password Reset Request</h2>" +
                "<p>Hello,</p>" +
                "<p>You have requested a password reset. Please click the link below to reset your password:</p>" +
                "<a href=\"" + resetUrl + "\">Reset Password</a>" +
                "<p>If you did not request this, please ignore this email.</p>" +
                "<p>Thank you!</p>" +
                "</body></html>";
        
        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, to, content);
        
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        
        if (response.getStatusCode() != 202) {
            throw new Exception("Failed to send email: " + response.getBody());
        }
    }


	public static boolean sendPasswordResetEmail(String destinatario, String resetUrl, String nome) {
		// Using a simple map-like approach to represent JSON data
		String[] keys = {"reset_url", "nome"};
		String[] values = {resetUrl, nome};
		return sendEmailWithTemplate(destinatario, REGISTER_TEMPLATE_ID, keys, values);
	}

	public static boolean sendConfirmationEmail(String destinatario, String confirmationUrl, String username) {
		// Using a simple map-like approach to represent JSON data
		String[] keys = {"confirmation_url", "username"};
		String[] values = {confirmationUrl, username};
		return sendEmailWithTemplate(destinatario, CONFIRMATION_TEMPLATE_ID, keys, values);
	}
	
	private static boolean sendEmailWithTemplate(String toEmail, String templateId, String[] keys, String[] values) {
		try {
			if (SENDGRID_API_KEY == null || FROM_EMAIL == null) {
				throw new IllegalStateException("SendGrid API key or sender email not configured");
			}
			
			Email from = new Email(FROM_EMAIL, FROM_NAME);
			Email to = new Email(toEmail);

			Mail mail = new Mail();
			mail.setFrom(from);
			mail.setTemplateId(templateId);

			Personalization personalization = new Personalization();
			personalization.addTo(to);

			// Add dynamic template data (this is the key change - using JSON-like structure)
			for (int i = 0; i < keys.length && i < values.length; i++) {
				personalization.addDynamicTemplateData(keys[i], values[i]);
			}

			mail.addPersonalization(personalization);

			SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);

			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sendGrid.api(request);

			return response.getStatusCode() >= 200 && response.getStatusCode() < 300;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}