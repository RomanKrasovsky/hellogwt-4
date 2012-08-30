package com.hellogwt.server.service;

import com.hellogwt.client.service.GreetingService;
import com.hellogwt.server.persistence.GreetingMapper;
import com.hellogwt.shared.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingMapper greetingMapper;

    @Override
    public Greeting getGreeting(String text) {
        return greetingMapper.getGreeting(text);
    }

    @Override
    public void addGreeting(String author, String text) {
        greetingMapper.addGreeting(author, text);
    }

    @Override
    public void updateGreeting(String author, String text) {
        greetingMapper.updateGreeting(author, text);
    }

    @Override
    public void deleteGreeting(String text) {
        greetingMapper.deleteGreeting(text);
    }

    @Override
    public List<Greeting> getGreetings() {
        return greetingMapper.getGreetings();
    }
}