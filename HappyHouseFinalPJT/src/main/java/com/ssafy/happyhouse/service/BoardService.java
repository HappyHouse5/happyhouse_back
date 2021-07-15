package com.ssafy.happyhouse.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.board.BoardDto;
import com.ssafy.happyhouse.dto.board.BoardParamDto;
import com.ssafy.happyhouse.dto.board.BoardResultDto;

public interface BoardService {
	
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	public BoardResultDto boardDelete(int boardId, HttpSession session);
	
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);

	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);

	public BoardResultDto boardList(BoardParamDto boardParamDto);
	
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);

}
