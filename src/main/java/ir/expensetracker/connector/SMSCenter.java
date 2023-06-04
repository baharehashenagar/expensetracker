package ir.expensetracker.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.expensetracker.constants.Constants;
import ir.expensetracker.exception.SMSException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class SMSCenter {
	private static ObjectMapper mapper=new ObjectMapper();
	private static Logger logger = LogManager.getLogger(SMSCenter.class);

	@Value("${SMS.URL}")
	private String smsUrl;

	@Value("${SMS.ORIGINATOR}")
	private String smsOriginator;

	@Value("${SMS.TOKEN}")
	private String token;

	@Value("${SMS.FORGET_PASSWORD_MESSAGE}")
	private String forgetPasswordMessage;

	@Value("${SMS.REGISTRATION_MESSAGE}")
	private String registrationMessage;

	private void sendSMS(String cellPhone, String message) {
		String messageToken=("AccessKey "+token);
		List<String> recipients=new ArrayList<>();
		recipients.add(cellPhone);

		Map<String,Object> body = new HashMap<>();
		body.put(Constants.SMS_ORIGINATOR, smsOriginator);
		body.put(Constants.SMS_RECIPIENTS, recipients);
		body.put(Constants.SMS_MESSAGE, message);

		try {
			Map<String,Object> result= HTTPCaller.postRequest(smsUrl, messageToken, mapper.writeValueAsString(body));
			if(!Integer.valueOf(result.get(Constants.HTTPSTATUSCODE).toString()).equals(Constants.SMS_SUCCESSSTATUSCODE)){
				throw new SMSException(result.toString(), Constants.SMS_ERROR);
			}
		} catch (JsonProcessingException e) {
			logger.error(e,e);
		}
	}

	public void sendRegistrationSMS(String cellPhone, String username ,String password){
		sendSMS(cellPhone, (registrationMessage+"Your username="+username+" your password="+password));
	}

	public void sendForgetPasswordSMS(String cellPhone, String password){
		sendSMS(cellPhone, forgetPasswordMessage+System.lineSeparator()+password);
	}
}
