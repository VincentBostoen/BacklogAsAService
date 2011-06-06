package com.baas.client.gin;

import com.baas.client.presenter.BacklogPresenter;
import com.baas.client.presenter.HomePresenter;
import com.baas.client.presenter.MainPagePresenter;
import com.baas.client.presenter.MenuPresenter;
import com.baas.client.presenter.ScrumBoardPresenter;
import com.baas.client.presenter.UserStoriesPresenter;
import com.baas.client.presenter.UserStoryPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

@GinModules({DispatchAsyncModule.class, BaasModule.class })
public interface BaasGinjector extends Ginjector {
	PlaceManager getPlaceManager();
	EventBus getEventBus();
	ProxyFailureHandler getProxyFailureHandler();
	
	Provider<MainPagePresenter> getMainPagePresenter();
	Provider<MenuPresenter> getMenuPresenter();
	AsyncProvider<HomePresenter> getHomePresenter();
	AsyncProvider<BacklogPresenter> getBacklogPresenter();
	AsyncProvider<UserStoryPresenter> getUserStoryPresenter();
	AsyncProvider<UserStoriesPresenter> getUserStoriesPresenter();
	AsyncProvider<ScrumBoardPresenter> getScrumBoardPresenter();
}