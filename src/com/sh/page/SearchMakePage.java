package com.sh.page;

public class SearchMakePage {
	private int perPage;
	private int curPage;
	private Search search;
	
	
	public SearchMakePage(int curPage, String kind, String search) {//curPage는 파라미터로 받는 값을 넣어준다. 
		this(curPage, 10, kind, search);
	}
	
	public SearchMakePage(int curPage, int perPage, String kind, String search) {
		this.perPage = perPage;
		this.curPage = curPage;
		this.search = new Search();
		this.search.setKind(kind);
		this.search.setSearch(search);
	}
	//row
	public SearchRow makeRow() {
		int startRow = (curPage -1)*perPage+1;
		int lastRow = curPage * perPage;
		SearchRow searchRow = new SearchRow();
		searchRow.setStartRow(startRow);
		searchRow.setLastRow(lastRow);
		searchRow.setSearch(search);
		
		return searchRow;
	}
	//page
	public SearchPager makePage(int totalCount) {
		//2. totalPage
		int totalPage = totalCount/perPage;
		if(totalCount%perPage!=0) {
			totalPage++;
		}
		
		//3.totalBlock
		int perBlock=5;
		int totalBlock = totalPage/perBlock;
		if(totalPage%perBlock!=0) {
			totalBlock++;
		}
		
		//4. curPage로 curBlock
		int curBlock = curPage/perBlock;
		if(curPage%perBlock!=0) {
			curBlock++;
		}
		
		//5. startNum, lastNum구하기
		int startNum = (curBlock-1)*perBlock+1;
		int lastNum = curBlock*perBlock;
		
		//6. 현재 블럭이 마지막 블럭일 때
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
		SearchPager searchPager = new SearchPager();
		searchPager.setCurPage(curPage);
		searchPager.setCurBlock(curBlock);
		searchPager.setLastNum(lastNum);
		searchPager.setStartNum(startNum);
		searchPager.setTotalBlock(totalBlock);
		searchPager.setSearch(search);
		
		return searchPager;
	}
	
	
}
