package com.perso.baas.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.perso.baas.client.place.DefaultPlace;
import com.perso.baas.client.place.MyPlaceManager;
import com.perso.baas.client.place.PlaceTokens;
import com.perso.baas.client.presenter.BacklogPresenter;
import com.perso.baas.client.presenter.HomePresenter;
import com.perso.baas.client.presenter.MainPagePresenter;
import com.perso.baas.client.presenter.MenuPresenter;
import com.perso.baas.client.presenter.ScrumBoardPresenter;
import com.perso.baas.client.presenter.UserStoriesPresenter;
import com.perso.baas.client.presenter.UserStoryPresenter;
import com.perso.baas.client.view.BacklogView;
import com.perso.baas.client.view.HomeView;
import com.perso.baas.client.view.MainPageView;
import com.perso.baas.client.view.MenuView;
import com.perso.baas.client.view.ScrumBoardView;
import com.perso.baas.client.view.UserStoriesView;
import com.perso.baas.client.view.UserStoryView;

public class BaasModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		//Singletons
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);

		//Contants
		bindConstant().annotatedWith(DefaultPlace.class).to(PlaceTokens.getHome());
		
		// Presenters
		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class, MainPageView.class, MainPagePresenter.MyProxy.class);
		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
		bindPresenter(MenuPresenter.class, MenuPresenter.MyView.class, MenuView.class, MenuPresenter.MyProxy.class);
		bindPresenter(BacklogPresenter.class, BacklogPresenter.MyView.class, BacklogView.class, BacklogPresenter.MyProxy.class);
		bindPresenter(UserStoryPresenter.class, UserStoryPresenter.MyView.class, UserStoryView.class, UserStoryPresenter.MyProxy.class);
		bindPresenter(UserStoriesPresenter.class, UserStoriesPresenter.MyView.class, UserStoriesView.class, UserStoriesPresenter.MyProxy.class);
		bindPresenter(ScrumBoardPresenter.class, ScrumBoardPresenter.MyView.class, ScrumBoardView.class, ScrumBoardPresenter.MyProxy.class);
	}
}