package org.slambook.application.event;

public interface UserMessageLikeEvent extends SlamBookEvent {
	public String likeBy();

	public String likesOn();

	public int totalLikes();
}
