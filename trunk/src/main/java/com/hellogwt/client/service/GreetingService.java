package com.hellogwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hellogwt.shared.domain.Greeting;

import java.util.List;

@RemoteServiceRelativePath("springGwtServices/greetingService")
public interface GreetingService extends RemoteService {

    Greeting getGreeting(String text);

    void addGreeting(String author, String text);

    void updateGreeting(String author, String text);

    void deleteGreeting(String text);

    List<Greeting> getGreetings();
}