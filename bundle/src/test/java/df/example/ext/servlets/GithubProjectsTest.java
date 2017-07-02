package df.example.ext.servlets;

import com.google.common.collect.Lists;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import df.example.ext.api.SearchException;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 02.07.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class GithubProjectsTest {

    @Mock
    SearchService searchService;

    @InjectMocks
    GithubProjects underTest;

    @Captor
    ArgumentCaptor<String> responseCaptor;

    @Test
    public void doGet__results__shouldReturnJsonList() throws Exception {
        // given
        SearchResult searchResult = mock(SearchResult.class);
        when(searchResult.getTitle()).thenReturn("title");
        when(searchResult.getDescription()).thenReturn("description");
        when(searchResult.getUrl()).thenReturn("url");

        when(searchService.search("acs-aem")).thenReturn(Lists.newArrayList(searchResult));

        SlingHttpServletRequest request = mock(SlingHttpServletRequest.class);
        when(request.getParameter("search")).thenReturn("acs-aem");
        SlingHttpServletResponse response = mock(SlingHttpServletResponse.class, Answers.RETURNS_DEEP_STUBS.get());

        // when
        underTest.doGet(request, response);

        // then
        verify(response.getWriter()).println(responseCaptor.capture());

        String json = responseCaptor.getValue();
        final DocumentContext parse = JsonPath.parse(json);

        assertThat(parse.read("$.search").toString(), is("acs-aem"));
        assertThat(parse.read("$.results[0].title").toString(), is("title"));
        assertThat(parse.read("$.results[0].description").toString(), is("description"));
        assertThat(parse.read("$.results[0].url").toString(), is("url"));

    }

    @Ignore
    public void doGet__searchException__getErrorMessage() throws Exception {
        // given
        when(searchService.search(Mockito.anyString())).thenThrow(new SearchException(new Exception("Error message")));

        SlingHttpServletRequest request = mock(SlingHttpServletRequest.class);
        SlingHttpServletResponse response = mock(SlingHttpServletResponse.class, Answers.RETURNS_DEEP_STUBS.get());

        // when
        underTest.doGet(request, response);

        // then
        verify(response.getWriter()).println(responseCaptor.capture());

        String json = responseCaptor.getValue();
        final DocumentContext parse = JsonPath.parse(json);

        assertThat(parse.read("$.error").toString(), is("java.lang.Exception: Error message"));
    }

}