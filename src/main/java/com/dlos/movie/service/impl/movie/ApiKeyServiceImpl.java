package com.dlos.movie.service.impl.movie;

import com.dlos.movie.service.movie.ApiKeyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
@Component
@Qualifier("ApiKeyServiceImpl")
public class ApiKeyServiceImpl implements ApiKeyService
{
	private final String API_KEY_FILE = "api.key";

	private  String _apiKey = "";

	private Boolean _apiKeyLoaded = false;

	public ApiKeyServiceImpl()
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(API_KEY_FILE)))
		{
			_apiKey = reader.readLine();

			if ((_apiKey == null) || _apiKey.isEmpty())
			{
				return;
			}

			_apiKeyLoaded = true;
		} catch (FileNotFoundException e)
		{
			System.out.println("API Key File Not Found");
		} catch (IOException e)
		{
			System.out.println("An IO Exception occurred: " + e);
		} catch (Exception e)
		{
			System.out.println("An unknown Exception occurred: " + e);
		}
	}

	public String getKey() { return _apiKeyLoaded ? _apiKey : null; }
}
