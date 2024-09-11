package demo;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        List<Person> people = getPeople(15);
        Map<Age, List<Person>> personMapping = Person.getPersonMapping(people);

        personMapping.forEach((age, person) -> {
            List<String> list = person.stream().map(Person::name).toList();
            System.out.printf("%s: %s\n", age, list);
        });
    }

    private static List<Person> getPeople(int n) {
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = faker.name().fullName();
            int age = faker.number().numberBetween(1, 80);
            Person person = new Person(name, age);
            people.add(person);
        }

        return people;
    }
}
