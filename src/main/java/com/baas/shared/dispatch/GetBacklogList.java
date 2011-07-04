package com.baas.shared.dispatch;

import java.util.List;

import com.baas.shared.core.Backlog;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure=false)
public class GetBacklogList {
	@Out(1)
	List<Backlog> backlogs;
}
