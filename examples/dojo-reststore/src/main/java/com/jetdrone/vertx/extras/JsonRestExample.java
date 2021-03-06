package com.jetdrone.vertx.extras;

import com.jetdrone.vertx.yoke.Yoke;
import com.jetdrone.vertx.yoke.middleware.*;
import com.jetdrone.vertx.yoke.middleware.BodyParser;
import org.vertx.java.core.Handler;
import org.vertx.java.platform.Verticle;

public class JsonRestExample extends Verticle {

    @Override
    public void start() {
        final Yoke yoke = new Yoke(this);

        yoke.use(new BodyParser());
        yoke.use(new ErrorHandler(true));

        // db access
        final InMemoryStore db = new InMemoryStore();

        // preload states from config
        db.bulkLoad("states", "id", container.config().getArray("states"));

        yoke.use(new JsonRestRouter(db)
                .rest("/states", "states")
        );

        yoke.use(new Static("static"));

        yoke.use(new Handler<YokeRequest>() {
            @Override
            public void handle(YokeRequest request) {
                request.response().redirect("/index.html");
            }
        });

        yoke.listen(8080);

        container.logger().info("Yoke server listening on port 8080");
    }
}
