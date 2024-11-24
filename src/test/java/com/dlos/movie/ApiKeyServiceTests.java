package com.dlos.movie;

import com.dlos.movie.service.impl.movie.ApiKeyServiceImpl;
import com.dlos.movie.service.movie.ApiKeyService;
import com.dlos.movie.service.movie.BufferedReaderProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
public class ApiKeyServiceTests {
	@Test
	void TestSuccessfullyReadsApiKeyFile() throws IOException {
		String expectedValue = "fakeapikey";

		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenReturn(expectedValue);

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);

		ApiKeyService keyService = new ApiKeyServiceImpl(readerProvider);
		String apiKey = keyService.getKey();

		Assertions.assertEquals(expectedValue, apiKey);
	}

	@Test
	void TestFailsToFindFile() throws Exception {
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenThrow(new FileNotFoundException());

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);

		RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
			new ApiKeyServiceImpl(readerProvider);
		});

		Assertions.assertInstanceOf(FileNotFoundException.class, ex.getCause());
	}

	@Test
	void TestFailsToReadFile() throws Exception {
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenThrow(new IOException());

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);

		RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
			new ApiKeyServiceImpl(readerProvider);
		});

		Assertions.assertInstanceOf(IOException.class, ex.getCause());
	}
}
