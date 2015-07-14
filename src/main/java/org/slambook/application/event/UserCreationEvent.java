package org.slambook.application.event;

public interface UserCreationEvent extends SlamBookEvent {
	public String createdBy();

	public String craetedDt();

	public String userData();
}
