package com.baas.shared;

import com.baas.shared.core.Backlog;
import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.shared.Result;

public class GetBacklogResult implements Result {

	private Backlog backlog;

	public GetBacklogResult(Backlog backlog) {
		this.backlog = backlog;
	}

	protected GetBacklogResult() {
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
		GetBacklogResult other = (GetBacklogResult) obj;
		if (backlog == null) {
			if (other.backlog != null)
				return false;
		} else if (!backlog.equals(other.backlog))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = (hashCode * 37) + (backlog == null ? 1 : backlog.hashCode());
		return hashCode;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	@Override
	public String toString() {
		return "GetStoryResult[" + backlog + "]";
	}
}
