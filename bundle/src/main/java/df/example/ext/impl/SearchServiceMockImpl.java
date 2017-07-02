package df.example.ext.impl;

import df.example.ext.api.SearchException;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import df.example.ext.impl.model.Item;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
public class SearchServiceMockImpl implements SearchService {

    private static List<SearchResult> testData = new ArrayList<SearchResult>();
    static {
        testData.add(new Item(1, "https://github.com/Adobe-Consulting-Services/acs-aem-commons", "acs-aem-commons", "Adobe-Consulting-Services/acs-aem-commons"));
        testData.add(new Item(2, "https://github.com/Adobe-Marketing-Cloud/aem-project-archetype", "aem-project-archetype", "Adobe-Marketing-Cloud/aem-project-archetype"));

        for (int i = 0; i < 28; i++) {
            testData.add(new Item(i, "http://example.com", format("example-%d", i), format("Example %d", i)));
        }
    }

    public List<SearchResult> search(String search) throws SearchException {
        return testData;
    }
}
