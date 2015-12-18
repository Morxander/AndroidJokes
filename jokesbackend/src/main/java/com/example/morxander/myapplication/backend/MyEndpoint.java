/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.morxander.myapplication.backend;

import com.example.JavaJokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.morxander.example.com",
    ownerName = "backend.myapplication.morxander.example.com",
    packagePath=""
  )
)
public class MyEndpoint {
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        JavaJokes jokes = new JavaJokes();
        response.setData(jokes.tellMeJoke());
        return response;
    }

}
