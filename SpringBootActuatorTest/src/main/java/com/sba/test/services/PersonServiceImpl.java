package com.sba.test.services;

import com.sba.test.db.entities.PersonEntity;
import com.sba.test.db.repositories.PersonRepository;
import com.sba.test.web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PersonServiceImpl implements PersonService
{
    private final PersonRepository personRepo;

    @Autowired
    PersonServiceImpl(PersonRepository personRepo)
    {
        this.personRepo = personRepo;
    }

    /**
     * Convert {@link Person} Object to {@link PersonEntity} object Set the
     * personId if present else return object with id null/0
     */
    private final Function<Person, PersonEntity> personToEntity = person ->
    {
        if (person.getPersonId() == 0)
        {
            return new PersonEntity(person.getFirstName(), person.getLastName(), person.getEmail());
        }
        else
        {
            return new PersonEntity(person.getPersonId(), person.getFirstName(), person.getLastName(), person.getEmail());
        }
    };

    /**
     * Convert {@link PersonEntity} to {@link Person} object
     */
    private final Function<Double, Object> entityToPerson = entity -> new Person(entity.getPersonId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());


    /**
     * If record is present then convert the record else return the empty {@link Optional}
     */
    @Override
    public Optional<Person> getPersonById(int personId)
    {
        return personRepo.findById(personId).map(s ->  entityToPerson.apply(s));
    }

    @Override
    public List<Person> getAllPersons()
    {
        return personRepo.findAll().parallelStream()
                .map(entityToPerson)
                .collect(Collectors.toList());
    }

    @Override
    public boolean removePerson(int personId)
    {
        personRepo.deleteById(personId);
        return true;
    }

    @Override
    public Optional<Person> saveUpdatePerson(Person person)
    {
        if (person.getPersonId() == 0 || personRepo.existsById(person.getPersonId()))
        {
            Double entity = personRepo.save(personToEntity.apply(person));
            return Optional.of(entityToPerson.apply(entity));
        }
        else
        {
            return Optional.empty();
        }
    }
}