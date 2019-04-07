package ut.com.idalko.plugins.jira.customfields;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.idalko.plugins.jira.customfields.ParenBalanceCustomField;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @since 3.5
 */
public class ParenBalanceCustomFieldTest {

    @Before
    public void setup() {
		System.out.println("Test setup");
    }

    @After
    public void tearDown() {
		System.out.println("Test tearDown");
    }

    @Test(expected=Exception.class)
    public void testSomething() throws Exception {

        //ParenBalanceCustomField testClass = new ParenBalanceCustomField();

        throw new Exception("ParenBalanceCustomField has no tests!");

    }

}
