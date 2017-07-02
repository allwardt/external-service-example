package df.example.ext.impl;

import com.google.common.collect.Lists;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 02.07.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PublicSearchServiceImplTest {

    @Mock
    SearchService searchService;

    PublicSearchServiceImpl underTest = new PublicSearchServiceImpl();


    @Before
    public void setUp() {
        Whitebox.setInternalState(underTest, "impl", searchService);
    }

    @Test
    public void search_shouldUseWrappedService() throws Exception {
        List<SearchResult> list = Lists.newArrayList();
        Mockito.when(searchService.search("test")).thenReturn(list);

        List<SearchResult> result = underTest.search("test");

        assertEquals(list, result);

    }

}