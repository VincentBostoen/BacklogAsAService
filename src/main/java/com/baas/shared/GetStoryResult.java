package com.baas.shared;

import com.gwtplatform.dispatch.shared.Result;

public class GetStoryResult implements Result {

	private UserStory story;

	public GetStoryResult(UserStory story) {
		this.story = story;
	}

	protected GetStoryResult() {
		// Possibly for serialization.
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetStoryResult other = (GetStoryResult) obj;
		if (story == null) {
			if (other.story != null)
				return false;
		} else if (!story.equals(other.story))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = (hashCode * 37) + (story == null ? 1 : story.hashCode());
		return hashCode;
	}

	public UserStory getStory() {
		return story;
	}

	@Override
	public String toString() {
		return "GetStoryResult[" + story + "]";
	}
}
