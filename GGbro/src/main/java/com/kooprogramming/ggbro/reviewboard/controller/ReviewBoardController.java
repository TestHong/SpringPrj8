package com.kooprogramming.ggbro.reviewboard.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardUploadFileVO;
import com.kooprogramming.ggbro.reviewboard.domain.ReviewBoardVO;
import com.kooprogramming.ggbro.reviewboard.service.IReviewBoardService;

@Controller
@RequestMapping("/review-board")
public class ReviewBoardController {

	public static final Logger logger = LoggerFactory.getLogger(ReviewBoardController.class);

	@Autowired
	private IReviewBoardService boardService;

	@GetMapping("/write")
	public String write() {		
		return "review-board/write";
	}

	@PostMapping("/write")
	public String write(ReviewBoardVO article) {	
		logger.info("/review-board/write -> POST : " + article);

		try {
			MultipartFile mfile = article.getFile();

			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/review-board/write : " + mfile.getOriginalFilename());
				ReviewBoardUploadFileVO file = new ReviewBoardUploadFileVO();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/review-board/write : " + file);
				boardService.insertArticle(article, file);
			} else {
				boardService.insertArticle(article);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/review-board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<ReviewBoardVO> list = boardService.selectArticleList();
		logger.info(list.toString());
		model.addAttribute("articles", list);
		return "review-board/list";
	}
	
	@RequestMapping("/file/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId) {
		ReviewBoardUploadFileVO file = boardService.getFile(fileId);
		logger.info("getFile " + file.toString());
		final HttpHeaders headers = new HttpHeaders();
		String[] mtypes = file.getFileContentType().split("/");
		headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
		headers.setContentLength(file.getFileSize());
		headers.setContentDispositionFormData("attachment", file.getFileName());
		
		return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
	}

}









