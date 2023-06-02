package ir.expensetracker.connector;

public class SMSCenter {
	private static String url = CoreBundle.SMS_URL;

	public static void sendSMS(String cellPhone, String nationalCode,String password) {
		String message = CoreBundle.SMS_MESSAGE_TEXT + System.lineSeparator() +
				System.lineSeparator() +
				CoreBundle.SMS_USERNAME + nationalCode + System.lineSeparator() +
				CoreBundle.SMS_PASSWORD + password + System.lineSeparator() +
				System.lineSeparator() +
				CoreBundle.SMS_MESSAGE_TEXT2;

		JSONObject body = new JSONObject();
		body.put(CoreBundle.SMS_NUMBERS, new JSONArray().put(cellPhone));
		body.put(CoreBundle.SMS_TEXT, message);
		body.put(CoreBundle.SMS_ENCODING, 0);
		body.put(CoreBundle.SMS_SENDERCODE, 0);
		JSONObject result=HTTPCaller.postRequest(url, body.toString());
		if(result.getInt(CommonBundle.HTTPSTATUSCODE)!=CoreBundle.SMS_SUCCESSSTATUSCODE|| !new JSONObject(result.getString(CommonBundle.HTTPRESULT)).get(CoreBundle.SMS_BUSINESSERRORS).equals(JSONObject.NULL)){
			throw new SMSException(result.toString(), ErrorBundle.smsError);
		}
	}


	public static void main(String[] args) {
		sendSMS("", "196007472","1234");
	}
}
