package com.baas.shared;

import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.shared.Result;

public class UpdateStoryResult implements Result {

	private UserStory story;

	public UpdateStoryResult(UserStory story) {
		this.story = story;
	}

	protected UpdateStoryResult() {
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
		UpdateStoryResult other = (UpdateStoryResult) obj;
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
		return "UpdateStoryResult[" + story + "]";
	}
}
