package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BlocServiceImplMockTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    private final Bloc bloc = new Bloc(1, "bloc1", 10, null, null);

    private final List<Bloc> listBlocs = new ArrayList<>() {{
        add(new Bloc(2, "bloc2", 20, null, null));
        add(new Bloc(3, "bloc3", 30, null, null));
    }};

    @Test
    public void testRetrieveBloc() {
        // Given
        when(blocRepository.findById(anyLong())).thenReturn(Optional.of(bloc));

        // When
        Bloc retrievedBloc = blocService.retrieveBloc(1L);

        // Then
        Assertions.assertNotNull(retrievedBloc);
        Assertions.assertEquals(bloc.getIdBloc(), retrievedBloc.getIdBloc());
    }

    @Test
    public void testAddBloc() {
        // Given
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // When
        Bloc savedBloc = blocService.addBloc(bloc);

        // Then
        Assertions.assertNotNull(savedBloc);
        Assertions.assertEquals(bloc.getNomBloc(), savedBloc.getNomBloc());
    }

    @Test
    public void testGetAllBloc() {
        // Given
        when(blocRepository.findAll()).thenReturn(listBlocs);

        // When
        List<Bloc> result = blocService.retrieveAllBlocs();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(listBlocs.size(), result.size());
    }

//    @Test
//    public void testUpdateBloc() {
//        // Given
//        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));
//        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);
//
//        Bloc updatedDetails = new Bloc();
//        updatedDetails.setIdBloc(1L);
//        updatedDetails.setCapaciteBloc(100);
//        updatedDetails.setNomBloc("bloc10");
//
//        // When
//        Bloc updatedBloc = blocService.modifyBloc(updatedDetails);
//
//        // Then
//        Assertions.assertNotNull(updatedBloc);
//    }

    @Test
    public void testDeleteBloc() {
        // Given
        doNothing().when(blocRepository).deleteById(2L);

        // When
        blocService.removeBloc(2L);

        // Then
        verify(blocRepository, times(1)).deleteById(2L);
    }
}
