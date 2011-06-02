package com.baas.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class RevealMenuEvent extends GwtEvent<RevealMenuHandler> {

  private static final Type<RevealMenuHandler> TYPE = new Type<RevealMenuHandler>();
  
  public static Type<RevealMenuHandler> getType() {
      return TYPE;
  }

  public static void fire(HasHandlers source) {
    source.fireEvent(new RevealMenuEvent());
  }
  
  public RevealMenuEvent() {
  }

  @Override
  protected void dispatch(RevealMenuHandler handler) {
    handler.onRevealMenu(this);
  }

  @Override
  public Type<RevealMenuHandler> getAssociatedType() {
    return getType();
  }
}
