package com.dlos.movie.service.impl.movie;

import com.dlos.movie.domain.Member;
import com.dlos.movie.service.movie.MemberService;
import com.dlos.movie.service.movie.ResourceFileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;

@Service
public class MemberServiceImpl implements MemberService
{
	private static final HashMap<String, Member> _members = new HashMap<>();

	static
	{
		final String MEMBER_FILE = "members.json";
		ResourceFileService fileService = new ResourceFileServiceImpl();
		File memberFile = fileService.getFile(MEMBER_FILE);

		try (BufferedReader reader = new BufferedReader(new FileReader(memberFile)))
		{
			StringBuilder json = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
			{
				json.append(line);
			}

			ObjectMapper mapper = new ObjectMapper();
			Member[] members = mapper.readValue(json.toString(), new TypeReference<Member[]>() {});

			for (Member member : members)
			{
				_members.put(member.getId(), member);
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("Member File (" + MEMBER_FILE + ") not found");
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

	@Override
	public Member getMember(String id)
	{
		if (_members.containsKey(id))
		{
			return _members.get(id);
		}
		return null;
	}

	@Override
	public Member[] getMembers()
	{
		Member[] members = new Member[_members.size()];
		_members.values().toArray(members);
		return members;
	}
}
