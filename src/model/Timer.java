package model;

public class Timer {

	private int secMinute;
	private int firstMinute;
	private int secSecond;
	private int firstSecond;
	
	
	public Timer() {
		secMinute = 0;
		firstMinute = 0;
		secSecond = 0;
		firstSecond = 0;
	}


	public int getSecMinute() {
		return secMinute;
	}


	public void setSecMinute(int secMinute) {
		this.secMinute = secMinute;
	}


	public int getFirstMinute() {
		return firstMinute;
	}


	public void setFirstMinute(int firstMinute) {
		this.firstMinute = firstMinute;
	}


	public int getSecSecond() {
		return secSecond;
	}


	public void setSecSecond(int secSecond) {
		this.secSecond = secSecond;
	}


	public int getFirstSecond() {
		return firstSecond;
	}


	public void setFirstSecond(int firstSecond) {
		this.firstSecond = firstSecond;
	}
	
	public String getTime() {
		return secMinute+""+firstMinute+":"+secSecond+""+firstSecond;
	}

	public void start() {
		if(firstSecond <= 8) {
			firstSecond += 1;
		}else {
			firstSecond = 0;
			if(secSecond < 5) {
				secSecond += 1;
			}else {
				secSecond = 0;
				if(firstMinute <= 8) {
					firstMinute += 1;
				}else {
					firstMinute = 0;
					if(secMinute < 5) {
						secMinute += 1;
					}
				}
			}
		}
	}
	
	
}
