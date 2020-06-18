package app;

import io.jooby.Jooby;
import io.jooby.Router;
import io.jooby.di.SpringModule;
import io.jooby.json.GsonModule;
import io.jooby.redis.RedisModule;

public class App extends Jooby {

    {
        install(new SpringModule());
        install(new RedisModule());
        install(new GsonModule());

        error((ctx, cause, statusCode) -> {
            Router router = ctx.getRouter();
            router.getLog().error("found `{}` error", statusCode.value(), cause);
            ctx.setResponseCode(statusCode);
            ctx.send("found `" + statusCode.value() + "` error");
        });
    }


    public static void main(final String[] args) {
        runApp(args, App::new);
    }

}
