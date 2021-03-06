package fr.umlv.andex.controller;

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
					
					
					HttpGet httpGet = new HttpGet("http://"+Property.SERVER_ADRESS+":"+Property.SERVER_PORT+"/exam");
					
					
					List<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
					nameValuePairs1.add(new BasicNameValuePair("token",jobj.optString("token")));
					nameValuePairs1.add(new BasicNameValuePair("exam","mathexam1"));
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
					
					httpPost.getRequestLine();
					
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
			e.printStackTrace();
		}
		return null;
	}

}
