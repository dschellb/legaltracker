package com.javaapps.legaltracker.utils;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.client.ResponseHandler;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpRequest;

import com.javaapps.legaltracker.upload.HttpClientFactory;

public class MockHttpClientFactory implements HttpClientFactory{
	private int counter=0;
	private ProtocolVersion protocolVersion;
	private int statusCodes[];
	private String reason;
		
	public MockHttpClientFactory(ProtocolVersion protocolVersion, int statusCodes[], String reason){
		this.protocolVersion=protocolVersion;
		this.statusCodes=statusCodes;
		this.reason=reason;
	}
	
	private HttpResponse getHttpResponse(){
		HttpResponse httpResponse=new BasicHttpResponse(protocolVersion,statusCodes[0],reason);
		return httpResponse;
	}
	
	@Override
	public HttpClient getHttpClient() {
		return new HttpClient(){

			@Override
			public HttpResponse execute(HttpUriRequest arg0)
					throws IOException, ClientProtocolException {
				return getHttpResponse();
			}

			@Override
			public HttpResponse execute(HttpUriRequest arg0,
					HttpContext arg1) throws IOException,
					ClientProtocolException {
				return getHttpResponse();
			}

			@Override
			public HttpResponse execute(HttpHost arg0, HttpRequest arg1)
					throws IOException, ClientProtocolException {
				return getHttpResponse();
			}

			@Override
			public <T> T execute(HttpUriRequest arg0,
					ResponseHandler<? extends T> arg1) throws IOException,
					ClientProtocolException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpResponse execute(HttpHost arg0, HttpRequest arg1,
					HttpContext arg2) throws IOException,
					ClientProtocolException {
				return getHttpResponse();
			}

			@Override
			public <T> T execute(HttpUriRequest arg0,
					ResponseHandler<? extends T> arg1, HttpContext arg2)
					throws IOException, ClientProtocolException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T execute(HttpHost arg0, HttpRequest arg1,
					ResponseHandler<? extends T> arg2) throws IOException,
					ClientProtocolException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T execute(HttpHost arg0, HttpRequest arg1,
					ResponseHandler<? extends T> arg2, HttpContext arg3)
					throws IOException, ClientProtocolException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ClientConnectionManager getConnectionManager() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpParams getParams() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	
}