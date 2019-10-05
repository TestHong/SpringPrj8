package com.kooprogramming.ggbro.reviewboard.service;

import java.util.List;

import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardUploadFileVO;
import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardVO;

public interface IReviewBoardService {
	
	void insertArticle(ReviewBoardVO article);
	void insertArticle(ReviewBoardVO article, ReviewBoardUploadFileVO file);
	
	List<ReviewBoardVO> selectArticleList();
	ReviewBoardVO selectArticle(int boardId);
	ReviewBoardUploadFileVO getFile(int fileId);

}
