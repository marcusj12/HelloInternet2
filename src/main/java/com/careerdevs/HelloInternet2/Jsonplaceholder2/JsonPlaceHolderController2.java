package com.careerdevs.HelloInternet2.Jsonplaceholder2;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/jsonplaceholder2/")
public class JsonPlaceHolderController2 {

    //http://localhost:4000/jsonplaceholder2/test GET --> worked
    @GetMapping("/test")
    public String test(){
        return "The test worked!!!";
        }

        /*

        GET	/posts
        GET	/posts/1 // One Singular post based off its ID
        GET	/posts/1/comments
        GET	/comments?postId=1
        POST	/posts
        PUT	/posts/1
        PATCH	/posts/1
        DELETE	/posts/1
         */


    @GetMapping("/getAllPost")
    public Object getPosts(RestTemplate restTemplate) {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/", Object.class);
    }

    @GetMapping("/getPostById{id}") //"https://jsonplaceholder.typicode.com/posts/13"
    public Object getPostById(RestTemplate restTemplate, @PathVariable String id) {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + id, Object.class);
    }

    @GetMapping("/posts/{id}/comments")
    public Object getCommentsByPost(RestTemplate restTemplate, @PathVariable String id){
       return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + id + "/comments", Object.class);
    }

    @GetMapping("/comments") //http: /jsonplaceholder2/comments?id=3
    public Object getCommentsByPostQuery(RestTemplate restTemplate, @RequestParam String id) {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments?postId=" + id, Object.class);
    }

    @PostMapping("/posts")
        public Post createPost(RestTemplate restTemplate, @RequestBody Post newPost) {
            return restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", newPost, Post.class);
    }

    @PutMapping("/posts/{id}")
        public String updatePost(RestTemplate restTemplate, @PathVariable int id, @RequestBody Post post){
            restTemplate.put("https://jsonplaceholder.typicode.com/posts/" + id, post);
            return "Post with id " + id + " successfully updated";
    }

    @DeleteMapping("/posts/{id}")
        public String deletePost(RestTemplate restTemplate, @PathVariable int id){
        restTemplate.delete("https://jsonplaceholder.typicode.com/posts/" + id);
        return "Deleted post with ID " + id;
    }

}

/*
1. For the instance of the JsonPlaceHolderController2 class we add the @RestController annotation so that way our code is
being compiled and can boot up the server

SpringBoot is built up by annotations(usually in yellow) ; we put them on all types of things like classes, variables, methods
when we want to denote some special property. FOr the instance of the

This is considered a third party API  because we're accessing data that is not on our local machines or network.
We're actually requesting proprietary data from a server that does not belong to us.

getForObject requires two parameters: (1. URL , 2.Return Type)

RestTemplate - Name of class or type of template
vs
restTemplate - The variable or instance
** its typical to make the variable the same as the template but just lower case for clean coding**

When you declare (append) a string to an id that is an integer, you don't have to worry because it will stringify the id

When we denote something with PathVariables the name of the variable has correlated to the variable within the GetMapping
 */