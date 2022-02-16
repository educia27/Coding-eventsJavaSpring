package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("events")
public class EventController {

    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
//        HashMap<String, String> events = new HashMap<>();
//        events.put("Python Workshop", "Event for python learning");
//        events.put("Java Uni Course", "Class for Java Learning");
//        events.put("Gradle & Maven", "Event for Build Tools learning");
//        events.put("Groovy, PHP", "Diving deeper into new languages");
        model.addAttribute("title", "All Events");
        model.addAttribute("events", events);
        return "events/index";

    }
    // lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@RequestParam String eventName) {
        events.add(new Event(eventName));
        return "redirect:";
    }
}
