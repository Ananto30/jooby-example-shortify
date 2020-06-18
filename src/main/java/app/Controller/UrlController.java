package app.Controller;

import app.data.Response;
import app.service.UrlService;
import io.jooby.Context;
import io.jooby.annotations.GET;
import io.jooby.annotations.POST;
import io.jooby.annotations.PathParam;
import org.springframework.stereotype.Controller;


@Controller
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

//    @GET(path = "/")
//    public Response sayHi(Context ctx) {
//        System.out.println(urlService);
////        ctx.se(MediaType.json);
//        return Response.builder().book(urlService.getBooks()).build();
////        return gson.toJson(bookService.getBooks());
//
//    }

    @POST(path = "/")
    public Response addUrl(Context ctx, ) {
        System.out.println(urlService);
//        ctx.se(MediaType.json);
        return Response.builder().book(urlService.toString()).build();
//        return gson.toJson(bookService.getBooks());

    }

    @GET(path = "/{shortCode}")
    public String sayBye(@PathParam String shortCode) {
        return urlService.getUrlByShortCode(shortCode);
    }

}
