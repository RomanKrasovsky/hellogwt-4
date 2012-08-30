package com.hellogwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hellogwt.shared.domain.Greeting;

import java.util.List;

public interface GreetingServiceAsync {

    void getGreeting(String text, AsyncCallback<Greeting> async);

    void addGreeting(String author, String text, AsyncCallback<Void> async);

    void updateGreeting(String author, String text, AsyncCallback<Void> async);

    void deleteGreeting(String text, AsyncCallback<Void> async);

    void getGreetings(AsyncCallback<List<Greeting>> async);
}