package org.kh.websocket.model.dao;

import java.util.List;

import org.kh.websocket.model.vo.ChatDto;
import org.kh.websocket.model.vo.ChatMemberDto;

public interface ProjectChatDao {

	
	
	//중복확인 
	public ChatDto checkRoom(String name)throws Exception;
	
	//방만들기 
	public void createChatRoom(ChatDto dto)throws Exception;
	
	//방 정보 리스트
	public List<ChatDto> getRoomList()throws Exception;
	
	// 현재 유저 지정한 방에 입장
	public void addRoomMember(ChatMemberDto mem)throws Exception;
	
	// 현재 유저가 어떤 방에 있는지 확인
	public ChatMemberDto getRoomMember(ChatMemberDto mem)throws Exception;
	
	//같은 방에 존재하는 사람정보 모두가져오기
	public List<ChatMemberDto> sameRoomList(ChatMemberDto mem)throws Exception;
	
	//유저의 방정보 수정
	public void updateRoomMember(ChatMemberDto mem)throws Exception;
	
	//유저의 방 정보 삭제
	public void deleteRoomMember(ChatMemberDto mem)throws Exception;
	
	//remainCount  증가
	public void updateChatCountInc(ChatDto dto)throws Exception;
	
	//remainCount  감소
	public void updateChatCountDec(ChatDto dto)throws Exception;
	
	//방 삭제
	public void deleteChat()throws Exception;
	
	//방 검색
	public List<ChatDto>searchRoomList(String name)throws Exception;
	
	
	
}
