package com.kooprogramming.ggbro.commons.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageCreator {

	private Criteria criteria; //페이지번호와 한페이지당 들어갈 게시물 수
	private int articleTotalCount; //DB에 저장된 총 게시물 수
	private int beginPage; //시작 페이지 번호
	private int endPage; //끝페이지 번호
	private boolean prev; //이전 버튼 활성화 여부
	private boolean next; //다음 버튼 활성화 여부
		
	//한 화면에 한번에 보여줄 페이지 수
	private final int displayPageNum = 10;
	
	//URI와 요청파라미터를 쉽게 작성하기 위한 기능
	public String makePageURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.queryParam("page", page)
							.queryParam("countPerPage", criteria.getCountPerPage())
							.build();
		return ucp.toUriString();
	}
	
	public String makeSearchURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("countPerPage", criteria.getCountPerPage())
				.queryParam("condition", ((SearchCriteria)criteria).getCondition())
				.queryParam("keyword", ((SearchCriteria)criteria).getKeyword())
				.build();
		return ucp.toUriString();
	}
	
	
	//화면에 보여질 페이지 관련 데이터 계산 메서드
	private void calcDataOfPage() {
		
		//보정 전 끝 페이지 구하기
		endPage = (int)(Math.ceil(criteria.getPage() 
				/ (double)displayPageNum) * displayPageNum);
		
		//시작 페이지 번호 구하기
		beginPage = (endPage - displayPageNum) + 1;
		
		//끝 페이지 재보정하기
		int temp = (int)(Math.ceil(articleTotalCount 
				/ (double)criteria.getCountPerPage()));
		
		//보정 전 값과 비교 후 재보정 여부 결정
		if(endPage > temp) {
			endPage = temp;
		}
		
		//현재 시작페이지가 1일경우 이전버튼을 false로 만들어 버튼 비활성
		prev = (beginPage == 1) ? false : true;
		
		/*
		 if(beginPage == 1) {
		 	prev = false;
		 } else {
		 	prev = true;
		 }	
		 */
		
		//마지막 페이지인지 확인 후 다음버튼 비활성
		next = (articleTotalCount <= (endPage * criteria.getCountPerPage())) ? false : true;
		
	}
	
	
		
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getArticleTotalCount() {
		return articleTotalCount;
	}
	
	//총 게시물 수를 세팅하면서 페이지 계산로직 메서드 호출
	public void setArticleTotalCount(int articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	
	
}








