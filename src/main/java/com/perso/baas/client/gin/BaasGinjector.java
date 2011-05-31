package com.perso.baas.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.perso.baas.client.presenter.BacklogPresenter;
import com.perso.baas.client.presenter.HomePresenter;
import com.perso.baas.client.presenter.MainPagePresenter;
import com.perso.baas.client.presenter.MenuPresenter;
import com.perso.baas.client.presenter.ScrumBoardPresenter;
import com.perso.baas.client.presenter.UserStoriesPresenter;
import com.perso.baas.client.presenter.UserStoryPresenter;

@GinModules({ BaasModule.class })
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