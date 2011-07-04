package com.baas.shared.dispatch;

import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure=false)
public class GetStory {

	@In(1)
	long storyId;
	
	@Out(1)
	UserStory story;
}
