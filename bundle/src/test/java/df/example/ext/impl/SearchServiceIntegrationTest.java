package df.example.ext.impl;

import df.example.ext.api.SearchService;
import df.example.ext.test.IntegrationTest;
import org.junit.experimental.categories.Category;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
@Category(IntegrationTest.class)
public class SearchServiceIntegrationTest extends AbstractSearchServiceIntegrationTest {

    SearchService underTest = new SearchServiceImpl();

    SearchService getSearchService() {
        return underTest;
    }

}
