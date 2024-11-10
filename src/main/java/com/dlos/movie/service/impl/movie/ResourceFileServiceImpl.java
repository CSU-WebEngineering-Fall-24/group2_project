package com.dlos.movie.service.impl.movie;

import com.dlos.movie.service.movie.ResourceFileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.File;

@Service
@Component
@Qualifier("ResourceFileServiceImpl")
public class ResourceFileServiceImpl implements ResourceFileService
{
	@Override
	public File getFile(String filename)
	{
		ClassLoader classLoader = getClass().getClassLoader();
		URL fileResource = classLoader.getResource(filename);
		if (fileResource == null)
		{
			throw new RuntimeException("File (" + filename + ") not found");
		}

		File file;
		try
		{
			file = new File(fileResource.toURI());
		}
		catch (URISyntaxException e)
		{
			throw new RuntimeException("An Exception occurred while trying to load file '" + filename + "'", e);
		}

		return file;
	}
}
