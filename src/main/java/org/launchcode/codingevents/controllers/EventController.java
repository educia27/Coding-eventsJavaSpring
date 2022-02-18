package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("events")
public class EventController {

//    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
//        HashMap<String, String> events = new HashMap<>();
//        events.put("Python Workshop", "Event for python learning");
//        events.put("Java Uni Course", "Class for Java Learning");
//        events.put("Gradle & Maven", "Event for Build Tools learning");
//        events.put("Groovy, PHP", "Diving deeper into new languages");
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";

    }
    // lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";

    }
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        // controller code will go here
        Event eventToEdit = EventData.getByID(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + "(id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title);
        return "events/edit";
    }
    @PostMapping("edit")  // @ModelAttribute Event newEvent
    public String processEditForm(int eventId, String name, String description) {
        // controller code will go here
        Event eventToEdit = EventData.getByID(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:";
    }
}
