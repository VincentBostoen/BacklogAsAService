package com.baas.shared.dispatch;

import java.util.List;

import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure=false)
public class GetStoriesFromBacklog {

	@In(1)
	long backlogId;
	
	@Out(1)
	List<UserStory> stories;
}
