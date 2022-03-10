package factoryRequest;

public class FactoryRequest {

    public static IRequest make (String methodRequest){
        IRequest request;
        switch (methodRequest.toLowerCase()){
            case "post":
                request= new RequestPOST();
                break;
            case "put":
                request = new RequestPUT();
                break;
            case "delete":
                request = new RequestDELETE();
                break;
            default:
                request = new RequestGET();
                break;
        }
        return request;
    }

}