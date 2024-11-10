package com.dlos.movie.service.impl.movie;

import com.dlos.movie.service.movie.ApiKeyService;
import com.dlos.movie.service.movie.ResourceFileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Component
@Qualifier("ApiKeyServiceImpl")
public class ApiKeyServiceImpl implements ApiKeyService
{
	private final String _apiKey;

	private Boolean _apiKeyLoaded = false;

	public ApiKeyServiceImpl()
	{
		final String API_KEY_FILE = "api.key";
		ResourceFileService fileService = new ResourceFileServiceImpl();
		File apiKeyFile = fileService.getFile(API_KEY_FILE);

		try (BufferedReader reader = new BufferedReader(new FileReader(apiKeyFile)))
		{
			// Only interested in the first line.
			_apiKey = reader.readLine();

			if ((_apiKey == null) || _apiKey.isEmpty())
			{
				return;
			}

			_apiKeyLoaded = true;
		} catch (FileNotFoundException e)
		{
			System.out.println("API Key File (" + API_KEY_FILE + ") Not Found");
			throw new RuntimeException("Something went wrong", e);
		} catch (IOException e)
		{
			System.out.println("An IO Exception occurred: " + e);
			throw new RuntimeException("Something went wrong", e);
		} catch (Exception e)
		{
			System.out.println("An unknown Exception occurred: " + e);
			throw new RuntimeException("Something went wrong", e);
		}
	}

	public String getKey() { return _apiKeyLoaded ? _apiKey : null; }
}
