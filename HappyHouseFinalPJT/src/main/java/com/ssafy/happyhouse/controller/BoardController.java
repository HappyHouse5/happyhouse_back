package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.dto.board.BoardDto;
import com.ssafy.happyhouse.dto.board.BoardParamDto;
import com.ssafy.happyhouse.dto.board.BoardResultDto;
import com.ssafy.happyhouse.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// BoardFileUploadSpringBootMybatis 대비 @CrossOrign 추가 <-- vue cli mode 개발 대응
//@CrossOrigin(
//		origins = "http://localhost:8080", // allowCredentials = "true" 일 경우, orogins="*" 는 X
//		allowCredentials = "true", 
//		allowedHeaders = "*", 
//		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//)
// @CrossOrigin // 이것만으로는 오류 발생 <-- allowCredentials = "true"
@RestController
@Api("Board Controller API V1")
public class BoardController {

	@Autowired
	private BoardService service;
	
	private static final int SUCCESS = 1;
	
	// 게시판 전체 혹은 검색 글 가져오기
	@ApiOperation(value = " 새로운 글을 입력한다. 그리고 그 글의 번호를 반환한다.", response = BoardResultDto.class)
	@GetMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto){
		
		BoardResultDto boardResultDto;
		if( boardParamDto.getSearchWord().isEmpty() ) {
			boardResultDto = service.boardList(boardParamDto);
		}else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		System.out.println(boardResultDto.getList());
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session){
		System.out.println(boardId);
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);
		
		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		
		// 게시글 작성자와 현 사용자가 동일
		System.out.println(session.getAttribute("member"));
		if( ((MemberDto) session.getAttribute("member")).getKeyId() == boardResultDto.getDto().getUserSeq() ) {
			boardResultDto.setOwner(true);
			System.out.println("owner content");
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardInsert(
			BoardDto boardDto, 
			MultipartHttpServletRequest request) {
		
		boardDto.setUserSeq( ((MemberDto) request.getSession().getAttribute("member")).getKeyId());
		BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	@PostMapping(value="/boards/{boardIdd}") 
	public ResponseEntity<BoardResultDto> boardUpdate(
			BoardDto boardDto, 
			MultipartHttpServletRequest request){

		BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);
		boardDto.setUserSeq( ((MemberDto) request.getSession().getAttribute("member")).getKeyId());
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping(value="/boards/delete/{boardId}") 
	public ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value="boardId") int boardId, HttpSession session){
		BoardResultDto boardResultDto = service.boardDelete(boardId, session);
		
		// CORS 이슈
		// 위 @CrossDomain  설정으로 get post 는 문제 없는데, delete 는 문제가 다시 발생  -> Interceptor 거치면서 delete는 오류 발생 -> ExcludePath에 추가
		// 아래의 Header 를 추가하는 코드를 넣었지만 이곳까지 오지 않음. Spring 밖에서 거부?
		// 그럼 Container ??
//		Response to preflight request doesn't pass access control check: The value of the 
//		'Access-Control-Allow-Credentials' header in the response is '' which must be 'true' 
//		when the request's credentials mode is 'include'. 
//		The credentials mode of requests initiated by the XMLHttpRequest is controlled by the withCredentials attribute.
	    
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
}
