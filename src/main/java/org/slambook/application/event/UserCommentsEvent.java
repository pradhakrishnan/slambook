package org.slambook.application.event;

public interface UserCommentsEvent extends SlamBookEvent {

	public String commentBy();
	
	public String commentsOn();
	
	public String commentsData();
}
