package burrows.apps.math.res;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricGradleTestRunner.class)
public class AndroidManifestTest {

    Document document;
    Node node;
    Element element;

    @Before
    public void setUp() throws Exception {
        File file = new File("./src/main/AndroidManifest.xml");
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        // Normalize
        document.getDocumentElement().normalize();
    }

    @Test
    public void test_manfiest_tag() {
        // manifest tag
        node = document.getElementsByTagName("manifest").item(0);
        element = (Element) node;

        // manifest attributes
        assertThat(element.getAttribute("xmlns:android"), is("http://schemas.android.com/apk/res/android"));
        assertThat(element.getAttribute("package"), containsString("burrows.apps"));
        assertThat(element.getAttribute("android:installLocation"), is("auto"));
        assertThat(element.hasAttribute("android:versionCode"), is(true));
        assertThat(element.hasAttribute("android:versionName"), is(true));
    }

    @Test
    public void test_support_screens_tag() {
        // supports-screens tag
        node = document.getElementsByTagName("supports-screens").item(0);
        element = (Element) node;

        // supports-screens attributes
        assertThat(element.getAttribute("android:anyDensity"), is("true"));
        assertThat(element.getAttribute("android:largeScreens"), is("true"));
        assertThat(element.getAttribute("android:normalScreens"), is("true"));
        assertThat(element.getAttribute("android:smallScreens"), is("true"));
        assertThat(element.getAttribute("android:xlargeScreens"), is("true"));
    }

    @Test
    public void test_application_tag() {
        // application tag
        node = document.getElementsByTagName("application").item(0);
        element = (Element) node;

        // application attributes
        assertThat(element.getAttribute("android:label"), is("@string/app_name"));
        assertThat(element.getAttribute("android:icon"), is("@mipmap/ic_launcher"));
        assertThat(element.getAttribute("android:theme"), is("@style/AppTheme"));
        assertThat(element.getAttribute("android:allowBackup"), is("true"));
        assertThat(element.getAttribute("android:supportsRtl"), is("true"));
    }
}
