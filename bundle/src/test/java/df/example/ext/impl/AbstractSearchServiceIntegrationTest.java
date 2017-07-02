package df.example.ext.impl;

import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
public abstract class AbstractSearchServiceIntegrationTest {

    abstract SearchService getSearchService();

    @Test
    public void shouldGetResults() throws Exception {
        final List<SearchResult> items = getSearchService().search("aem");
        assertThat(items.size(), is(30));

        SearchResult first = items.get(0);
        assertNotNull(first);
        assertThat(first.getTitle(), is("acs-aem-commons"));
        assertThat(first.getDescription(), is("Adobe-Consulting-Services/acs-aem-commons"));
        assertThat(first.getUrl(), is("https://github.com/Adobe-Consulting-Services/acs-aem-commons"));

        SearchResult second = items.get(1);
        assertNotNull(second);
        assertThat(second.getTitle(), is("aem-project-archetype"));
        assertThat(second.getDescription(), is("Adobe-Marketing-Cloud/aem-project-archetype"));
        assertThat(second.getUrl(), is("https://github.com/Adobe-Marketing-Cloud/aem-project-archetype"));
    }

}