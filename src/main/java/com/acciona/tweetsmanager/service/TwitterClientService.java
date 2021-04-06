package com.acciona.tweetsmanager.service;

public interface TwitterClientService {
	
	public void authenticate();
	
	public void streamingApi();
	
	public void closeStreamingApi();
}
