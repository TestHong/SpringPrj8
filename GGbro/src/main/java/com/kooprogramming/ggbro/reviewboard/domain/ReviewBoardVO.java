package com.kooprogramming.ggbro.reviewboard.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReviewBoardVO {
	
	private int boardId;
	private String writer;
	private String title;
	private String content;
	private Timestamp writeDate;
	private int viewCount;
	private MultipartFile file;

	private int fileId;
	private String fileName;
	private long fileSize;
	private String fileContentType;

}
