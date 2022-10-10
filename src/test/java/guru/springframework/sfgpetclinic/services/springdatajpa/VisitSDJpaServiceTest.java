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
    @DisplayName("Test find visit by id")
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertNotNull(foundVisit);
        verify(visitRepository).findById(anyLong());
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
    @DisplayName("Test Delete visit object")
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    @DisplayName("Test Delete visit by id")
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}