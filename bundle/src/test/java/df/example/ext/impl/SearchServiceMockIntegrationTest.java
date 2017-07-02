package df.example.ext.impl;

import df.example.ext.api.SearchService;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
public class SearchServiceMockIntegrationTest extends AbstractSearchServiceIntegrationTest {

    SearchService underTest = new SearchServiceMockImpl();

    SearchService getSearchService() {
        return underTest;
    }
}
