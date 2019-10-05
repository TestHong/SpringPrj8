package com.kooprogramming.ggbro.reviewboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="fileData")
public class ReviewBoardUploadFileVO {
	
	private int fileId;
	private int boardId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private byte[] fileData;

}
