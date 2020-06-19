package app;

import app.data.ResponseFormat;
import com.google.gson.Gson;
import io.jooby.Jooby;
import io.jooby.Router;
import io.jooby.di.SpringModule;
import io.jooby.json.GsonModule;
import io.jooby.redis.RedisModule;

import java.util.Collections;

public class App extends Jooby {

    {
        install(new SpringModule());
        install(new RedisModule());
        install(new GsonModule());

        Gson gson = require(Gson.class);

        error((ctx, cause, statusCode) -> {
            Router router = ctx.getRouter();
            router.getLog().error("found `{}` error", statusCode.value(), cause);
            ctx.setResponseCode(statusCode);
            ctx.setResponseHeader("content-type", "application/json;charset=UTF-8");
            ctx.send(gson.toJson(ResponseFormat.builder()
                    .status(statusCode.toString())
                    .errors(Collections.singletonList(cause.getLocalizedMessage()))
                    .build()));
        });


        // request response logger
        decorator(next -> ctx -> {
            long start = System.currentTimeMillis();

            Object response = next.apply(ctx);

            long end = System.currentTimeMillis();
            long took = end - start;

            ctx.getRouter().getLog().info(ctx.getRequestPath() + " | " + ctx.body().value() + " | Took: " + took + "ms");

            return response;
        });


    }


    public static void main(final String[] args) {
        runApp(args, App::new);
    }

}
