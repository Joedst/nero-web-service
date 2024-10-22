package com.demo.nero.controller;

import com.demo.nero.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private List<Person> people = new ArrayList<>();

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        person.setId((long) (people.size() + 1)); // Generera ett ID
        people.add(person);
        return person;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return people.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = people.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setAge(personDetails.getAge());
        }
        return person;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        people.removeIf(p -> p.getId().equals(id));
        return "Person deleted!";
    }
}
