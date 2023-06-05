package ir.expensetracker.connector;

import ir.expensetracker.constants.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HTTPCaller {

	private static Logger logger = LogManager.getLogger(HTTPCaller.class);
	public static Map<String,Object> postRequest(String url, String token, String body) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			post.setHeader(Constants.CONTENTTYPE, Constants.JSON);
			post.setHeader(Constants.AUTHORIZATION, token);
			if (body != null) {
				StringEntity bodyEntity = new StringEntity(body, Constants.UTF8);
				post.setEntity(bodyEntity);
			}
			logger.info("url - {}, body - {} ",url,body);
			HttpResponse response = client.execute(post);

			StringBuffer result = new StringBuffer();
			if ((response.getEntity() != null) && (response.getEntity().getContent() != null)) {
				InputStreamReader streamReader = new InputStreamReader(
						response.getEntity().getContent(), Constants.UTF8);
				BufferedReader br = new BufferedReader(streamReader);

				String line = "";
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
			}

			Map<String,Object> output = new HashMap<>();
			output.put(Constants.HTTPSTATUSCODE, response.getStatusLine().getStatusCode());
			output.put(Constants.HTTPRESPONSEMESSAGE, response.getStatusLine().getReasonPhrase());
			output.put(Constants.HTTPRESULT, result.toString());
			logger.info(" result - {}" , result);
			return output;
		}catch(Throwable e){
			logger.error(e,e);
		}
		return null;
	}
}
