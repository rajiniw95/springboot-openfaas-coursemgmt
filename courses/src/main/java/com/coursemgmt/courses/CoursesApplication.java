package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CoursesApplication.class, args);
		
		HTTPRequestGenerator httpreq = new HTTPRequestGenerator();
		httpreq.helloooo();
		
		try {
            		System.out.println("Testing 1 - Send Http GET request");
            		httpreq.sendGET_home();

            		System.out.println("Testing 2 - Send Http POST request");
            		httpreq.sendPOST_save_course();
        	} finally {
            		httpreq.close();
        	}
		/*System.out.println("Hello World!!!");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
            		HttpGet request = new HttpGet("http://127.0.0.1:8080/");

            		// add request headers
            		request.addHeader("custom-key", "mkyong");
            		request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            		CloseableHttpResponse response = httpClient.execute(request);

            		try {
                		// Get HttpResponse Status
                		System.out.println(response.getProtocolVersion());              // HTTP/1.1
                		System.out.println(response.getStatusLine().getStatusCode());   // 200
                		System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                		System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                		HttpEntity entity = response.getEntity();
                		if (entity != null) {
                    			// return it as a String
                    			String result = EntityUtils.toString(entity);
                    			System.out.println(result);
                		}
                		System.out.println("POST SENT");
            		} finally {
                		response.close();
            		}
        	} finally {
            		httpClient.close();
        	}*/
    	}
    	/*
    	private void sendGet() throws Exception {

        	HttpGet request = new HttpGet("http://127.0.0.1:8080/");

        	// add request headers
        	request.addHeader("custom-key", "mkyong");
        	request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        	try (CloseableHttpResponse response = httpClient.execute(request)) {
	
            		// Get HttpResponse Status
            		System.out.println(response.getStatusLine().toString());

            		HttpEntity entity = response.getEntity();
            		Header headers = entity.getContentType();
            		System.out.println(headers);

            		if (entity != null) {
                		// return it as a String
                		String result = EntityUtils.toString(entity);
                		System.out.println(result);
            		}
        	}
    	}*/
}
