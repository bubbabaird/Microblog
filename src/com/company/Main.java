package com.company;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    // currently logged in user to display in html
    static User user;
    // multiple messages
    public static ArrayList<Message> messages = new ArrayList<>();

    // Create a user

    // create a message

    // People need to be able to add a message

    public static void main(String[] args) {

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "index.html");
                    } else {
                        m.put("name", user.name);
                        m.put("notes", messages);
                        return new ModelAndView(m, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                //create an end point to create a new message
                "/create-message",
                ((request, response) -> {
                    String message = request.queryParams("message-text");
                    //convert a string into a new message object
                    Message m = new Message(message);
                    messages.add(m);
                    response.redirect("/");
                    return "";
                })
        );
    }
}


// Create a GET route for "/" and a POST route for "/create-user"
// and "/create-message".

// When the user hits submit in "index.html", it should post the
// name to "/create-user" which saves it in a user object and
// redirects to "/".

// Create resources/templates/messages.html which looks like the
// second screenshot below. It must display the name given by the
// user, and use Mustache templating to list the messages written
// by the user.

// When the user hits submit in "messages.html", it should post
// the text to "/create-message" which saves it in an
// ArrayList<Message> and redirects to "/" (i.e. refreshes the page).
