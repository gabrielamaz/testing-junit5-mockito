package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Test find all visits")
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> foundVisitSet = new HashSet<>();
        foundVisitSet.add(visit);
        when(visitRepository.findAll()).thenReturn(foundVisitSet);
        Set<Visit> visitSet = service.findAll();
        assertNotNull(visitSet);
        assertEquals(visitSet.size(), 1);
        verify(visitRepository).findAll();
    }

    @Test
    void findALlBdd() {
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);

        //when
        service.findAll();

        then(visitRepository).should().findAll();

    }

    @Test
    @DisplayName("Test find visit by id")
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertNotNull(foundVisit);
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void findByIdBdd() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        service.findById(1L);

        //then
        then(visitRepository).should().findById(anyLong());
    }

    @Test
    @DisplayName("Test save visit")
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(visit);
        assertNotNull(savedVisit);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void saveBdd() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        service.save(visit);

        //then
        then(visitRepository).should().save(any(Visit.class));
    }

    @Test
    @DisplayName("Test Delete visit object")
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteBdd() {
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    @DisplayName("Test Delete visit by id")
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }

    @Test
    void deleteByIdBdd() {
        //given

        //when
        service.deleteById(1L);

        //then
        then(visitRepository).should().deleteById(anyLong());
    }
}