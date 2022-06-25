package tests.ui.browser;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("UI")
@Tag("Browser")
public class HomeTests extends BaseBrowserTest {
    @Test
    public void testSignInButton() {
        assertThat(homePage.signInButtonIsPresent(), is(true));
        homePage.clickSignIn();
    }

    @Test
    public void testSignInErrorMessages() {
        homePage.signInWithCredentials("","");
        assertThat(homePage.getSignInErrorMessage(), containsString("Please enter your email address or mobile number"));

        homePage.signInWithCredentials("invalid","");
        assertThat(homePage.getSignInErrorMessage(), containsString("Please enter a valid email address or mobile number"));

        homePage.signInWithCredentials("johnrey.sanmiguel@gmail.com","");
        assertThat(homePage.getSignInErrorMessage(), containsString("Please enter your password"));

        homePage.signInWithCredentials("johnrey.sanmiguel@gmail.com","test");
        assertThat(homePage.getSignInErrorMessage(), containsString("Password must be 8 characters or more"));
    }

    @Test
    public void testFailureInReport() {
        // this will fail on purpose to show both passing and failing tests in the report
        homePage.signInWithCredentials("johnrey.sanmiguel@gmail.com","testing");
        assertThat(homePage.getSignInErrorMessage(), containsString("Please enter your password"));
    }
}
