package com.acciona.tweetsmanager.service;

public interface TwitterClientService {

	public void authenticate();

	public void streamingApi(Integer followers);

	public void closeStreamingApi();
}
