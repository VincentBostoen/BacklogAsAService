package com.baas.shared.dispatch;

import java.util.Set;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;

@GenDispatch(isSecure=false)
public class DeleteUserStories {

	@In(1)
	Set<Long> userStoriesKeys; 
}
