package com.baas.shared;

import com.gwtplatform.dispatch.shared.Action;

public class GetBacklogListAction implements Action<GetBacklogListResult> { 

  public GetBacklogListAction() {
    // Possibly for serialization.
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "GetBacklogList";
  }

  @Override
  public boolean isSecured() {
    return false;
  }

  @Override
  public String toString() {
    return "GetBacklogListAction["
    + "]";
  }
}
