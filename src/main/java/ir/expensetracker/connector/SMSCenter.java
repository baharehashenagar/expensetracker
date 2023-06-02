package ir.expensetracker.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.expensetracker.constants.Constants;
import ir.expensetracker.exception.SMSException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SMSCenter {
	private static ObjectMapper mapper=new ObjectMapper();
	private static Logger logger = LogManager.getLogger(SMSCenter.class);

	public static void sendSMS(String url, String token, String cellPhone, String message) {
		List<String> recipients=new ArrayList<>();
		recipients.add(cellPhone);

		Map<String,Object> body = new HashMap<>();
		body.put(Constants.SMS_ORIGINATOR, "+983000505");
		body.put(Constants.SMS_RECIPIENTS, recipients);
		body.put(Constants.SMS_MESSAGE, message);

		try {
			Map<String,Object> result= HTTPCaller.postRequest(url, token, mapper.writeValueAsString(body));
			if(!Integer.valueOf(result.get(Constants.HTTPSTATUSCODE).toString()).equals(Constants.SMS_SUCCESSSTATUSCODE)){
				throw new SMSException(result.toString(), Constants.SMS_ERROR);
			}
		} catch (JsonProcessingException e) {
			logger.error(e,e);
		}
	}
}
