package com.dlos.movie;

import com.dlos.movie.domain.Member;
import com.dlos.movie.service.impl.movie.MemberServiceImpl;
import com.dlos.movie.service.movie.BufferedReaderProvider;
import com.dlos.movie.service.movie.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;

@SpringBootTest
public class MemberServiceTests
{
	private final String json = """
			[
			\t{
			\t  "id": "d",
			\t  "name": "Alexis Davidson",
			\t  "shortBio": "",
			\t  "longBio": "",
			\t  "shortBioImageUrl": "",
			\t  "longBioImageUrl": ""
			\t},
			\t{
			\t  "id": "l",
			\t  "name": "Nam Luu",
			\t  "shortBio": "",
			\t  "longBio": "",
			\t  "shortBioImageUrl": "",
			\t  "longBioImageUrl": ""
			\t},
			\t{
			\t  "id": "o",
			\t  "name": "Justin Owens",
			\t  "shortBio": "",
			\t  "longBio": "",
			\t  "shortBioImageUrl": "",
			\t  "longBioImageUrl": ""
			\t},
			\t{
			\t  "id": "s",
			\t  "name": "Michael K. Sretenović",
			\t  "shortBio": "I am currently working on my Masters in Computer Science.  This is my last class and I'm very excited to graduate.  I started my career life as a draftsman in architecture.  In 2002, I went back to get my Bachelor's in Computer Science.  While working full time, I went to school part time and graduated in 2009.  I got a job at CallCopy in 2010 and have been a software engineer ever since.  I work at NICE, which is the number one vendor in Call Center as a Service (CCaaS).",
			\t  "longBio": "I am currently working on my Masters in Computer Science.  This is my last class and I'm very excited to graduate.  I started my career life as a draftsman in architecture.  In 2002, I went back to get my Bachelor's in Computer Science.  While working full time, I went to school part time and graduated in 2009.  I got a job at CallCopy in 2010 and have been a software engineer ever since.  I work at NICE, which is the number one vendor in Call Center as a Service (CCaaS).  I work on the Media Services team; which is responsible for moving RTP data and audio through our system.  The work on this team is very interesting as most of the processing as close to real time as possible.  The data that can be handled in a less time restricted manner is one of the projects I am working on.  Essentially, anything that is not time sensitive is being moved to another process without adding any additional delays.  This has been quite challenging and I have had a chance to learn quite a bit.",
			\t  "shortBioImageUrl": "https://avatars.githubusercontent.com/u/178439261?v=4",
			\t  "longBioImageUrl": "https://avatars.githubusercontent.com/u/178439261?v=4"
			\t}
			]
			""";

	@Test
	public void SuccessfullyReadsMemberFileTest() throws IOException
	{
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenReturn(json).thenReturn(null);

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);
		MemberService memberService = new MemberServiceImpl(readerProvider);
		Member[] members = memberService.getMembers();

		Assertions.assertEquals(4, members.length);
	}

	@Test
	public void SuccessfullyReadsMemberTest() throws IOException
	{
		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenReturn(json).thenReturn(null);

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);
		MemberService memberService = new MemberServiceImpl(readerProvider);
		Member member = memberService.getMember("s");

		Assertions.assertEquals("S", member.getId());
	}
}
