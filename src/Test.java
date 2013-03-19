import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost("http://127.0.0.1:12345/connect");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username","foo1"));
			nameValuePairs.add(new BasicNameValuePair("password","xyz"));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

			httpPost.getRequestLine();


			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();


			if (resEntity != null) {
				String responseBody = EntityUtils.toString(resEntity);
				try {
					JSONObject jobj = new JSONObject(responseBody);
					System.out.println(jobj);
					JSONArray exams = jobj.getJSONArray("exams");
					System.out.println(exams);
					for(int i =0;i<exams.length();i++){
						System.out.println(exams.getString(i));
					}
					
					System.out.println();
					
					
					HttpGet httpGet = new HttpGet("http://127.0.0.1:12345/exam?token="+jobj.getString("token")+"&exam="+exams.getString(0));
					
				
					httpGet.getRequestLine();
					
					 response = httpclient.execute(httpGet);
					 resEntity = response.getEntity();

					if (resEntity != null) {
						 responseBody = EntityUtils.toString(resEntity);
						 System.out.println(responseBody);
					}
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
