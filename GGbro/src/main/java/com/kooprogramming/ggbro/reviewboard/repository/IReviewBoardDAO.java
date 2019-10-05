package com.kooprogramming.ggbro.reviewboard.repository;

import java.util.List;

import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardUploadFileVO;
import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardVO;

public interface IReviewBoardDAO {

	void insertArticle(ReviewBoardVO board);
	void insertFileData(ReviewBoardUploadFileVO file);
	
	int selectMaxArticleNo();
	int selectMaxFileId();
	
	List<ReviewBoardVO> selectArticleList();
	ReviewBoardVO selectArticle(int boardId);
	ReviewBoardUploadFileVO getFile(int fileId);
	
}
