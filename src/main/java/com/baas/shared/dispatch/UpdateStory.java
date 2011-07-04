package com.baas.shared.dispatch;

import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure=false)
public class UpdateStory {

	@In(1)
	UserStory story;
	
	@Out(1)
	UserStory updatedStory;
}
