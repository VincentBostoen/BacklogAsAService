package com.baas.shared.dispatch;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;

@GenDispatch(isSecure=false)
public class DeleteBacklog {
	  @In(1)
	  long backlogId;
}
