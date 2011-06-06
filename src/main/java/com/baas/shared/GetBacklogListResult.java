package com.baas.shared;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

public class GetBacklogListResult implements Result { 

  private List<Backlog> backlogs;

  public GetBacklogListResult(List<Backlog> backlogs) {
    this.backlogs = backlogs;
  }

  protected GetBacklogListResult() {
    // Possibly for serialization.
  }

  public List<Backlog> getBacklogs() {
    return backlogs;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GetBacklogListResult other = (GetBacklogListResult) obj;
    if (backlogs == null) {
      if (other.backlogs != null)
        return false;
    } else if (!backlogs.equals(other.backlogs))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (backlogs == null ? 1 : backlogs.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GetBacklogListResult["
                 + backlogs
    + "]";
  }
}
