package com.baas.shared.dispatch;

import com.baas.shared.core.Backlog;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure=false)
public class GetBacklog {
	@In(1)
	long backlogId;
	
	@Out(1)
	Backlog backlog;
}
