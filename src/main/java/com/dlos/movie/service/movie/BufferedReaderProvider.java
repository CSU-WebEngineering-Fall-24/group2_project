package com.dlos.movie.service.movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface BufferedReaderProvider
{
	BufferedReader createBufferedReader(File file) throws FileNotFoundException, IOException;
}
