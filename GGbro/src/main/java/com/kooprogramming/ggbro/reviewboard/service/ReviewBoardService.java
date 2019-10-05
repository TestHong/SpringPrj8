package com.kooprogramming.ggbro.reviewboard.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardUploadFileVO;
import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardVO;
import com.kooprogramming.ggbro.reviewboard.repository.IReviewBoardDAO;

@Service
public class ReviewBoardService implements IReviewBoardService {

	@Autowired
	IReviewBoardDAO boardDao;
	
	@Transactional
	@Override
	public void insertArticle(ReviewBoardVO article) {
		boardDao.insertArticle(article);
	}

	@Transactional
	@Override
	public void insertArticle(ReviewBoardVO article, ReviewBoardUploadFileVO file) {
		
		boardDao.insertArticle(article);
		
		if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
			article.setBoardId(boardDao.selectMaxArticleNo());
			file.setBoardId(article.getBoardId());
						
			boardDao.insertFileData(file);
		}

	}

	@Override
	public List<ReviewBoardVO> selectArticleList() {
		return boardDao.selectArticleList();
	}

	@Override
	public ReviewBoardVO selectArticle(int boardId) {
		return boardDao.selectArticle(boardId);
	}

	@Override
	public ReviewBoardUploadFileVO getFile(int fileId) {
		
		ReviewBoardUploadFileVO file = boardDao.getFile(fileId);
		
        
		return file	;
	}

}








