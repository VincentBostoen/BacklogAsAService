package com.baas.shared;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

public class GetStoryListResult implements Result {

	private List<UserStory> stories;

	public GetStoryListResult(List<UserStory> stories) {
		this.stories = stories;
	}

	protected GetStoryListResult() {
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
		GetStoryListResult other = (GetStoryListResult) obj;
		if (stories == null) {
			if (other.stories != null)
				return false;
		} else if (!stories.equals(other.stories))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = (hashCode * 37) + (stories == null ? 1 : stories.hashCode());
		return hashCode;
	}

	public List<UserStory> getStories() {
		return stories;
	}

	@Override
	public String toString() {
		return "GetStoryListResult[" + stories + "]";
	}
}
