package Request;

import java.util.Locale;

public class Factory {

    public static IRquest make(String methodRequest){
        IRquest request;
        switch (methodRequest.toLowerCase()){
            case "getToken":
                request = new RequestGETToken();
                break;
            case "post":
                request = new RequestPOST();
                break;

        }
    }

}
