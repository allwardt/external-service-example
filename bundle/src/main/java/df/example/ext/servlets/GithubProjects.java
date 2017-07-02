package df.example.ext.servlets;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 02.07.17.
 */

import df.example.ext.api.SearchException;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@SlingServlet(paths = "/bin/example/github-projects")
public class GithubProjects extends SlingSafeMethodsServlet {

    @Reference
    SearchService searchService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String search = getSearch(request, "aem");
        try {
            final List<SearchResult> searchResults = searchService.search(search);
            JSONObject jsonobject = toJson(search, searchResults);
            response.getWriter().println(jsonobject.toString(4));
        } catch (SearchException e) {
            response.getWriter().println("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (JSONException e) {
            throw new ServletException(e);
        }
    }

    private JSONObject toJson(String search, List<SearchResult> searchResults) throws JSONException {
        JSONArray list = new JSONArray();
        for (SearchResult searchResult : searchResults) {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("title", searchResult.getTitle());
            jsonobject.put("description", searchResult.getDescription());
            jsonobject.put("url", searchResult.getUrl());
            list.put(jsonobject);
        }
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("search", search);
        jsonobject.put("results", list);
        return jsonobject;
    }

    private String getSearch(SlingHttpServletRequest request, String def) {
        String ret = request.getParameter("search");
        return ret != null ? ret : def;
    }
}
