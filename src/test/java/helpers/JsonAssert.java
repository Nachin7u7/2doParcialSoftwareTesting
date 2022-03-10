package helpers;

import org.json.JSONObject;

import java.util.Iterator;

public class JsonAssert {
	public static void areEqualJson(String expected, String actual, String msg) throws Exception {
        boolean areEqual = true;
        JSONObject expectedJSON = new JSONObject(expected);
        JSONObject actualJSON = new JSONObject(actual);
        Iterator<?> attributes = expectedJSON.keys();

        while (attributes.hasNext()) {
            String attribute = (String) attributes.next();
            if (expectedJSON.has(attribute) && actualJSON.has(attribute)) {
                String expectedValue = String.valueOf(expectedJSON.get(attribute));
                String actualValue = String.valueOf(actualJSON.get(attribute));
                if (expectedValue.equals("IGNORE") || actualValue.equals("IGNORE")) {
                    System.out.println("INFO> ignorando el attribute: [" + attribute + "]");
                } else if (!expectedValue.equals(actualValue)) {
                    System.out.println("ERROR> el attribute: [" + attribute + "] > actual: " + actualValue +
                            " vs expected: " + expectedValue);
                    areEqual = false;
                    msg = msg + "\nERROR> el attribute: [" + attribute + "] > actual: " + actualValue +
                            " vs expected: " + expectedValue;
                }
            } else {
                System.out.println("ERROR> el attribute no existe: [" + attribute + "]");
                msg = msg + "\nERROR> el attribute no existe: [" + attribute + "]";
                areEqual = false;
            }

        }
        if (!areEqual)
            throw new Exception(msg);
    }
}
