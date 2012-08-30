package com.hellogwt.server.persistence;

import com.hellogwt.shared.domain.Greeting;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GreetingMapper {

    @Select("SELECT * FROM greetings WHERE text = #{text}")
    Greeting getGreeting(@Param("text") String text);

    @Select("INSERT INTO greetings (author, text) VALUES (#{author}, #{text})")
    void addGreeting(@Param("author") String author, @Param("text") String text);

    @Select("UPDATE greetings SET author = #{author} where text = #{text}")
    void updateGreeting(@Param("author") String author, @Param("text") String text);

    @Select("DELETE FROM greetings WHERE text = #{text}")
    void deleteGreeting(@Param("text") String text);

    @Select("SELECT * FROM greetings")
    List<Greeting> getGreetings();
}
