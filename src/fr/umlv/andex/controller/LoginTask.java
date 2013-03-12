package fr.umlv.andex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import fr.umlv.andex.util.Property;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		try {

			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost("http://"+Property.SERVER_ADRESS+":"+Property.SERVER_PORT+"/connect");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username",params[0]));
			nameValuePairs.add(new BasicNameValuePair("password",params[1]));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
			
			httpPost.getRequestLine();


			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();


			if (resEntity != null) {
				System.out.println("Response content length: " + resEntity.getContentLength());
				System.out.println("Chunked?: " + resEntity.isChunked());
				String responseBody = EntityUtils.toString(resEntity);
				System.out.println("Data: " + responseBody);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
