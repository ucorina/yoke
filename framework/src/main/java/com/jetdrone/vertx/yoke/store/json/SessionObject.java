package com.jetdrone.vertx.yoke.store.json;

import org.vertx.java.core.json.JsonObject;

public class SessionObject extends ChangeAwareJsonObject {

	public SessionObject(JsonObject jsonObject) {
	    super(jsonObject);
    }

	public SessionObject(JsonObject jsonObject, boolean initialChanged) {
	    super(jsonObject, initialChanged);
    }

}
