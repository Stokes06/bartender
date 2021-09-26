package fr.norsys.dojo.bartender;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


class GameTest {

    @Test
    void test() {

        List<String> messages = new ArrayList<>();
        Queue<String> input = new ArrayDeque<>();
        input.add("juice");
        input.add("nothing");

        new Game(messages::add, input::poll).run();

        messages.forEach(System.out::println);

    }
}