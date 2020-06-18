package app.Controller;

import app.data.AddUrlRequest;
import app.data.AddUrlResponse;
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

    @POST(path = "/url")
    public AddUrlResponse addUrl(Context ctx, AddUrlRequest addUrlRequest) {
        String shortCode = urlService.addUrl(addUrlRequest.getUrl());
        return AddUrlResponse.builder().shortCode(shortCode).build();

    }

    @GET(path = "/{shortCode}")
    public String sayBye(@PathParam String shortCode) {
        return urlService.getUrlByShortCode(shortCode);
    }

}
