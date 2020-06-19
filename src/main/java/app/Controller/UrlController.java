package app.Controller;

import app.data.AddUrlRequest;
import app.data.AddUrlResponse;
import app.data.ResponseFormat;
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
    public ResponseFormat addUrl(Context ctx, AddUrlRequest addUrlRequest) {
        String shortCode = urlService.addUrl(addUrlRequest.getUrl());
        return ResponseFormat.builder()
                .status("200")
                .data(AddUrlResponse.builder()
                        .shortCode(shortCode)
                        .build())
                .build();
    }

    @GET(path = "/{shortCode}")
    public void getUrl(@PathParam String shortCode, Context ctx) {
        ctx.sendRedirect(urlService.getUrlByShortCode(shortCode));
    }

}
