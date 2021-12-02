package com.coursemgmt.courses.monitoring;

/*
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.HttpClientParams;
import java.io.*;*/

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// source : https://mkyong.com/java/how-to-send-http-request-getpost-in-java/

public class HTTPRequestGenerator {

	// declare variables for URIs
	private static String uri_home = "http://127.0.0.1:8080/";
	private static String uri_new_course = "http://127.0.0.1:8080/showNewCourseForm/";
	private static String uri_update_form = "http://127.0.0.1:8080/showFormForUpdate/";
	private static String uri_save_course = "http://127.0.0.1:8080/saveCourse";
	private static String uri_update_course = "http://127.0.0.1:8080/updateCourse";
	private static String uri_delete_course = "http://127.0.0.1:8080/deleteCourse/";

	private final CloseableHttpClient http_client = HttpClients.createDefault();
  	
  	public void close() throws IOException {
        	http_client.close();
    	}
    	
    	// http://127.0.0.1:8080/
    	public void sendGET_home() throws Exception {

        	HttpGet request = new HttpGet(uri_home);

        	try (CloseableHttpResponse response = http_client.execute(request)) {
            		// Get HttpResponse Status
            		System.out.println(response.getStatusLine().toString());
            		HttpEntity entity = response.getEntity();
            		if (entity != null) {
            			// String result = EntityUtils.toString(entity);
                		// System.out.println(result);
                		System.out.println("VIEW HOME PAGE request sent successfully");
            		}
        	}
	}
	
	// http://127.0.0.1:8080/showNewCourseForm/
	public void sendGET_new_course() throws Exception {

        	HttpGet request = new HttpGet(uri_new_course);

        	try (CloseableHttpResponse response = http_client.execute(request)) {
            		// Get HttpResponse Status
            		System.out.println(response.getStatusLine().toString());
            		HttpEntity entity = response.getEntity();

            		if (entity != null) {
                  		System.out.println("NEW COURSE PAGE request sent successfully");
            		}
        	}
	}
	
	// http://127.0.0.1:8080/deleteCourse/
	public void sendGET_delete_course(String cid) throws Exception {

    		String uri_with_param = uri_delete_course + cid;
        	HttpGet request = new HttpGet(uri_with_param);        	
        	
        	try (CloseableHttpResponse response = http_client.execute(request)) {
            		// Get HttpResponse Status
            		System.out.println(response.getStatusLine().toString());
            		HttpEntity entity = response.getEntity();
            		if (entity != null) {
                		System.out.println("DELETE request for course ID " + cid + " sent successfully");
            		} 
        	}
    	}
    	
    	// http://127.0.0.1:8080/showFormForUpdate/
    	public void sendGET_update_form(String cid) throws Exception {

    		String uri_with_param = uri_update_form + cid;
        	HttpGet request = new HttpGet(uri_with_param);        	
        	
        	try (CloseableHttpResponse response = http_client.execute(request)) {
            		// Get HttpResponse Status
            		System.out.println(response.getStatusLine().toString());
            		HttpEntity entity = response.getEntity();
            		if (entity != null) {
                		System.out.println("GET UPDATE COURSE FORM request for course ID " + cid + " sent successfully");
            		} 
        	}
    	}
	
	// incomplete   
	// http://127.0.0.1:8080/saveCourse 
	public void sendPOST_save_course() throws Exception {

        	HttpPost post = new HttpPost(uri_save_course);

        	// add request parameter, form parameters
        	List<NameValuePair> urlParameters = new ArrayList<>();
        	urlParameters.add(new BasicNameValuePair("data", "CS1008,Human%20Computer%20Interaction,Sarah%20Sebo,100001"));
        	//urlParameters.add(new BasicNameValuePair("password", "123"));
        	//urlParameters.add(new BasicNameValuePair("custom", "secret"));
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		//http://127.0.0.1:31112/function/savecourse?data=CS1008,Human%20Computer%20Interaction,Sarah%20Sebo,100


        	post.setEntity(new UrlEncodedFormEntity(urlParameters));

        	try (CloseableHttpClient http_client = HttpClients.createDefault();
             		CloseableHttpResponse response = http_client.execute(post)) {
            		System.out.println(EntityUtils.toString(response.getEntity()));
        	}

    	}
    	
    	
}
