package com.dlos.movie.service.impl.movie;

import com.dlos.movie.service.movie.BufferedReaderProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Component
@Qualifier("BufferedReaderProviderImpl")
public class BufferedReaderProviderImpl implements BufferedReaderProvider
{
	@Override
	public BufferedReader createBufferedReader(File file) throws FileNotFoundException, IOException
	{
		return new BufferedReader(new FileReader(file));
	}
}
