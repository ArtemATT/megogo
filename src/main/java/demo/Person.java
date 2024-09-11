package demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Person(String name, int age) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ((Person) o).name().equals(this.name());
    }

    public static Map<Age, List<Person>> getPersonMapping(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getPersonAge, Collectors.toList()));
    }

    private static Age getPersonAge(Person person) {
        int age = person.age();
        if (age >= 14 && age < 30) return Age.YOUNG;
        if (age >= 30 && age < 60) return Age.ADULT;
        if (age >= 60) return Age.OLD;
        else return Age.UNDEFINED;
    }
}
