package tool;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	private List<Integer> jpOriPageList = new ArrayList<Integer>();
	private List<Integer> chOriPageList = new ArrayList<Integer>();
	private int jpOriIndex = 0;
	private int chOriIndex = 0;
	private int jpOriPagesSum = 0;
	private int chOriPagesSum = 0;
	
	public Pagination() {
		
	}
	
	public void process() {
		this.jpOriPageList = getListNum(this.jpOriIndex, this.jpOriPagesSum);
		this.chOriPageList = getListNum(this.chOriIndex, this.chOriPagesSum);
	}
	
	private List<Integer> getListNum(int currentIndex, int pagesSum) {
		List<Integer> list = new ArrayList<Integer>();
		if (currentIndex == 0) {
			for (int i = 1; i <= 10; i++) {
				if (i <= pagesSum) {
					list.add(i);
				} else {
					break;
				}
			}
			return list;
		} else if (currentIndex % 10 == 0) {
			int newNum = currentIndex - 10;
			int result = getResult(newNum);
			for (int i = 1; i <= 10; i++) {
				if (i + result <= pagesSum) {
					list.add(i + result);
				} else {
					break;
				}
			}
			return list;
		} else {
			int result = getResult(currentIndex);
			for (int i = 1; i <= 10; i++) {
				if (i + result <= pagesSum) {
					list.add(i + result);
				} else {
					break;
				}
			}
			return list;
		}
	}
	
	private int getResult(int num) {
		if (num == 0) {
			return 0;
		} else {
			String numStr = String.valueOf(num);
			int len = numStr.length();
			//1001  part -> 100
			String part = numStr.substring(0, len - 1);
			String end = part + "0";
			int result = Integer.parseInt(end);
			return result;
		}
	}
	
	public static void main(String[] args) {
		Pagination n = new Pagination();
		int idx = 20;
		int pages = 100;
		List<Integer> as = n.getListNum(idx, pages);
		for (Integer d : as) {
			System.out.print(d + " ");
		}
	}
	
	public Integer getChFirstPage() {
		if (this.chOriPagesSum > 9) {
			return this.chOriPagesSum - 9;
		} else {
			return 1;
		}
	}
	
	public Integer getJpFirstPage() {
		if (this.jpOriPagesSum > 9) {
			return this.jpOriPagesSum - 9;
		} else {
			return 1;
		}
	}
	
	public List<Integer> getJpOriPageList() {
		return this.jpOriPageList;
	}

	public List<Integer> getChOriPageList() {
		return this.chOriPageList;
	}

	public void setJpOriIndex(int i) {
		this.jpOriIndex = i;
	}

	public void setChOriIndex(int i) {
		this.chOriIndex = i;
	}
	
	public void setJpOriPagesSum(int i) {
		this.jpOriPagesSum = i;
	}
	
	public void setChOriPagesSum(int j) {
		this.chOriPagesSum = j;
	}
}
