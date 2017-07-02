package df.example.ext.impl.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 02.07.17.
 */
public class ItemTest {

    Item underTest = new Item(1, "url", "name", "fullName");

    @Test
    public void shouldConstructEmpty() throws Exception {
        Item item = new Item();
        assertNull(item.getId());
    }

    @Test
    public void getId() throws Exception {
        assertEquals(underTest.getId(), Integer.valueOf(1));
    }

    @Test
    public void getUrl() throws Exception {
        assertEquals(underTest.getUrl(), "url");
    }

    @Test
    public void getName() throws Exception {
        assertEquals(underTest.getName(), "name");
    }

    @Test
    public void getFullName() throws Exception {
        assertEquals(underTest.getFullName(), "fullName");
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(underTest.getTitle(), "name");
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(underTest.getDescription(), "fullName");
    }

    @Test
    public void toString_shouldPrintValues() throws Exception {
        String str = underTest.toString();

        assertEquals(str, "Item{id=1, url=url, name=name, fullName=fullName}");
    }

    @Test
    public void toString_shouldAcceptNullValues() throws Exception {
        Item item = new Item();
        String str = item.toString();

        assertEquals(str, "Item{id=null, url=null, name=null, fullName=null}");
    }

}